package genelectrovise.magiksmostevile.tileentity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import genelectrovise.magiksmostevile.core.support.TrackableIntegerHolder;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

public class TestIchorFluidStorage {

  @Mock
  private FluidStack fluid;
  
  @Mock
  private TrackableIntegerHolder maxIchor;
  
  @Mock
  private TrackableIntegerHolder currentIchor;
  
  private final String nbtKey = "ichor_fluid_storage";
  
  @InjectMocks
  private IchorFluidStorage storage;

  @BeforeAll
  void before() {
    storage.setNbtKey(nbtKey);
  }

  @Test
  void whenStorageContainsNothing_thenExtractNothing() {
    assertEquals(0, storage.drain(10, FluidAction.SIMULATE));
  }

}
