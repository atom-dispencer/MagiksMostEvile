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
