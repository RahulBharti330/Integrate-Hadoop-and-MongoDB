package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoHadoopJob {
    public static void main(String[] args) {
        // MongoDB Connection URI
        String uri = "mongodb+srv://Rahul:Rahul%4028@cluster0.vblvn.mongodb.net/test?retryWrites=true&w=majority";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            // Get source and destination collections
            MongoDatabase database = mongoClient.getDatabase("testDB");
            MongoCollection<Document> sourceCollection = database.getCollection("usersNew");
            MongoCollection<Document> destCollection = database.getCollection("processedUsersData");

            // Process all documents in the source collection
            System.out.println("Starting processing of MongoDB documents...");
            int processedCount = 0;

            try (MongoCursor<Document> cursor = sourceCollection.find().iterator()) {
                List<Document> processedDocs = new ArrayList<>();

                while (cursor.hasNext()) {
                    Document doc = cursor.next();

                    // Map operation (extract name and city)
                    String name = doc.getString("name");
                    String city = doc.getString("city");

                    if (name != null && city != null) {
                        // Create new document with processed data (similar to reduce operation)
                        Document processedDoc = new Document()
                                .append("name", name)
                                .append("city", city)
                                .append("processed", true)
                                .append("timestamp", System.currentTimeMillis());

                        processedDocs.add(processedDoc);
                        processedCount++;
                    }
                }

                // Insert all processed documents if any were created
                if (!processedDocs.isEmpty()) {
                    destCollection.insertMany(processedDocs);
                }
            }

            System.out.println("Processing complete. Processed " + processedCount + " documents.");
        }
    }
}