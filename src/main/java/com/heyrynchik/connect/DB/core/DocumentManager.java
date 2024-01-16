package com.heyrynchik.connect.DB.core;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class DocumentManager {
    protected MongoCollection<Document> collection;

    public DocumentManager(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    public DocumentManager(String collection, String database) {
        this.collection = new CollectionManager(database).getCollection(collection);
    }

    public Document getDocument(String document) {
        return collection.find(eq("title", Variables.getTitle())).first();
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }
}