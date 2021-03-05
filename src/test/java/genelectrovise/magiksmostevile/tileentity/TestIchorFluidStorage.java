package genelectrovise.magiksmostevile.tileentity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

public class TestIchorFluidStorage {

  @Mock
  private FluidStack fluid;
  @InjectMocks
  private IchorFluidStorage storage = new IchorFluidStorage(100, "test_ichor_fluid_storage");

  @Test
  public void whenStorageContainsNothing_thenExtractNothing() {
    assertEquals(0, storage.drain(10, FluidAction.SIMULATE), "Fluid was extracted from the tank even though it contained nothing!");
  }
}
