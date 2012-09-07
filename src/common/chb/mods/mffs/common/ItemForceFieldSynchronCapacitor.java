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

import java.util.List;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;
import ic2.api.ElectricItem;
import ic2.api.IElectricItem;

public class ItemForceFieldSynchronCapacitor extends Item implements IElectricItem{
	public ItemForceFieldSynchronCapacitor(int i) {
		super(i);
		setMaxStackSize(1);
		setIconIndex(4);
		setMaxDamage(27);
		setTabToDisplayOn(CreativeTabs.tabMaterials);
	}
	@Override
	public String getTextureFile() {
		return "/chb/mods/mffs/sprites/items.png";
	}
	@Override
	public boolean isRepairable() {
		return false;
	}

	@Override
	public boolean isDamageable()
	{
	return true;
	}

	@Override
	public boolean canProvideEnergy() {
		return false;
	}
	@Override
	public int getChargedItemId() {
		return this.shiftedIndex;
	}

    @Override
    public void addInformation(ItemStack itemStack, List info)
    {
        String tooltip = String.format( "%d EU/%d EU ",ElectricItem.discharge(itemStack, getMaxCharge(), 1, true, true),getMaxCharge());
        info.add(tooltip);
    }

	public static boolean canuseBatpack(ItemStack itemStack)
	{
    	NBTTagCompound nbtTagCompound = NBTTagCompoundHelper.getTAGfromItemstack(itemStack);
    	if(nbtTagCompound != null)
    	{
    		return nbtTagCompound.getBoolean("Batpack");
    	}
		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world,
			EntityPlayer entityplayer) {
	if(!world.isRemote)
	{
    	NBTTagCompound nbtTagCompound = NBTTagCompoundHelper.getTAGfromItemstack(itemstack);
    	if(nbtTagCompound != null)
    	{
    		if(nbtTagCompound.getBoolean("Batpack"))
    		{
    			nbtTagCompound.setBoolean("Batpack", false);
    			entityplayer.addChatMessage("[Synchron Capacitor] ignore batpack/lappack");
    		}else{
    			nbtTagCompound.setBoolean("Batpack", true);
    			entityplayer.addChatMessage("[Synchron Capacitor] can use batpack/lappack");
    		}
    	}
	}
		return itemstack;
	}

	@Override
	public int getEmptyItemId() {
		return this.shiftedIndex;
	}
	@Override
	public int getMaxCharge() {
		return ModularForceFieldSystem.forcefieldtransportcost*10;
	}
	@Override
	public int getTier() {
		return 1;
	}
	@Override
	public int getTransferLimit() {
		return 1000;
	}
}
