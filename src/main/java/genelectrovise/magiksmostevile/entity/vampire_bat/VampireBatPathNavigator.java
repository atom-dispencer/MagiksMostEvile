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
/**
 * 
 */
package genelectrovise.magiksmostevile.entity.vampire_bat;

import net.minecraft.entity.MobEntity;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

/**
 * @author GenElectrovise 4 Jun 2020
 */
public class VampireBatPathNavigator extends PathNavigator {

  /**
   * @param entityIn
   * @param worldIn
   */
  public VampireBatPathNavigator(MobEntity entityIn, World worldIn) {
    super(entityIn, worldIn);
  }

  @Override
  protected PathFinder getPathFinder(int p_179679_1_) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Vector3d getEntityPosition() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected boolean canNavigate() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  protected boolean isDirectPathBetweenPoints(Vector3d posVec31, Vector3d posVec32, int sizeX,
      int sizeY, int sizeZ) {
    // TODO Auto-generated method stub
    return false;
  }

}
