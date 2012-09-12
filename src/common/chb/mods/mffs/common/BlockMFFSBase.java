/*  
    Copyright (C) 2012 Thunderdark

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    
    Contributors:
    Thunderdark - initial implementation
*/

package chb.mods.mffs.common;

import java.util.Random;

import net.minecraft.src.BlockContainer;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public abstract class BlockMFFSBase extends BlockContainer {
	private int blockid;
	private int texturindex;

	public BlockMFFSBase(int i, int texturindex) {
		super(i, Material.iron);
		this.texturindex = texturindex;
		blockid = i;
		setHardness(25F);
		setResistance(25F);
		setStepSound(soundMetalFootstep);
		setRequiresSelfNotify();
		setCreativeTab(CreativeTabs.tabBlock);
	}

	public abstract Integer getGui(World world, int i, int j, int k,
			EntityPlayer entityplayer);

	@Override
	public abstract TileEntity createNewTileEntity(World world);

	@Override
	public abstract String getTextureFile();

	@Override
	public abstract boolean onBlockActivated(World world, int i, int j, int k,EntityPlayer entityplayer, int par6, float par7, float par8, float par9);

	@Override
	public void onBlockAdded(World world, int i, int j, int k) {
		TileEntity tileEntity = world.getBlockTileEntity(i, j, k);

		if(tileEntity instanceof TileEntityCapacitor)
		{
			((TileEntityCapacitor)tileEntity).addtogrid();
		}
		if(tileEntity instanceof TileEntityAreaDefenseStation)
		{
			((TileEntityAreaDefenseStation)tileEntity).addtogrid();
		}
		if(tileEntity instanceof TileEntityProjector)
		{
			((TileEntityProjector)tileEntity).addtogrid();
		}
		if(tileEntity instanceof TileEntitySecurityStation)
		{
			((TileEntitySecurityStation)tileEntity).addtogrid();
		}
		if(tileEntity instanceof TileEntityExtractor)
		{
			((TileEntityExtractor)tileEntity).addtogrid();
		}
	}
	@Override
	public void breakBlock(World world, int i, int j, int k,int a,int b) {
		TileEntity tileEntity = world.getBlockTileEntity(i, j, k);

		if(tileEntity instanceof TileEntityCapacitor)
		{
			((TileEntityCapacitor)tileEntity).removefromgrid();
		}
		if(tileEntity instanceof TileEntityAreaDefenseStation)
		{
			((TileEntityAreaDefenseStation)tileEntity).removefromgrid();
		}
		if(tileEntity instanceof TileEntityProjector)
		{
			((TileEntityProjector)tileEntity).removefromgrid();
		}
		if(tileEntity instanceof TileEntitySecurityStation)
		{
			((TileEntitySecurityStation)tileEntity).removefromgrid();
		}
		if(tileEntity instanceof TileEntityExtractor)
		{
			((TileEntityExtractor)tileEntity).removefromgrid();
		}
		
		world.removeBlockTileEntity(i, j, k);
	}

	@Override
	protected int damageDropped(int i) {
		return i;
	}

	public int idDropped(int i, Random random) {
		return blockID;
	}

	public static boolean isActive(IBlockAccess iblockaccess, int i, int j,
			int k) {
		TileEntity tileentity = iblockaccess.getBlockTileEntity(i, j, k);
		if (tileentity instanceof TileEntityMaschines) {
			return ((TileEntityMaschines) tileentity).isActive();
		} else {
			return false;
		}
	}
	@Override
	public int getBlockTexture(IBlockAccess iblockaccess, int i, int j, int k,
			int l) {
		TileEntity tileentity = iblockaccess.getBlockTileEntity(i, j, k);

		int facing = (tileentity instanceof TileEntityMaschines) ? ((TileEntityMaschines) tileentity)
				.getFacing() : 1;
		int typ = (tileentity instanceof TileEntityProjector) ? ((TileEntityProjector) tileentity)
				.getProjektor_Typ() : 0;

		if (isActive(iblockaccess, i, j, k)) {
			if (facing == l ) {
				return ((texturindex + typ) * 16) + 7 +1;
			}
			
			return ((texturindex + typ) * 16) + 7 ;
			
		} else {
			
			if (facing == l) {
				return ((texturindex + typ) * 16) +1;
			}
			
			return ((texturindex + typ) * 16);
		}
	}

	@Override
	public float getExplosionResistance(Entity entity,World world, int i, int j,
			int k, double d, double d1, double d2) {
		if (world.getBlockTileEntity(i, j, k) instanceof TileEntityMaschines) {
			TileEntity tileentity = world.getBlockTileEntity(i, j, k);
			if (((TileEntityMaschines) tileentity).isActive()) {
				return 999F;
			} else {
				return 25F;
			}
		}
		return 25F;
	}
}
