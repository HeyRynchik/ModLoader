package com.heyrynchik.connect.DB.core;


import com.heyrynchik.utils.EnvFileLoader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.heyrynchik.modloader.LOGGER;

public class Variables {
    private static String path;

    static EnvFileLoader env = new EnvFileLoader();


    public static String getUsername() {
        return env.get("username");
    }

    public static String getPassword() {
        return env.get("password");
    }

    public static String getDatabase() {
        return env.get("database");
    }

    public static String getCollection() {
        return env.get("collection");
    }

    public static String getTitle() {
        return env.get("title");
    }
}
