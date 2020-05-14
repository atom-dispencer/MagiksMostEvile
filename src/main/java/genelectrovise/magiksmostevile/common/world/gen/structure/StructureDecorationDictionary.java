package genelectrovise.magiksmostevile.common.world.gen.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;

import genelectrovise.magiksmostevile.common.main.Main;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

/**
 * Stores the keys and values of {@link Block} that can be replaced and the
 * {@link BlockReplacementList} of {@link Block} they can be replaced with.
 * 
 * @author GenElectrovise 12 May 2020
 */
public class StructureDecorationDictionary extends HashMap<Block, BlockReplacementList> {
	private static final long serialVersionUID = 1L;
	protected Random random;
	protected IWorld world;

	/**
	 * 
	 */
	public StructureDecorationDictionary(Random random, IWorld world) {
		this.random = random;
		this.world = world;
	}

	/**
	 * Uses <code>super.put(key, value)</code> to add to the extended
	 * {@link TreeMap}, but returns this object instead to make this chainable.
	 * 
	 * @param key
	 * @param value
	 * @return this
	 */
	public StructureDecorationDictionary add(Block key, BlockReplacementList value) {
		super.put(key, value);
		return this;
	}

	/**
	 * If the {@link Block} of the given {@link BlockState} is a key in this
	 * {@link TreeMap}, replace it using the bound {@link BlockReplacementList}.
	 * 
	 * @param state
	 * @return If success, the new {@link BlockState}. If failed, the old
	 *         {@link BlockState}
	 */
	public BlockState decorate(BlockState state, BlockPos pos) {

		BlockReplacementList list = get(state.getBlock());

		// Make a list of all of the weights
		ArrayList<Integer> weights = new ArrayList<Integer>();
		for (BlockWeightPair pair : list) {
			weights.add(pair.weight);
		}

		// Add all of the weights together
		int totalWeight = 0;
		for (BlockWeightPair pair : list) {
			totalWeight = totalWeight + pair.weight;
		}

		int selection = 0;
		// decrWeight = totalWeight
		// While decrWeight is greater than 0:
		// set decrWeight equal to itself minus the weight of the previous selection.
		// then increment the selection
		int selectedWeight = random.nextInt(totalWeight + 1);
		for (int decrementedWeight = selectedWeight; decrementedWeight > 0; selection++) {
			decrementedWeight = decrementedWeight - list.get(selection).weight;
		}

		Main.LOGGER.debug("selection=" + selection);

		if (selection < 1) {
			selection = 1;
		}

		BlockState newState;
		/*
		 * if (state.getBlock() instanceof StairsBlock) {
		 * Main.LOGGER.debug("Is a StairsBlock!"); newState = list.get(selection -
		 * 1).block.getDefaultState().with(StairsBlock.FACING,
		 * state.get(StairsBlock.FACING)); } else {
		 * Main.LOGGER.debug("Is not a StairsBlock"); newState = list.get(selection -
		 * 1).block.getDefaultState(); }
		 */
		BlockWeightPair pair = list.get(selection - 1);
		newState = pair.block.getDefaultState();

		return newState;
	}

	/**
	 * @param state
	 * @return The {@link BlockState} of the handled chest.
	 */
	protected BlockState handleChest(BlockState state, BlockPos pos) {
		return state;
	}

}