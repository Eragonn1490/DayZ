package dayz.common;

import java.util.Random;

import dayz.common.blocks.EnumChestType;

import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntityChest;
import net.minecraft.src.TileEntityDispenser;
import net.minecraft.src.WeightedRandom;
import net.minecraft.src.WeightedRandomItem;
import net.minecraftforge.common.ChestGenHooks;

public class WeightedRandomChestDayZ extends WeightedRandomItem
{
    /** The Item/Block ID to generate in the Chest. */
    private int theItemId;

    /** The Item damage/metadata. */
    private int theItemDamage;

    /** The minimum chance of item generating. */
    public int theMinimumChanceToGenerateItem;

    /** The maximum chance of item generating. */
    public int theMaximumChanceToGenerateItem;
    
    public final ItemStack itemStack;

    public WeightedRandomChestDayZ(int par1, int par2, int par3, int par4, int par5)
    {
        super(par5);
        this.theItemId = par1;
        this.theItemDamage = par2;
        this.theMinimumChanceToGenerateItem = par3;
        this.theMaximumChanceToGenerateItem = par4;
        itemStack = new ItemStack(par1, 1, par2);
    }
    
    public WeightedRandomChestDayZ(ItemStack stack, int min, int max, int weight)
    {
        super(weight);
        itemStack = stack;
        theMinimumChanceToGenerateItem = min;
        theMaximumChanceToGenerateItem = max;
    }
    /**
     * Generates the Chest contents.
     */
    public static void generateChestContents(Random par0Random, EnumChestType chestType, TileEntityChest par2TileEntityChest, int par3)
    {
    	WeightedRandomChestDayZ[] par1ArrayOfWeightedRandomChestContent;
    	if (chestType == EnumChestType.ALL)
    	{
    		par1ArrayOfWeightedRandomChestContent = ChestHookRegistry.chestAllContents;
    	}
    	else if (chestType == EnumChestType.COMMON)
    	{
    		par1ArrayOfWeightedRandomChestContent = ChestHookRegistry.chestCommonContents;
    	}
    	else
    	{
    		par1ArrayOfWeightedRandomChestContent = ChestHookRegistry.chestRareContents;
    	}
    	
        for (int var4 = 0; var4 < par3; ++var4)
        {
            WeightedRandomChestDayZ var5 = (WeightedRandomChestDayZ)WeightedRandom.getRandomItem(par0Random, par1ArrayOfWeightedRandomChestContent);
            ItemStack[] stacks = ChestGenHooks.generateStacks(par0Random, var5.itemStack, var5.theMinimumChanceToGenerateItem, var5.theMaximumChanceToGenerateItem);

            for (ItemStack item : stacks)
            {
                par2TileEntityChest.setInventorySlotContents(par0Random.nextInt(par2TileEntityChest.getSizeInventory()), item);
            }
        }
    }

    /**
     * Generates the Dispenser contents.
     */
    public static void generateDispenserContents(Random par0Random, WeightedRandomChestDayZ[] par1ArrayOfWeightedRandomChestContent, TileEntityDispenser par2TileEntityDispenser, int par3)
    {
        for (int var4 = 0; var4 < par3; ++var4)
        {
            WeightedRandomChestDayZ var5 = (WeightedRandomChestDayZ)WeightedRandom.getRandomItem(par0Random, par1ArrayOfWeightedRandomChestContent);
            ItemStack[] stacks = ChestGenHooks.generateStacks(par0Random, var5.itemStack, var5.theMinimumChanceToGenerateItem, var5.theMaximumChanceToGenerateItem);

            for (ItemStack item : stacks)
            {
                par2TileEntityDispenser.setInventorySlotContents(par0Random.nextInt(par2TileEntityDispenser.getSizeInventory()), item);
            }
        }
    }
}
