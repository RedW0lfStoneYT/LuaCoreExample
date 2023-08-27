package dev.selena.tests;

import dev.selena.luacore.utils.config.FileManager;
import dev.selena.luacore.utils.lua.LuaValueMapper;

import java.io.File;

public enum ScriptSettings {

    TEST(MappingTest.class, "test", "Test.lua");

    private final File file;

    private final Class<?> clazz;

    <T> ScriptSettings(Class<T> clazz, String parent, String script) {
        this.clazz = clazz;
        file = FileManager.file(parent, script);
    }


    @SuppressWarnings("unchecked")
    public <T> T getScriptValues() {
        return (T) LuaValueMapper.mapToClass(clazz, file.getPath());
    }

}
