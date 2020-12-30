/**
 * 
 */
package genelectrovise.magiksmostevile.common.particle.glyph;

import java.awt.Color;
import java.util.Locale;
import javax.annotation.Nonnull;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import genelectrovise.magiksmostevile.common.core.registry.orbital.registries.ParticleOrbitalRegistry;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.MathHelper;

/**
 * @author GenElectrovise 15 Jun 2020
 */
public class GlyphParticleData implements IParticleData {

  public GlyphParticleData(Color tint, double diameter) {
    this.tint = tint;
    this.diameter = constrainDiameterToValidRange(diameter);
  }

  public Color getTint() {
    return tint;
  }

  /**
   * @return get diameter of particle in metres
   */
  public double getDiameter() {
    return diameter;
  }

  @Nonnull
  @Override
  public ParticleType<GlyphParticleData> getType() {
    return ParticleOrbitalRegistry.GLYPH_PARTICLE.get();
  }

  // write the particle information to a PacketBuffer, ready for transmission to a
  // client
  @Override
  public void write(PacketBuffer buf) {
    buf.writeInt(tint.getRed());
    buf.writeInt(tint.getGreen());
    buf.writeInt(tint.getBlue());
    buf.writeDouble(diameter);
  }

  // used for debugging I think; prints the data in human-readable format
  @Nonnull
  @Override
  public String getParameters() {
    return String.format(Locale.ROOT, "%s %.2f %i %i %i", this.getType().getRegistryName(),
        diameter, tint.getRed(), tint.getGreen(), tint.getBlue());
  }

  private static double constrainDiameterToValidRange(double diameter) {
    final double MIN_DIAMETER = 0.05;
    final double MAX_DIAMETER = 1.0;
    return MathHelper.clamp(diameter, MIN_DIAMETER, MAX_DIAMETER);
  }

  private Color tint;
  private double diameter;

  // The DESERIALIZER is used to construct FlameParticleData from either command
  // line parameters or from a network packet

  public static final IDeserializer<GlyphParticleData> DESERIALIZER =
      new IDeserializer<GlyphParticleData>() {

        // parse the parameters for this particle from a /particle command
        @Nonnull
        @Override
        public GlyphParticleData deserialize(@Nonnull ParticleType<GlyphParticleData> type,
            @Nonnull StringReader reader) throws CommandSyntaxException {
          reader.expect(' ');
          double diameter = constrainDiameterToValidRange(reader.readDouble());

          final int MIN_COLOUR = 0;
          final int MAX_COLOUR = 255;
          reader.expect(' ');
          int red = MathHelper.clamp(reader.readInt(), MIN_COLOUR, MAX_COLOUR);
          reader.expect(' ');
          int green = MathHelper.clamp(reader.readInt(), MIN_COLOUR, MAX_COLOUR);
          reader.expect(' ');
          int blue = MathHelper.clamp(reader.readInt(), MIN_COLOUR, MAX_COLOUR);
          Color color = new Color(red, green, blue);

          return new GlyphParticleData(color, diameter);
        }

        // read the particle information from a PacketBuffer after the client has
        // received it from the server
        @Override
        public GlyphParticleData read(@Nonnull ParticleType<GlyphParticleData> type,
            PacketBuffer buf) {
          // warning! never trust the data read in from a packet buffer.

          final int MIN_COLOUR = 0;
          final int MAX_COLOUR = 255;
          int red = MathHelper.clamp(buf.readInt(), MIN_COLOUR, MAX_COLOUR);
          int green = MathHelper.clamp(buf.readInt(), MIN_COLOUR, MAX_COLOUR);
          int blue = MathHelper.clamp(buf.readInt(), MIN_COLOUR, MAX_COLOUR);
          Color color = new Color(red, green, blue);

          double diameter = constrainDiameterToValidRange(buf.readDouble());

          return new GlyphParticleData(color, diameter);
        }
      };
}
