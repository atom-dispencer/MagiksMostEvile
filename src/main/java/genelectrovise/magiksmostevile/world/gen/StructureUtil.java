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
package genelectrovise.magiksmostevile.world.gen;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.template.*;
import net.minecraft.world.server.ServerWorld;

/**
 * {@link Biome}
 *
 * @author GenElectrovise
 */
public class StructureUtil {

    public static RuleEntry randomBlockReplacementRule(Block replaceThis, float chance,
                                                       Block replaceWith) {
        return new RuleEntry(new RandomBlockMatchRuleTest(replaceThis, chance),
                AlwaysTrueRuleTest.INSTANCE, replaceWith.defaultBlockState());
    }

    public void placeStructure(ServerWorld serverWorld, ChunkPos chunkPos,
                               ResourceLocation templateLocation) {

        // Gets the world's structure template manager
        TemplateManager templatemanager = serverWorld.getStructureManager();

        // Sets default placement settings
        PlacementSettings placementsettings =
                (new PlacementSettings()).setIgnoreEntities(true).setChunkPos((ChunkPos) null);

        // Gets the template and builds
        templatemanager.get(templateLocation).placeInWorld(serverWorld, chunkPos.getWorldPosition(), placementsettings, serverWorld.getRandom());

        // Alternatively use func_237152_b_ to avoid "placementSettings.setBoundingBoxFromChunk()"
    }

}
