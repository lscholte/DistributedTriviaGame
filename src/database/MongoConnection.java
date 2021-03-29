package database;

import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class MongoConnection {

    // create the connection to the DB, on local machine
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection;

    public MongoConnection(String host, int port, String database, String collection){
        // create the connection to the DB
        this.mongoClient = new MongoClient(host, port);
        this.database = mongoClient.getDatabase(database);
        this.collection = this.database.getCollection(collection);
    }

    // inserts a single Document into the connected Database.
    // returns true if successfully update, else returns false
    public boolean insert(Document document){
        try{
            collection.insertOne(document);
            return true;
        } catch (MongoWriteException MWe){
            return false;
        }
    }

    public HashMap<String, String> getQuestion (int id){
        HashMap<String, String> question = new LinkedHashMap<String, String>();
        Bson filter = eq("_id", id);
        Document query = collection.find(filter).first();

        query.forEach((key, value) ->{
            if(!key.equalsIgnoreCase("_id")){
                if(key.equalsIgnoreCase("answer")){
                    question.put("option 4", (String) value);
                }
                question.put(key, (String) value);
            }
        });

        return question;
    }

    // updates a specifice key value pair for a id
    // checks if id exists and updates the value and returns true
    // else id is not there and returns false
    public boolean update (int id, String key, String newValue){
        Bson filter = eq("_id", id);
        if(collection.find(filter).first() != null) {
            Bson updateOperation = set(key, newValue);
            collection.updateOne(filter, updateOperation);
            return true;
        }
        return false;
    }

    // deletes specified id
    public void delete (int id){
        Bson filter = eq("_id", id);
        Document doc = collection.findOneAndDelete(filter);
    }


}
