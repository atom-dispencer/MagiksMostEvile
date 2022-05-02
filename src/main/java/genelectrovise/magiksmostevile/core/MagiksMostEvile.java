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
package genelectrovise.magiksmostevile.core;

import genelectrovise.magiksmostevile.config.Config;
import genelectrovise.magiksmostevile.config.ForgeConfigGuiScreen;
import genelectrovise.magiksmostevile.config.PrimitiveWidgetFactory;
import genelectrovise.magiksmostevile.core.setup.RecipeSetup;
import genelectrovise.magiksmostevile.core.setup.RegistryCreationManager;
import genelectrovise.magiksmostevile.entity.EntityAttributeManager;
import genelectrovise.magiksmostevile.network.pixiecourier.PixieCourier;
import genelectrovise.magiksmostevile.particle.ParticleClientStartup;
import genelectrovise.magiksmostevile.registry.orbital.OrbitalRegistryGenerator;
import genelectrovise.magiksmostevile.world.noisyore.NoisyOreConfiguration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

@Mod(MagiksMostEvile.MODID)
@Mod.EventBusSubscriber(modid = MagiksMostEvile.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MagiksMostEvile {
    public static final Logger LOGGER = LogManager.getLogger(MagiksMostEvile.class);
    public static final String MODID = "magiksmostevile";
    public static final String VERSION = "alpha-1.0";
    public static final String NAME = "Magiks Most Evile";
    public static final String ACCEPTED_VERSIONS = "1.16.4";

    public static IEventBus MOD_EVENT_BUS;
    public static IEventBus FORGE_EVENT_BUS;

    public MagiksMostEvile() {

        // Hello there!
        LOGGER.info("Magiks Most Evile is being loaded by FML");

        // Get event bus instances
        MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();
        FORGE_EVENT_BUS = MinecraftForge.EVENT_BUS;

        // Register config file
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_SPEC);

        // Register event listeners
        registerCommonEvents();

        // Handle orbital registries
        new OrbitalRegistryGenerator(OrbitalRegistryGenerator.REFLECTIONS_CONFIGURATION).collectOrbitalRegistries();

    }

    @SubscribeEvent
    public static void createRegistries(RegistryEvent.NewRegistry event) {
        LOGGER.debug("Creating MME registries");
        new RegistryCreationManager().create();
    }

    public static void registerCommonEvents() {
        LOGGER.debug("Registering MagiksMostEvile common events");
        MOD_EVENT_BUS.register(EntityAttributeManager.class);
        MOD_EVENT_BUS.register(RecipeSetup.class);
        MOD_EVENT_BUS.addListener(PixieCourier::onCommonSetupEvent);
        FORGE_EVENT_BUS.addListener(PixieCourier::onClientJoinServer_requestCourierHashPacket);
        FORGE_EVENT_BUS.addListener(NoisyOreConfiguration::onResourceManagerReload);
    }

    @SubscribeEvent
    public static void registerClientEvents(final FMLClientSetupEvent event) {
        LOGGER.debug("Registering MME client only events");
        MOD_EVENT_BUS.register(ParticleClientStartup.class);

        // Register ExtensionPoint<T> of type BiFunction<Minecraft, Screen, Screen>>
        // Factory supplies a BiFunction<Minecraft, Screen, Screen>>
        // ForgeConfigGuiScreen takes Minecraft, Screen. () -> ((mc, sc) -> new)
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (mc, sc) -> new ForgeConfigGuiScreen(new PrimitiveWidgetFactory()));
    }

    @SubscribeEvent
    public static void processIMC(final InterModProcessEvent event) {
        LOGGER.info(MagiksMostEvile.MODID + " recieved IMC {}", event
                .getIMCStream()
                .map(m -> m
                        .getMessageSupplier()
                        .get())
                .collect(Collectors.toList()));
    }

    @SubscribeEvent
    public static void enqueueIMC(final InterModEnqueueEvent event) {
        InterModComms.sendTo("examplemod", "helloworld", () -> {
            LOGGER.info("MagiksMostEvile Inter-Mod Communications is active");
            return "Success";
        });
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        LOGGER.info("MagiksMostEvile is starting on the server...");
    }

}
