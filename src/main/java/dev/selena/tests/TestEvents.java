package dev.selena.tests;

import dev.selena.luacore.utils.lua.LuaArgValue;
import dev.selena.luacore.utils.lua.LuaManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class TestEvents implements Listener {

    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {
        LuaManager.runEvent("test/Test.lua", new LuaArgValue("player", event.getPlayer()), new LuaArgValue("event", event));
    }

}
