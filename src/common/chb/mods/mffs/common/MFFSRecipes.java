package chb.mods.mffs.common;

import cpw.mods.fml.common.registry.GameRegistry;
import ic2.api.ExplosionWhitelist;
import ic2.api.Ic2Recipes;
import ic2.api.Items;
import net.minecraft.src.Block;
import net.minecraft.src.CraftingManager;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class MFFSRecipes {

	

	public static void init()
	{
		
		OreDictionary.registerOre("ForciciumItem", ModularForceFieldSystem.MFFSitemForcicium);
		OreDictionary.registerOre("MonazitOre", ModularForceFieldSystem.MFFSMonazitOre);
		
		RecipesFactory.addRecipe("uuuuiuuuu", 1, 0, null, ModularForceFieldSystem.MFFSitemForcePowerCrystal);
		RecipesFactory.addRecipe("vvvvvvvvv", 1, 0, null, ModularForceFieldSystem.MFFSProjectorFFStrenght);
		RecipesFactory.addRecipe("vvv   vvv", 1, 0, null, ModularForceFieldSystem.MFFSProjectorFFDistance);
		
		CraftingManager.getInstance().addShapelessRecipe(new ItemStack(ModularForceFieldSystem.MFFSitemcardempty),new Object[] { new ItemStack(ModularForceFieldSystem.MFFSitemfc) });
		CraftingManager.getInstance().addShapelessRecipe(new ItemStack(ModularForceFieldSystem.MFFSitemcardempty),new Object[] { new ItemStack(ModularForceFieldSystem.MFFSItemIDCard) });
		CraftingManager.getInstance().addShapelessRecipe(new ItemStack(ModularForceFieldSystem.MFFSitemcardempty),new Object[] { new ItemStack(ModularForceFieldSystem.MFFSItemSecLinkCard) });

		GameRegistry.addSmelting(ModularForceFieldSystem.MFFSMonazitOre.blockID, new ItemStack(ModularForceFieldSystem.MFFSitemForcicium, 5), 0.5F);
	
	if(ModularForceFieldSystem.ic2found){
		
		Ic2Recipes.addMaceratorRecipe(new ItemStack(ModularForceFieldSystem.MFFSMonazitOre, 1), new ItemStack(ModularForceFieldSystem.MFFSitemForcicium, 10));
		Ic2Recipes.addMatterAmplifier(new ItemStack(ModularForceFieldSystem.MFFSitemForcicium, 1), 50000);
		
		RecipesFactory.addRecipe(" RRR   R ", 8, 1, null, ModularForceFieldSystem.MFFSitemForcicium);
		RecipesFactory.addRecipe("AAAAxAADA", 1, 1, null, ModularForceFieldSystem.MFFSitemForcicumCell);
		RecipesFactory.addRecipe(" E EBE E ", 4, 1, null, ModularForceFieldSystem.MFFSitemupgradeexctractorboost);
		RecipesFactory.addRecipe(" E ExE E ", 1, 1, null, ModularForceFieldSystem.MFFSitemupgradecapcap);
		RecipesFactory.addRecipe("HHHEIEEDE", 1, 1, null, ModularForceFieldSystem.MFFSitemupgradecaprange);
		RecipesFactory.addRecipe("AlAlilAlA", 64, 1, null, ModularForceFieldSystem.MFFSitemFocusmatix);
		RecipesFactory.addRecipe("ooooCoooo", 1, 1, null, ModularForceFieldSystem.MFFSitemcardempty);
		RecipesFactory.addRecipe("mSnExEEDE", 1, 1, null, ModularForceFieldSystem.MFFSitemWrench);
	}

	if(ModularForceFieldSystem.uefound){
		
		RecipesFactory.addRecipe("AAAAxAAHA", 1, 2, null, ModularForceFieldSystem.MFFSitemForcicumCell);
		RecipesFactory.addRecipe(" C CGC C ", 9, 2, null, ModularForceFieldSystem.MFFSitemupgradeexctractorboost);
		RecipesFactory.addRecipe(" C CxC C ", 1, 2, null, ModularForceFieldSystem.MFFSitemupgradecapcap);
		RecipesFactory.addRecipe("NNNCICCEC", 1, 2, null, ModularForceFieldSystem.MFFSitemupgradecaprange);
		RecipesFactory.addRecipe("BlBlilBlB", 64, 2, null, ModularForceFieldSystem.MFFSitemFocusmatix);
		RecipesFactory.addRecipe("ooooEoooo", 1, 2, null, ModularForceFieldSystem.MFFSitemcardempty);
		RecipesFactory.addRecipe("mOnDxDDED", 1, 2, null, ModularForceFieldSystem.MFFSitemWrench);
	}
	
	
		
	}
	
}