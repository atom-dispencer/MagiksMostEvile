package genelectrovise.magiksmostevile.network.pixiecourier;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
    void testRegister_throwsCourier_whenNoType() {
        assertThrows(CourierException.class, () -> {
            PixieProcessor.Registry registry = PixieProcessor.Registry.getInstance();
            PixieProcessor processor = Mockito.mock(PixieProcessor.class);
            registry.register(null, processor);
        });
    }

    @Test
    void testRegister_throwsCourier_whenNoProcessor() {
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
    void testGet_throwsCourier_whenNullType() {
        assertThrows(CourierException.class, () -> {
            PixieProcessor.Registry registry = PixieProcessor.Registry.getInstance();
            PixieProcessor expected_processor = Mockito.mock(PixieProcessor.class);
            registry.processors.put(String.class, expected_processor);
            PixieProcessor actual_processor = registry.get(null); // No type
            assertEquals(expected_processor, actual_processor);
        });
    }

    @Test
    void testGet_throwsCourier_whenNullProcessorReturned() {
        assertThrows(CourierException.class, () -> {
            PixieProcessor.Registry registry = Mockito.mock(PixieProcessor.Registry.class);
            when(registry.n_get(any())).thenReturn(null); // Null processor
            when(registry.get(any())).thenCallRealMethod();

            registry.get(String.class); // Should throw Courier
        });
    }

    @Test
    void testN_Get() {
        PixieProcessor.Registry registry = Mockito.mock(PixieProcessor.Registry.class);
        Map<Class<?>, PixieProcessor> map = Maps.newHashMap();
        map.put(PixieProcessor.class, PixieProcessor.DO_NOTHING);

        when(registry.getProcessors()).thenReturn(map);
        when(registry.n_get(any())).thenCallRealMethod();

        PixieProcessor gotten = registry.n_get(PixieProcessor.class);
        assertEquals(PixieProcessor.DO_NOTHING, gotten);
    }
}
