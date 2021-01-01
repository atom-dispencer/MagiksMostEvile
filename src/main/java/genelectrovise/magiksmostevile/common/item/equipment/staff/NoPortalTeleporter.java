package genelectrovise.magiksmostevile.common.item.equipment.staff;

import java.util.Optional;
import java.util.function.Function;
import net.minecraft.entity.Entity;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.TeleportationRepositioner.Result;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.server.ServerWorld;

public class NoPortalTeleporter extends Teleporter {

  public NoPortalTeleporter(ServerWorld worldIn) {
    super(worldIn);
  }

  @Override
  public Optional<Result> makePortal(BlockPos pos, Axis axis) {
    return Optional.of(null);
  }

  @Override
  public Optional<Result> getExistingPortal(BlockPos pos, boolean isNether) {
    return Optional.of(null);
  }

  @Override
  public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld,
      float yaw, Function<Boolean, Entity> repositionEntity) {
    return super.placeEntity(entity, currentWorld, destWorld, yaw, repositionEntity);
  }

}
