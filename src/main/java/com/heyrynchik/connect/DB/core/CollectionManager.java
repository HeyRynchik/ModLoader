package com.heyrynchik.connect.DB.core;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class CollectionManager {
    public MongoDatabase database;

    public CollectionManager(MongoDatabase database) {
        this.database = database;
    }
    public CollectionManager(String database){
        this.database = DatabaseManager.getDatabase(database);
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public MongoCollection<Document> getCollection(String collection){
        return database.getCollection(collection);
    }
}
