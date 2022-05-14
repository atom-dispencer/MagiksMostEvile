package genelectrovise.magiksmostevile.registry.orbital

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.internal.matchers.Or

class OrbitalRegistryHandlerTest {

    val clazz: Class<*> = String::class.java
    val orbital: OrbitalRegistry = Mockito.mock(OrbitalRegistry::class.java)

    @Test
    fun when_instantiateAndRegisterOrbitalFails_throwsOrbitalRegistryException() {
        val handler = Mockito.mock(OrbitalRegistryHandler::class.java)
        Mockito.`when`(handler.instantiateAndRegisterOrbital(Mockito.any(), Mockito.any())).thenCallRealMethod()
        Mockito.`when`(handler.getOrbitalRegistryInstance(Mockito.any(), Mockito.any()))
            .thenThrow(Exception("An intentional testing Exception occurred!"))

        assertThrows<OrbitalRegistryException> { handler.instantiateAndRegisterOrbital(clazz, orbital) }
    }

    @Test
    fun when_getOrbitalRegistryInstance_returnIsInstanceOfInput() {
        val handler = Mockito.mock(OrbitalRegistryHandler::class.java)
        Mockito.`when`(handler.getOrbitalRegistryInstance(Mockito.any(), Mockito.any())).thenCallRealMethod()

        val instance = handler.getOrbitalRegistryInstance(clazz, orbital)
        assert(clazz.isAssignableFrom(instance.javaClass)) { "$clazz is not a supertype of $instance" }
    }
}
