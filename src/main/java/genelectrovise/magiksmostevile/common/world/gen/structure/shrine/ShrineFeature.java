package genelectrovise.magiksmostevile.common.world.gen.structure.shrine;

import java.util.Random;
import java.util.function.Function;
import com.mojang.datafixers.Dynamic;
import genelectrovise.magiksmostevile.common.main.MagiksMostEvile;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ResourceLocationException;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

@Deprecated
public class ShrineFeature extends Feature<IFeatureConfig> {

  private static final ResourceLocation SHRINE_TEMPLATE =
      new ResourceLocation(MagiksMostEvile.MODID, "structure/shrine/shrine_template");

  public ShrineFeature(Function<Dynamic<?>, ? extends ShrineFeatureConfig> config) {
    super(config);
  }

  @Override
  public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator,
      Random rand, BlockPos pos, IFeatureConfig config) {

    if (!worldIn.isRemote()) {
      ServerWorld serverWorld =
          (ServerWorld) worldIn.getWorld().getServer().getWorld(worldIn.getDimension().getType());
      TemplateManager manager = serverWorld.getStructureTemplateManager();

      Template template;
      try {
        // manager.func_227458_a_(); //Requires the nbt data as a compound tag
        MagiksMostEvile.LOGGER.debug("Generating Shrine using template at " + SHRINE_TEMPLATE);
        MagiksMostEvile.LOGGER.debug("Template file : " + SHRINE_TEMPLATE.getPath());
        MagiksMostEvile.LOGGER.debug("Namespace : " + SHRINE_TEMPLATE.getNamespace());

        MagiksMostEvile.LOGGER.debug("TemplateManager attempting to get template from file "
            + SHRINE_TEMPLATE + " : " + manager.getTemplate(SHRINE_TEMPLATE));
        template = manager.getTemplate(SHRINE_TEMPLATE);
      } catch (ResourceLocationException r) {
        System.err.println("The resource " + SHRINE_TEMPLATE + " does not exist!");
        return false;
      }

      return build(template, worldIn, pos, rand);

    }
    return false;
  }

  private boolean build(Template template, IWorld worldIn, BlockPos pos, Random rand) {

    try {
      PlacementSettings placement = new PlacementSettings();

      switch (rand.nextInt(4)) {
        case (0):
          placement.setRotation(Rotation.NONE);
          break;
        case (1):
          placement.setRotation(Rotation.CLOCKWISE_90);
          break;
        case (2):
          placement.setRotation(Rotation.CLOCKWISE_180);
          break;
        case (3):
          placement.setRotation(Rotation.COUNTERCLOCKWISE_90);
          break;
      }

      template.addBlocksToWorld(worldIn, pos, placement);
    } catch (Exception e) {
      System.err.println("Error generating ShrineFeature!");
      e.printStackTrace();
      System.err.println("World : " + worldIn);
      System.err.println("BlockPos : " + pos);
      System.err.println("Random : " + rand);
      System.err.println("Template : " + template.toString());
    }
    return true;
  }

}
