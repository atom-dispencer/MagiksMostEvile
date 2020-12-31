package genelectrovise.magiksmostevile.common.world.gen.structure;

import genelectrovise.magiksmostevile.common.core.registry.orbital.registries.StructureFeatureHolder;

/**
 * Used in structure orbital registry! Holds references as a pair!
 * 
 * @author GenElectrovise
 *
 */
public class StructureAndFeaturesHolder {

  private StructureHolder structure;
  private StructureFeatureHolder[] features;

  public StructureAndFeaturesHolder(StructureHolder structureHolder,
      StructureFeatureHolder... structureFeatureHolders) {
    this.structure = structureHolder;
    this.features = structureFeatureHolders;
  }

  public StructureHolder getStructure() {
    return structure;
  }

  public StructureFeatureHolder[] getFeatures() {
    return features;
  }
}
