package dev.selena.tests;

import dev.selena.libs.gson.annotations.Expose;
import dev.selena.luacore.LuaCore;
import dev.selena.luacore.utils.RandomCollection;
import dev.selena.luacore.utils.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

public class MappingTest {

    @Expose
    public String Test_String = "123";
    @Expose
    public boolean Test_Boolean = false;
    @Expose
    public int Test_Int = 1;
    @Expose
    public World Test_World = Bukkit.getWorld("world_the_end");

    public ItemStack test = new ItemBuilder(Material.STONE)
            .addCustomNBT("MapperClass", this) // You can store any value using addCustomNBT
            .setAmount(5) // Used for setting the ItemStack size
            .setTitle("&aCustom Item") // Sets the display name of the item
            .setStackable(false) // Assigns it a random UUID to prevent stacking the items
            .setGlowing(true) // If there are no enchants it will hide the enchants on the item and add ARROW_DAMAGE enchant
            .setLore("Line 1", "line 2") // There are 2 methods of setting the Lore, you can either use a string array or a List
            .build(); // Builds the item and returns the ItemStack



}
