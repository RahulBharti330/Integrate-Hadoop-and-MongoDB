package org.example;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.MongoClient;

public class MongoDBInsert {
    public static void main(String[] args) {
        // MongoDB Connection URI
        String uri = "mongodb+srv://Rahul:Rahul%4028@cluster0.vblvn.mongodb.net/test?retryWrites=true&w=majority";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("testDB");
            MongoCollection<Document> collection = database.getCollection("usersNew");

            // Insert sample data
            Document user1 = new Document("name", "Jack")
                    .append("age", 36)
                    .append("city", "Tokyo");

            Document user2 = new Document("name", "John")
                    .append("age", 20)
                    .append("city", "Delhi");

            collection.insertOne(user1);
            collection.insertOne(user2);

            System.out.println("Data inserted successfully.");
        }
    }
}

