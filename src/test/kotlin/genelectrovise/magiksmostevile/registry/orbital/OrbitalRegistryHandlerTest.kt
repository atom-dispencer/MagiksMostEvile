package genelectrovise.magiksmostevile.registry.orbital

import org.junit.jupiter.api.Test
import org.mockito.Mockito

class OrbitalRegistryHandlerTest {

    @Test
    fun when_getClassOrbitalRegistryMap_thenReturn () {
        val handler = Mockito.mock(OrbitalRegistryHandler::class.java)
        Mockito.`when`(handler.mapOrbitalsToClasses(Mockito.any())).thenCallRealMethod()

        handler.
    }
}
