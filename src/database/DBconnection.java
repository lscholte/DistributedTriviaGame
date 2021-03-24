package database;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DBconnection {


    public static void main(String[] args) {

        // create the connection to the DB, on local machine
        MongoClient mongoClient = new MongoClient("localhost" , 27017);
        MongoDatabase database = mongoClient.getDatabase("trivia"); // database
        MongoCollection<Document> collection = database.getCollection("testing"); // collection

        // create add object to be insert
        // changed Id after every insert manually for now. Inserts probably not needed
//        Document document = new Document("_id", "3")
//                .append("question", "trivia question placeholder")
//                .append("option 1", "1")
//                .append("option 2", "2")
//                .append("option 3", "3")
//                .append("option 4", "4");
//        // insert to DB
//        collection.insertOne(document);

        // get all items in collection
        //FindIterable<Document> iterDoc = collection.find();

        // create the query needed. pass in as find parameter
        BasicDBObject query = new BasicDBObject("_id","2");
        FindIterable<Document> iterDoc = collection.find(query);

        int i = 1;
        // iterrate through the query result
        for (Document question : iterDoc) {
            question.forEach((key, value) -> {
                System.out.printf("%s: %s\n", key, value);
            });
            i++;
        }

    }



}
