package genelectrovise.magiksmostevile.network.pixiecourier.packet;

import net.minecraft.util.math.BlockPos;

public class TransferEnergyParticlePacket {

  private BlockPos departure;
  private BlockPos destination;

  public TransferEnergyParticlePacket(BlockPos departure, BlockPos destination) {
    this.departure = departure;
    this.destination = destination;
  }

  public BlockPos getDeparture() { return departure; }

  public void setDeparture(BlockPos departure) { this.departure = departure; }

  public BlockPos getDestination() { return destination; }

  public void setDestination(BlockPos destination) { this.destination = destination; }


}
