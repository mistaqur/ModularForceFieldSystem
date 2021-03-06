package chb.mods.mffs.common;

import ic2.api.Items;

import java.util.List;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Slot;
import net.minecraft.src.World;


public class ItemForcicumCell extends ItemMFFSBase {
	
	private boolean ic = false;
	private boolean aktiv =  false;
	
	protected ItemForcicumCell(int id) {
		super(id);
		setIconIndex(98);
		setMaxStackSize(1);
		setMaxDamage(100);
		
		if(Items.getItem("matter") != null)
		{
			ic = true;
		}
		
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
	public String getTextureFile() {
		return "/chb/mods/mffs/sprites/items.png";
	}

	
	
	public int getItemDamage(ItemStack itemStack)
	{
		return 101-((getForceciumlevel(itemStack)*100)/getMaxForceciumlevel());
		
	}
	
    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5)
    {
    	
		if (world.isRemote==false)
			{
			
		if(aktiv)
		{
    	
    	if(getForceciumlevel(itemStack) < getMaxForceciumlevel())
    	{
        	if(entity instanceof EntityPlayer)
        	{
        	
    		List<Slot> slots = ((EntityPlayer)entity).inventorySlots.inventorySlots;
    		for (Slot slot : slots) {
    			if (slot.getStack() != null) {
    				if (slot.getStack().getItem() == ModularForceFieldSystem.MFFSitemForcicium) {
    					
    					setForceciumlevel(itemStack,getForceciumlevel(itemStack)+1);

    					if(slot.getStack().stackSize > 1)
    					{
       					 ItemStack forcecium= new ItemStack(ModularForceFieldSystem.MFFSitemForcicium, slot.getStack().stackSize-1);
       					 slot.putStack(forcecium);
    					}else{
    					
    						slot.putStack(null);
    						
    					}
    					break;
    					
    				}
    				
    			
    				if(ic)
    				{
    			    if (slot.getStack().getItem() == Items.getItem("matter").getItem()) {
    			    
    			    	if(getForceciumlevel(itemStack) <= 995)
    			    	{	
    			    	setForceciumlevel(itemStack,getForceciumlevel(itemStack)+5);
    			    	
       					if(slot.getStack().stackSize > 1)
    					{
       					 ItemStack matter= new ItemStack(Items.getItem("matter").getItem(), slot.getStack().stackSize-1);
       					 slot.putStack(matter);
    					}else{
    					
    						slot.putStack(null);
    						
    					}
    			    	}
       					break;
    			    }
    				}
    				
    				
    				
    				
    			}
    		}
        	}
    	
    		
    	}
    	
    	itemStack.setItemDamage(getItemDamage(itemStack));
    }
	}
    }
	
    @Override
    public void addInformation(ItemStack itemStack,EntityPlayer player, List info,boolean b)
    {
        String tooltip = String.format( "%d / %d  Forcicum  ",getForceciumlevel(itemStack),getMaxForceciumlevel());
        info.add(tooltip);
    }
    
    
    public  boolean useForcecium(int count,ItemStack itemstack)
    {
    	if(count > getForceciumlevel(itemstack))
    	{
    		return false;
    	}else{
    		
    		setForceciumlevel(itemstack,getForceciumlevel(itemstack)-count);
    		return true;
    	}

    }
	
	public  int getMaxForceciumlevel() {
		
		return 1000;
	}
	
	
    public   void setForceciumlevel(ItemStack itemStack, int  Forceciumlevel)
    {
       
       NBTTagCompound nbtTagCompound = NBTTagCompoundHelper.getTAGfromItemstack(itemStack);
       nbtTagCompound.setInteger("Forceciumlevel", Forceciumlevel);

    }

 
    public  int getForceciumlevel(ItemStack itemstack)
    {
   
    	NBTTagCompound nbtTagCompound = NBTTagCompoundHelper.getTAGfromItemstack(itemstack);
    	if(nbtTagCompound != null)
    	{
    		return nbtTagCompound.getInteger("Forceciumlevel");
    	}
       return 0;
    }
    
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world,
			EntityPlayer entityplayer) {
		if (world.isRemote==false)
		{
       if(!aktiv)
       {
    	   aktiv= true;
    	   entityplayer.addChatMessage("[Forcicum Cell] Active");
       }else{
    	   aktiv= false;
    	   entityplayer.addChatMessage("[Forcicum Cell] Inactive");
       }
		
		
		
		}
		return itemstack;
	}
	
	
}
