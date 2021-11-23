/*******************************************************************************
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise
 *
 * This file is part of Magiks Most Evile. Magiks Most Evile is free software: you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile.
 * If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
package genelectrovise.magiksmostevile.item.equipment.staff;

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
  public Optional<Result> createPortal(BlockPos pos, Axis axis) {
    return Optional.of(null);
  }

  @Override
  public Optional<Result> findPortalAround(BlockPos pos, boolean isNether) {
    return Optional.of(null);
  }

  @Override
  public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld,
      float yaw, Function<Boolean, Entity> repositionEntity) {
    return super.placeEntity(entity, currentWorld, destWorld, yaw, repositionEntity);
  }

}
