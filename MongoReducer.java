package org.example;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.bson.BSONObject;
import org.bson.BasicBSONObject;

import java.io.IOException;

public class MongoReducer extends Reducer<Text, Text, Text, BSONObject> {
    @Override
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        try {
            for (Text city : values) {
                BSONObject doc = new BasicBSONObject();
                doc.put("name", key.toString());
                doc.put("city", city.toString());
                doc.put("processed", true);
                doc.put("timestamp", System.currentTimeMillis());
                context.write(key, doc);
            }
        } catch (Exception e) {
            System.err.println("Error in reducer: " + e.getMessage());
            e.printStackTrace();
        }
    }
}