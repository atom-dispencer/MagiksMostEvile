package genelectrovise.magiksmostevile.network.pixiecourier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PixieProcessorTest {

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetInstance() {
    PixieProcessor.Registry registry = PixieProcessor.Registry.getInstance();
    assertNotNull(registry);
  }

  @Test
  void testRegister() throws CourierException {
    PixieProcessor.Registry registry = PixieProcessor.Registry.getInstance();
    PixieProcessor processor = Mockito.mock(PixieProcessor.class);
    registry.register(String.class, processor);
  }

  @Test
  void testRegister_throwsCourier_whenNoType() throws CourierException {
    assertThrows(CourierException.class, () -> {
      PixieProcessor.Registry registry = PixieProcessor.Registry.getInstance();
      PixieProcessor processor = Mockito.mock(PixieProcessor.class);
      registry.register(null, processor);
    });
  }

  @Test
  void testRegister_throwsCourier_whenNoProcessor() throws CourierException {
    assertThrows(CourierException.class, () -> {
      PixieProcessor.Registry registry = PixieProcessor.Registry.getInstance();
      registry.register(String.class, null);
    });
  }

  @Test
  void testGet() throws CourierException {
    PixieProcessor.Registry registry = PixieProcessor.Registry.getInstance();
    PixieProcessor expected_processor = Mockito.mock(PixieProcessor.class);
    registry.processors.put(String.class, expected_processor);
    PixieProcessor actual_processor = registry.get(String.class);
    assertEquals(expected_processor, actual_processor);
  }

  @Test
  void testGet_throwsCourier_whenNoType() throws CourierException {
    assertThrows(CourierException.class, () -> {
      PixieProcessor.Registry registry = PixieProcessor.Registry.getInstance();
      PixieProcessor expected_processor = Mockito.mock(PixieProcessor.class);
      registry.processors.put(String.class, expected_processor);
      PixieProcessor actual_processor = registry.get(null); // No type
      assertEquals(expected_processor, actual_processor);
    });
  }
}
