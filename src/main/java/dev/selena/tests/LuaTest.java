package dev.selena.tests;

import dev.selena.libs.vm2.compiler.LuaC;
import dev.selena.luacore.LuaCore;
import dev.selena.luacore.exceptions.lua.NoReturnValueException;
import dev.selena.luacore.utils.RandomCollection;
import dev.selena.luacore.utils.config.FileManager;
import dev.selena.luacore.utils.data.UserDataManager;
import dev.selena.luacore.utils.lua.LuaManager;
import dev.selena.luacore.utils.lua.LuaValueMapper;
import dev.selena.luacore.utils.text.LuaMessageUtils;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.Random;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class LuaTest extends JavaPlugin implements Listener {

    @Getter
    private UserDataManager dataManager;
    @Override
    public void onEnable() {

        LuaCore.setPlugin(this);
        LuaCore.setVerbose(true);
        LuaCore.registerItemEvents();
        dataManager = new UserDataManager("data");
        LuaCore.setUserDataManager(dataManager);

        getServer().getPluginManager().registerEvents(this, this);


        try {
            LuaManager.loadResourceFolder("test");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MappingTest cls = null;
        try {
            cls = LuaValueMapper.mapToClass(MappingTest.class, FileManager.folderPath("test") + "Test.lua");
        } catch (NoReturnValueException e) {
            throw new RuntimeException(e);
        }
        LuaMessageUtils.json_dump(cls);

        RandomCollection<ItemStack> items = new RandomCollection<>(); // Initiate the collection with a set seed

        items.add(5, new ItemStack(Material.STONE)) // Adds stone with a lower chance of getting chosen
                .add(10, new ItemStack(Material.REDSTONE)); // Adds Redstone with twice the chance of getting chosen

        ItemStack randomItem = items.getRandom(); // Will return a random value based off the weights
        LuaMessageUtils.consoleSend(randomItem.getType());

    }

    @SneakyThrows
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        TestUserData data = dataManager.getUserDataFolder(TestUserData.class, event.getPlayer().getUniqueId());
        LuaMessageUtils.verboseMessage(event.getPlayer().getName() + " Has joined the sever with the following data");
        LuaMessageUtils.verboseMessage(data.getData1().test.toString());
        LuaMessageUtils.verboseMessage(data.getData2().test.toString());

    }



}
