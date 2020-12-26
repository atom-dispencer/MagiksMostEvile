/**
 * 
 */
package genelectrovise.magiksmostevile.common.ritual;

import genelectrovise.magiksmostevile.common.core.MagiksMostEvile;
import genelectrovise.magiksmostevile.common.core.registry.EvileDeferredRegistry;
import genelectrovise.magiksmostevile.common.network.glyph.GlyphMessageToClient;
import genelectrovise.magiksmostevile.common.network.glyph.GlyphNetworkingManager;
import genelectrovise.magiksmostevile.common.ritual.glyph.Glyph.GlyphOrientation;
import genelectrovise.magiksmostevile.common.ritual.result.ConvertAmethystResultHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

/**
 * @author GenElectrovise 19 May 2020
 */
public class ConvertAmethystRitual extends Ritual {
  public static final String displayName = "Magikify Amethyst";
  public static final String description = "Imbue an amethyst with magiky powers!";
  public static final String information = "Convert an amethyst into a powered amethyst";
  private static final int energyRequirement = 50;

  public ConvertAmethystRitual() {
    super(displayName, description, information, energyRequirement);
  }

  @Override
  protected boolean canStart() {
    if (!super.canStart()) {
      return false;
    }

    LazyOptional<IItemHandler> itemHandler =
        altar.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);

    ItemStack[] stacks = new ItemStack[4];

    itemHandler.ifPresent((handler) -> {
      stacks[0] = handler.getStackInSlot(0);
      stacks[1] = handler.getStackInSlot(1);
      stacks[2] = handler.getStackInSlot(2);
      stacks[3] = handler.getStackInSlot(3);
    });

    for (int i = 0; i < stacks.length; i++) {
      if (stacks[i].getItem() == EvileDeferredRegistry.AMETHYST.get()) {
        return true;
      }
    }

    return false;
  }

  @Override
  public void begin() {
    GlyphNetworkingManager.CGlyph.send(PacketDistributor.ALL.noArg(),
        new GlyphMessageToClient(
            new ResourceLocation(MagiksMostEvile.MODID, "textures/items/general/amethyst.png"),
            GlyphOrientation.VERTICAL, altar.getPos().up(7), true, 0.5));
    super.begin();
  }

  @Override
  protected RitualResult tick() {
    super.tick();

    if (isBetweenTicks(1, 50, true)) {
      if (altar.removeEnergy(1)) {
        return RitualResult.CASTING;
      } else {
        return RitualResult.CATACLYSM;
      }
    }

    if (getTick() > 360) {
      return RitualResult.SUCCESS;
    }

    return RitualResult.CASTING;
  }

  @Override
  public ResultHandler<?> getResultHandler() {
    return new ConvertAmethystResultHandler(getAltar(), this);
  }

}
