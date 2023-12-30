package dev.selena.tests;

import dev.selena.luacore.utils.data.UserFolder;
import lombok.Getter;

import java.util.UUID;

public class TestUserData extends UserFolder {

    @Getter
    private TestMappedUserData1 data1;
    @Getter
    private TestMappedUserData2 data2;

    public TestUserData(UUID uuid) {
        super(uuid);
        data1 = loadData(TestMappedUserData1.class, "Test1.json");
        data2 = loadData(TestMappedUserData2.class, "Test2.json");
    }



}
