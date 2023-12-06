package dev.selena.tests;

import dev.selena.libs.vm2.compiler.LuaC;
import dev.selena.luacore.LuaCore;
import dev.selena.luacore.utils.RandomCollection;
import dev.selena.luacore.utils.config.FileManager;
import dev.selena.luacore.utils.lua.LuaManager;
import dev.selena.luacore.utils.lua.LuaValueMapper;
import dev.selena.luacore.utils.text.LuaMessageUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;


public class LuaTest extends JavaPlugin {


    @Override
    public void onEnable() {

        LuaCore.setPlugin(this);
        LuaCore.setVerbose(true);
        LuaCore.registerItemEvents();

        LuaManager.loadResourceFolder("test");
        MappingTest cls = LuaValueMapper.mapToClass(MappingTest.class, FileManager.folderPath("test") + "Test.lua");
        LuaMessageUtils.json_dump(cls);

        RandomCollection<ItemStack> items = new RandomCollection<>(); // Initiate the collection with a set seed

        items.add(5, new ItemStack(Material.STONE)) // Adds stone with a lower chance of getting chosen
                .add(10, new ItemStack(Material.REDSTONE)); // Adds Redstone with twice the chance of getting chosen

        ItemStack randomItem = items.getRandom(); // Will return a random value based off the weights
        LuaMessageUtils.consoleSend(randomItem.getType());

    }

}
