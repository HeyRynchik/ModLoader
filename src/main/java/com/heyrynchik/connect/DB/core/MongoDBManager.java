package com.heyrynchik.connect.DB.core;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBManager {
    protected static MongoDBManager instance;
    public String format = "mongodb+srv://%s:%s@modloadermods.zngqw2d.mongodb.net/?retryWrites=true&w=majority"; // username, password
    public String username, password;

    protected MongoDBManager(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public MongoClient getClient(){
        return MongoClients.create(String.format(format,username,password));
    }
    public static MongoDBManager getInstance(){
        return instance;
    }
    public static void createInstance(String username, String password) {
        if(instance == null){
            instance = new MongoDBManager(username,password);
        }
    }

}
