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
package genelectrovise.magiksmostevile.tileentity.altar;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import genelectrovise.magiksmostevile.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.network.particle.ParticleNetworkingManager;
import genelectrovise.magiksmostevile.network.particle.transfer_energy.TransferEnergyMessageToClient;
import genelectrovise.magiksmostevile.registry.orbital.registries.BlockOrbitalRegistry;
import genelectrovise.magiksmostevile.registry.orbital.registries.RitualOrbitalRegistry;
import genelectrovise.magiksmostevile.registry.orbital.registries.TileEntityOrbitalRegistry;
import genelectrovise.magiksmostevile.ritual.Ritual;
import genelectrovise.magiksmostevile.tileentity.ICustomContainer;
import genelectrovise.magiksmostevile.tileentity.IchorFluidStorage;
import genelectrovise.magiksmostevile.tileentity.amethyst_crystal.AmethystCrystalTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.client.gui.screen.EnchantmentScreen;
import net.minecraft.client.particle.EnchantmentTableParticle.EnchantmentTable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.EnchantmentContainer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.EnchantingTableTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

/**
 * @see EnchantmentTable
 * @see EnchantmentContainer
 * @see EnchantmentScreen
 * @see EnchantingTableBlock
 * @see EnchantingTableTileEntity
 * @author GenElectrovise 14 May 2020
 */
public class AltarTileEntity extends TileEntity implements ITickableTileEntity, ICustomContainer {

  public static final Logger LOGGER = LogManager.getLogger();

  private static final int BASE_ICHOR_CAPACITY = 50;
  private static final int ADDITIONAL_STORAGE_PER_CRYSTAL = 50;
  private static final int DAY_ICHOR_PER_CRYSTAL = 1;
  private static final int NIGHT_ICHOR_PER_CRYSTAL = 3;

  private int tickIncr = 0;
  private int recieveFluxCountdown = 0;
  private ArrayList<AmethystCrystalTileEntity> crystals = new ArrayList<AmethystCrystalTileEntity>();
  public boolean isCasting = false;
  public Ritual currentRitual;

  // IItemHandler
  protected ItemStackHandler slot_0;
  protected ItemStackHandler slot_1;
  protected ItemStackHandler slot_2;
  protected ItemStackHandler slot_3;

  private final LazyOptional<IItemHandler> slot_0_holder = LazyOptional.of(() -> slot_0);
  private final LazyOptional<IItemHandler> slot_1_holder = LazyOptional.of(() -> slot_1);
  private final LazyOptional<IItemHandler> slot_2_holder = LazyOptional.of(() -> slot_2);
  private final LazyOptional<IItemHandler> slot_3_holder = LazyOptional.of(() -> slot_3);

  private final LazyOptional<IItemHandler> allSlots = LazyOptional.of(() -> new CombinedInvWrapper(slot_0, slot_1, slot_2, slot_3));

  // IFluidTank
  protected IchorFluidStorage ichorStorage;
  private final LazyOptional<IFluidTank> ichorStorageLazyOptional = LazyOptional.of(() -> ichorStorage);

  // Constructor

  public AltarTileEntity() {
    super(TileEntityOrbitalRegistry.TILE_ENTITY_ALTAR.get());

    // IItemHandler
    slot_0 = new ItemStackHandler() {
      @Override
      protected void onContentsChanged(int slot) {
        setChanged();
      }
    };

    slot_1 = new ItemStackHandler() {
      @Override
      protected void onContentsChanged(int slot) {
        setChanged();
      }
    };

    slot_2 = new ItemStackHandler() {
      @Override
      protected void onContentsChanged(int slot) {
        setChanged();
      }
    };

    slot_3 = new ItemStackHandler() {
      @Override
      protected void onContentsChanged(int slot) {
        setChanged();
      }
    };

    // IFluidTank
    ichorStorage = new IchorFluidStorage(BASE_ICHOR_CAPACITY, MagiksMostEvile.MODID + ":ichorStorage") {

    };
  }

  // IItemHandler
  @Override
  public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
    // IItemHandler
    if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
      this.setChanged();

      // if the block at myself isn't myself, allow full access (Block Broken)
      if (level != null && level.getBlockState(worldPosition).getBlock() != this.getBlockState().getBlock()) {
        return allSlots.cast();
      }
      if (side == null) {
        return allSlots.cast();
      }
    }

    // IFluidTank
    if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
      this.setChanged();

      // if the block at myself isn't myself, allow full access (Block Broken)
      if (level != null && level.getBlockState(worldPosition).getBlock() != this.getBlockState().getBlock()) {
        return ichorStorageLazyOptional.cast();
      }
      if (side == null) {
        return ichorStorageLazyOptional.cast();
      }
    }

    return super.getCapability(capability, side);
  }

  // Generic stuff for tile entities

  @Override
  public void setRemoved() {
    super.setRemoved();
    slot_0_holder.invalidate();
    slot_1_holder.invalidate();
    slot_2_holder.invalidate();
    slot_3_holder.invalidate();
    allSlots.invalidate();

    ichorStorageLazyOptional.invalidate();
  }

  @Override
  public void load(BlockState state, CompoundNBT tag) {
    super.load(state, tag);
    slot_0.deserializeNBT(tag.getCompound(MagiksMostEvile.MODID + ":slot_0"));
    slot_1.deserializeNBT(tag.getCompound(MagiksMostEvile.MODID + ":slot_1"));
    slot_2.deserializeNBT(tag.getCompound(MagiksMostEvile.MODID + ":slot_2"));
    slot_3.deserializeNBT(tag.getCompound(MagiksMostEvile.MODID + ":slot_3"));
    ichorStorage.fromNbt(tag.getCompound(ichorStorage.nbtKey));
    setCasting(tag.getBoolean("casting"));
    ResourceLocation location = new ResourceLocation(tag.getString("ritual"));
    Ritual ritual = location.equals(Ritual.NONE) ? null : getRitualFromResourceLocation(location);

    if (ritual != null) {
      try {
        castRitualAtArbitraryTick(ritual, tag.getInt("ritual_tick"));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public CompoundNBT save(CompoundNBT tag) {
    tag = super.save(tag);
    tag.put(MagiksMostEvile.MODID + ":slot_0", slot_0.serializeNBT());
    tag.put(MagiksMostEvile.MODID + ":slot_1", slot_1.serializeNBT());
    tag.put(MagiksMostEvile.MODID + ":slot_2", slot_2.serializeNBT());
    tag.put(MagiksMostEvile.MODID + ":slot_3", slot_3.serializeNBT());
    tag.put(ichorStorage.nbtKey, ichorStorage.toNbt());
    tag.putBoolean("casting", isCasting());
    tag.putInt("ritual_tick", currentRitual != null ? currentRitual.getTick() : 0);
    tag.putString("ritual", currentRitual != null ? currentRitual.getRegistryName().toString() : Ritual.NONE.toString());

    return tag;
  }

  @Override
  public void onLoad() {
    super.onLoad();
  }

  // ITickableTileEntity
  @Override
  public void tick() {

    if (!level.isClientSide) {
      if (tickIncr % 100 == 0) {
        for (AmethystCrystalTileEntity te : crystals) {
          ParticleNetworkingManager.CTransferEnergy.send(PacketDistributor.ALL.noArg(),
              new TransferEnergyMessageToClient(te.getBlockPos(), this.getBlockPos()));
        }
      }
    }

    if (!level.isClientSide) {
      // Test for crystals nearby
      if (tickIncr % 100 == 0) {
        crystals.clear();

        for (int x = -4; x < 4; x++) {
          for (int y = -4; y < 4; y++) {
            for (int z = -4; z < 4; z++) {
              BlockPos position = new BlockPos(worldPosition.getX() + x, worldPosition.getY() + y, worldPosition.getZ() + z);
              BlockState state = level.getBlockState(position);
              if (state.getBlock() == BlockOrbitalRegistry.AMETHYST_CRYSTAL.get() || level.getBlockEntity(position) instanceof AmethystCrystalTileEntity) {
                crystals.add((AmethystCrystalTileEntity) level.getBlockEntity(position));
              }
            }
          }
        }

        // Increase ichor capacity
        ichorStorage.setCapacity(BASE_ICHOR_CAPACITY + (crystals.size() * ADDITIONAL_STORAGE_PER_CRYSTAL));
      }

      // Receive amethyst flux
      if (recieveFluxCountdown > 20) {
        if (level instanceof ServerWorld) {
          if (!level.isDay()) {
            ichorStorage.fill(new FluidStack(Fluids.LAVA, 1), FluidAction.EXECUTE);

            if (new Random().nextInt(15) == 0) {
              ichorStorage.fill(new FluidStack(Fluids.LAVA, crystals.size() * NIGHT_ICHOR_PER_CRYSTAL), FluidAction.EXECUTE);
            }

            recieveFluxCountdown = 0;
          } else {
            if (new Random().nextInt(25) == 0) {
              ichorStorage.fill(new FluidStack(Fluids.LAVA, crystals.size() * DAY_ICHOR_PER_CRYSTAL), FluidAction.EXECUTE);
            }
          }
        }
      } else {
        recieveFluxCountdown++;
      }

      // Should reset tickIncr
      if (tickIncr > 100) {
        tickIncr = 0;
      } else {
        tickIncr++;
      }
    }

    tickRitual();
  }

  @Override
  public Container createMenu(int id, PlayerInventory playerInv, PlayerEntity player) {
    return new AltarContainer(id, playerInv, new CombinedInvWrapper(slot_0, slot_1, slot_2, slot_3), this);
  }

  @Override
  public void openGUI(ServerPlayerEntity player) {
    if (!level.isClientSide && !isCasting()) {
      NetworkHooks.openGui(player, this, getBlockPos());
    }
  }

  @Override
  public ITextComponent getDisplayName() { return new TranslationTextComponent(MagiksMostEvile.MODID + ":container.altar"); }

  /**
   * @return the ichorStorage
   */
  public int getIchorStored() { return ichorStorage.getFluidAmount(); }

  public boolean fillIchor(int amount) {
    return ichorStorage.fill(new FluidStack(Fluids.LAVA, amount), FluidAction.EXECUTE) == amount;
  }

  public boolean drainIchor(int amount) {
    return ichorStorage.drain(new FluidStack(Fluids.LAVA, amount), FluidAction.EXECUTE).getAmount() == amount;
  }

  // Ritual handling

  /**
   * @return the isCasting
   */
  public boolean isCasting() { return isCasting; }

  /**
   * @param isCasting the isCasting to set
   */
  public void setCasting(boolean isCasting) { this.isCasting = isCasting; }

  /**
   * @param resourceLocation
   * @param serverPlayerEntity
   * @return
   */
  public static Ritual getRitualFromResourceLocation(ResourceLocation resourceLocation) {
    MagiksMostEvile.LOGGER.debug("getting ritual by resource location! : " + resourceLocation);

    ArrayList<Supplier<Ritual>> all = new ArrayList<Supplier<Ritual>>();
    RitualOrbitalRegistry.RITUALS.getEntries().forEach((ritualSupplier) -> all.add(ritualSupplier));

    for (Supplier<Ritual> ritualSupplier : all) {
      if (ritualSupplier.get().getRegistryName().toString()
          .equalsIgnoreCase(resourceLocation.toString())) {
        return ritualSupplier.get();
      }
    }

    return null;
  }

  public void castRitual(Ritual ritual) {
    castRitualAtArbitraryTick(ritual, 0);
  }

  public void castRitualAtArbitraryTick(Ritual ritual, int tick) {
    MagiksMostEvile.LOGGER.debug("casting Ritual!");
    ritual.init(this);
    ritual.setTick(tick);
    ritual.tryStart();
  }

  private void tickRitual() {
    if (this.isCasting) {
      if (this.currentRitual != null) {
        currentRitual.nextCycle();
      }
    }
  }
}
