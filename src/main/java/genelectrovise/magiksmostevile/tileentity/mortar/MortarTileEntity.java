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
package genelectrovise.magiksmostevile.tileentity.mortar;

import genelectrovise.magiksmostevile.data.recipe.SimpleRecipe;
import genelectrovise.magiksmostevile.data.recipe.Usage;
import genelectrovise.magiksmostevile.registry.orbital.RecipeSerializerOrbitalRegistry;
import genelectrovise.magiksmostevile.registry.orbital.TileEntityOrbitalRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class MortarTileEntity extends TileEntity {

    public static final Logger LOGGER = LogManager.getLogger(MortarTileEntity.class);

    private ItemStackHandler slot = new ItemStackHandler() {
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        ;
    };
    protected final LazyOptional<IItemHandler> slotHandler = LazyOptional.of(() -> slot);
    protected final LazyOptional<IItemHandler> allSlots = LazyOptional.of(() -> new CombinedInvWrapper(slot));

    public MortarTileEntity() {
        super(TileEntityOrbitalRegistry.TILE_ENTITY_MORTAR.get());
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {

        // IItemHandler
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            this.setChanged();

            // if the block at myself isn't myself, allow full access (Block Broken)
            if (level != null && level.getBlockState(getBlockPos()).getBlock() != this.getBlockState().getBlock()) {
                return allSlots.cast();
            }

            // If accessing from the top or bottom
            if (side == Direction.UP || side == Direction.DOWN) {
                return allSlots.cast();
            }
        }

        return super.getCapability(capability, side);
    }

    /**
     * Pops the current contents out of the mortar, and adds the new {@link ItemStack} in. Calls
     * {@link #inner_recieveItemStack(ItemStack, IItemHandler) to actually handle the action because
     * debugging within lambdas is a pain.
     *
     * @param playerHeldStack
     */
    public void recieveItemStack(ItemStack playerHeldStack) {
        if (!this.level.isClientSide()) {
            slotHandler.ifPresent((handler) -> {
                inner_recieveItemStack(playerHeldStack, handler);
            });
        }
    }

    /**
     * Only called on server. Pops the current contents out of the mortar, and adds the new
     * {@link ItemStack} in. Called from the lambda of {@link #recieveItemStack(ItemStack)}.
     */
    private void inner_recieveItemStack(ItemStack playerHeldStack, IItemHandler handler) {
        if (!this.level.isClientSide()) {
            popContents();
            handler.insertItem(0, new ItemStack(playerHeldStack.getItem(), 1), false);
            playerHeldStack.shrink(1);
            setChanged();
        }
    }

    /**
     * Pops the contents of this mortar out as an {@link ItemEntity}. Calls
     * {@link #inner_popContents(IItemHandler)} to actually handle the action as debugging within
     * lambdas is a pain.
     */
    public void popContents() {
        if (!this.level.isClientSide()) {
            slotHandler.ifPresent((handler) -> {
                inner_popContents(handler);
            });
        }
    }

    /**
     * Only on server. Pops the contents of the given {@link IItemHandler} out as an {@link ItemEntity}.
     * Called from the lambda of {@link #popContents()} <br>
     * <br>
     * {@link Block#spawnDrops(BlockState, World, BlockPos, TileEntity, Entity, ItemStack)}
     *
     * @param handler
     */
    private void inner_popContents(IItemHandler handler) {
        if (!this.level.isClientSide()) {
            ItemStack out = handler.extractItem(0, 1, false);

            ItemEntity entity = (ItemEntity) EntityType.ITEM.spawn((ServerWorld) level, null, null, getBlockPos().offset(0, 1, 0), SpawnReason.EVENT, false, false);
            entity.setItem(out);

            setChanged();
        }
    }

    /**
     * Called to process the contents of the mortar as a recipe. Checks the contents against all of the
     * usable recipes, and processes the first one it comes across.
     */
    public void recipe() {

        // Do nothing on the client
        if (level.isClientSide()) {
            return;
        }

        // Handle the recipe on the server. Don't let exceptions leak!
        try {
            Map<ResourceLocation, IRecipe<?>> recipes = RecipeSerializerOrbitalRegistry.getRecipes(this.level).get(RecipeSerializerOrbitalRegistry.SIMPLE_TYPE);
            for (IRecipe<?> recipe : recipes.values()) {
                if (recipe instanceof SimpleRecipe) {

                    SimpleRecipe castRecipe = (SimpleRecipe) recipe;

                    if (!castRecipe.craftable((CombinedInvWrapper) allSlots.resolve().orElse(null), Usage.MORTAR)) {
                        return;
                    }

                    IItemHandler handlerRealised = slotHandler.orElse(null);
                    if (handlerRealised == null) {
                        MortarTileEntity.LOGGER.error("Slot handler has been realised to null - unable to process recipe");
                        return;
                    }

                    handlerRealised.extractItem(0, 6400, false);
                    handlerRealised.insertItem(0, castRecipe.getResultItem(), false);

                    popContents();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
