package org.example;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.bson.BSONObject;

import java.io.IOException;

public class MongoMapper extends Mapper<Object, BSONObject, Text, Text> {
    @Override
    public void map(Object key, BSONObject value, Context context) throws IOException, InterruptedException {
        try {
            if (value.containsField("name") && value.containsField("city")) {
                String name = value.get("name").toString();
                String city = value.get("city").toString();
                context.write(new Text(name), new Text(city));
            } else {
                System.err.println("Missing fields in document: " + value.toString());
            }
        } catch (Exception e) {
            System.err.println("Error processing document: " + e.getMessage());
            e.printStackTrace();
        }
    }
}