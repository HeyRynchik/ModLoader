package com.heyrynchik.connect.DB.core;

import com.mongodb.client.MongoDatabase;

public class DatabaseManager {

    public DatabaseManager() {
    }
    public static MongoDatabase getDatabase(String database){
        if(MongoDBManager.getInstance() == null){
            MongoDBManager.createInstance(Variables.getUsername(),Variables.getPassword());
        }
        return MongoDBManager.getInstance().getClient().getDatabase(database);
    }
}
