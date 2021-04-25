package database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Collection;
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

    public MongoConnection(String URL, String database, String collection){
        String mongoURL ="mongodb://" + URL + "/?replicaSet=trivia";
        this.mongoClient = new MongoClient( new MongoClientURI(mongoURL));
//        this.mongoClient = new MongoClient( new MongoClientURI(
//                "mongodb://172.25.0.2:27017,172.25.0.3:27017,172.25.0.4:27017/?replicaSet=trivia"));
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
            if(key.equalsIgnoreCase("question")){
                question.put(key, (String) value);
            }
            if(key.equalsIgnoreCase("correct_answer")){
                question.put("answer", (String) value);
                question.put("option 1", (String) value);
            }
            if(key.equalsIgnoreCase("incorrect_answers")){
                ArrayList<String> incorrect = new ArrayList<String>((Collection<? extends String>) value);
                int i = 2;
                for (String s : incorrect) {
                    question.put("option "+i, s);
                    i++;
                }
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
