package genelectrovise.magiksmostevile.registry.orbital

import org.junit.jupiter.api.Test
import org.mockito.Mockito

class OrbitalRegistryHandlerTest {

    @Test
    fun whens() {
        val generator = Mockito.mock(OrbitalRegistryHandler::class.java)
        Mockito.`when`(generator.generateOrbitals()).thenCallRealMethod()

        generator.
    }
}
