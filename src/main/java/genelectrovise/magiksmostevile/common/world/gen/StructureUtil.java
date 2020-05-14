package genelectrovise.magiksmostevile.common.world.gen;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

public class StructureUtil {
	public void placeStructure(ServerWorld serverworld, ChunkPos chunk_pos, ResourceLocation template_location) {
		// Gets the world's structure template manager
		TemplateManager templatemanager = serverworld.getStructureTemplateManager();
		// Sets default placement settings
		PlacementSettings placementsettings = (new PlacementSettings()).setIgnoreEntities(true)
				.setChunk((ChunkPos) null);
		// Gets the template and builds
		templatemanager.getTemplate(template_location).addBlocksToWorldChunk(serverworld, chunk_pos.asBlockPos(),
				placementsettings);
	}

	public void placeStructureWithPlacementConfig(ServerWorld serverworld, ChunkPos chunk_pos,
			ResourceLocation template_location) {
		// Gets the world's structure template manager
		TemplateManager templatemanager = serverworld.getStructureTemplateManager();
		// Sets default placement settings
		PlacementSettings placementsettings = (new PlacementSettings()).setIgnoreEntities(true)
				.setChunk((ChunkPos) null);
		// Gets the template and builds
		templatemanager.getTemplate(template_location).addBlocksToWorldChunk(serverworld, chunk_pos.asBlockPos(),
				placementsettings);
	}
}
