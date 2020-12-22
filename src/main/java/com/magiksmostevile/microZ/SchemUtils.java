package com.magiksmostevile.microZ;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockStairs;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.io.InputStream;

/**
 * Created by multimote on 05.07.14.
 */
public class SchemUtils {
    public Schematic get(String res){
        try {
        	System.out.println("We're trying!");
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("assets/magiksmostevile/schem/" + res);
            if(is==null){
            	System.out.println("What Schematic?");
            	return null;
            }
            
        	System.out.println("Found InputStream!");

            NBTTagCompound nbtdata = CompressedStreamTools.readCompressed(is);
            short width = nbtdata.getShort("Width");
            short height = nbtdata.getShort("Height");
            short length = nbtdata.getShort("Length");
            
        	System.out.println("NBTData: " + nbtdata.toString());

        	
            byte[] blocks = nbtdata.getByteArray("Blocks");
            byte[] data = nbtdata.getByteArray("Data");
            
            System.out.println("schem size:" + width + " x " + height + " x " + length);
            NBTTagList tileentities = nbtdata.getTagList("TileEntities", 10);
            is.close();
            
        	System.out.println("About to return schematic!");

            return new Schematic(tileentities, width, height, length, blocks, data);
        } catch (Exception e) {
        	System.out.println("Exception! Stacktrace incoming!");
        	e.printStackTrace();

      	
            return null;
        }
    }

    public class Schematic{ // <3
        public  NBTTagList tileentities;
        public  short width;
        public  short height;
        public short length;
        public byte[] blocks;
        public byte[] data;
        public Schematic(NBTTagList tileentities, short width, short height, short length, byte[] blocks, byte[] data){
            this.tileentities = tileentities;
            this.width = width;
            this.height = height;
            this.length = length;
            this.blocks = blocks;
            this.data = data;
        }

    }

    public class rotations{
        public int[] rs;
        public rotations(int... r){
            rs = r;
        }
        public int getSide(int meta){
            for(int i = 0; i<rs.length; i++)
                if(rs[i]==meta)return i;
            return -1;
        }

        public int getMeta(int side){
            return rs[side];
        }
    }



    public int[] blockCoordsRotation(int blockX, int blockZ, int rotation){
        int[] coords={blockX, blockZ};
        switch (rotation){
            case 1:  coords[0]=-blockZ; coords[1]=blockX; break;
            case 2:  coords[0]=-blockX; coords[1]=-blockZ; break;
            case 3:  coords[0]=blockZ; coords[1]=-blockX; break;
        }
        return coords;
    }

    public rotations signRotations = new rotations(2,5,3,4);
    public rotations stairsRotations = new rotations(2,1,3,0);
    public rotations chestRotations = new rotations(4,2,5,3);
    public rotations pumpkinRotations = new rotations(2,3,0,1);
    public rotations torchRotations = new rotations(4,1,3,2);

    public rotations doorRotations = new rotations(1,2,3,0);

    public int rotateMeta(int blockId, int meta, int rotation){ //todo rewrite this 

        if(rotation>0) {
            if(Block.getIdFromBlock(Blocks.torch)==blockId || Block.getIdFromBlock(Blocks.redstone_torch)==blockId)
                return torchRotations.getMeta( (torchRotations.getSide(meta)+rotation)%4 );

            if(meta<4 && Block.getBlockById(blockId) instanceof BlockDoor)
                return doorRotations.getMeta( (doorRotations.getSide(meta)+rotation)%4 );

            if(Block.getIdFromBlock(Blocks.wall_sign)==blockId)
                return signRotations.getMeta( (signRotations.getSide(meta)+rotation)%4 );

            if(Block.getIdFromBlock(Blocks.ladder)==blockId)
                return signRotations.getMeta( (signRotations.getSide(meta)+rotation)%4 );

            if(Block.getIdFromBlock(Blocks.chest)==blockId || Block.getIdFromBlock(Blocks.ender_chest)==blockId)
                return chestRotations.getMeta( (chestRotations.getSide(meta)+rotation)%4 );

            if(Block.getIdFromBlock(Blocks.furnace)==blockId || Block.getIdFromBlock(Blocks.lit_furnace)==blockId)
                return signRotations.getMeta( (signRotations.getSide(meta)+rotation)%4 );

            if(Block.getIdFromBlock(Blocks.pumpkin)==blockId || Block.getIdFromBlock(Blocks.lit_pumpkin)==blockId)
                return pumpkinRotations.getMeta( (pumpkinRotations.getSide(meta)+rotation)%4 );

            if(Block.getBlockById(blockId) instanceof BlockStairs)
                return stairsRotations.getMeta((stairsRotations.getSide(meta)+rotation)%4);
        }

        return meta;
    }
}







/* HOW TO USE ON ITEMUSE
public boolean onItemUse(ItemStack is, EntityPlayer placer, World world, int x, int y, int z, int side, float px, float py, float pz) {

if(!world.isRemote && !blocked && delay<=0 && side == 1 && placer.capabilities.isCreativeMode){
blocked = true;
delay = 20;
int rotation = OtherUtils.getPlayerRotationSide(placer);
SchemUtils.Schematic sh = sut.get(schematic);
if(sh==null){
    placer.addChatMessage(new ChatComponentText("Schematic is dead!"));
    this.setUnlocalizedName("builder_corrupt");
return false;}

if(logBuilding)placer.addChatMessage(new ChatComponentText("Building started."));

int i = 0;
for(int sy = 0; sy < sh.height; sy++)
for(int sz = 0; sz < sh.length; sz++)
for(int sx = 0; sx < sh.width; sx++){

        Block b = Block.getBlockById(sh.blocks[i]);
        if(b!= Blocks.air)
        {
            int rx = SchemUtils.blockCoordsRotation(sx - this.getxShift(), sz, rotation)[0];
            int rz = SchemUtils.blockCoordsRotation(sx - this.getxShift(), sz, rotation)[1];
            world.setBlockToAir(x + rx, y + ylevel + sy, z + rz);
            world.setBlock(x+rx, y+ylevel+sy, z+rz, b, SchemUtils.rotateMeta(sh.blocks[i], sh.data[i], rotation ), 2);
        }
        i++;
}

if (sh.tileentities != null)
{
    for (int i1 = 0; i1 < sh.tileentities.tagCount(); ++i1)
    {
        NBTTagCompound nbttagcompound4 = sh.tileentities.getCompoundTagAt(i1);
        TileEntity tileentity = TileEntity.createAndLoadEntity(nbttagcompound4);

        if (tileentity != null)
        {
            int[] conv2 = SchemUtils.blockCoordsRotation(tileentity.xCoord - this.getxShift(), tileentity.zCoord, rotation);
            tileentity.xCoord = x + conv2[0];
            tileentity.yCoord += y+ylevel;
            tileentity.zCoord = z + conv2[1];
            world.setTileEntity(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord, tileentity);
        }
    }
}

if(logBuilding) placer.addChatMessage(new ChatComponentText("Building finished."));
blocked = false;
return true;
}
return false;
}
*/