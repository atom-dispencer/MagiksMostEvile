package genelectrovise.magiksmostevile.network.pixiecourier.processor;

import com.google.gson.Gson;
import genelectrovise.magiksmostevile.network.pixiecourier.PixiePacket;
import genelectrovise.magiksmostevile.network.pixiecourier.PixieProcessor;
import genelectrovise.magiksmostevile.network.pixiecourier.packet.TransferEnergyParticlePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class TransferEnergyParticleProcessor implements PixieProcessor {

  public TransferEnergyParticleProcessor() {}

  @Override
  public void process(PixiePacket packet, Context context, Gson gson) {

    if (context.getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
      enqueueParticleSpawn(packet, context, gson);
    }
  }

  @SuppressWarnings("resource")
  protected void enqueueParticleSpawn(PixiePacket packet, Context context, Gson gson) {
    context.enqueueWork(() -> {
      TransferEnergyParticlePacket particlePacket = gson.fromJson(packet.getContent(), TransferEnergyParticlePacket.class);

      Vector3d crystal = new Vector3d(particlePacket.getDeparture().getX(), particlePacket.getDeparture().getY(), particlePacket.getDeparture().getZ());
      Vector3d altar = new Vector3d(particlePacket.getDestination().getX(), particlePacket.getDestination().getY(), particlePacket.getDestination().getZ());

      Vector3d direction = altar.subtract(crystal);
      direction.normalize();

      double mul = 0.4;
      double yMul = mul * 10;

      Minecraft.getInstance().player.clientLevel.addParticle(ParticleTypes.TOTEM_OF_UNDYING, true, particlePacket.getDeparture().getX() + 0.5, particlePacket.getDeparture().getY() + 0.5, particlePacket.getDeparture().getZ() + 0.5, direction.x * mul, direction.y * yMul, direction.z * mul);
    });
  }

}
