package genelectrovise.magiksmostevile.common.world.gen;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

public class StructureUtil {
  
  public void placeStructure(ServerWorld serverWorld, ChunkPos chunkPos,
      ResourceLocation templateLocation) {

    // Gets the world's structure template manager
    TemplateManager templatemanager = serverWorld.getStructureTemplateManager();

    // Sets default placement settings
    PlacementSettings placementsettings =
        (new PlacementSettings()).setIgnoreEntities(true).setChunk((ChunkPos) null);

    // Gets the template and builds
    templatemanager.getTemplate(templateLocation).func_237144_a_(serverWorld, chunkPos.asBlockPos(), placementsettings, serverWorld.getRandom());
    
    //Alternatively use func_237152_b_ to avoid "placementSettings.setBoundingBoxFromChunk()"
  }
  
}
