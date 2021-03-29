package database;

import org.bson.Document;

import java.util.HashMap;

public class DBMainTest {

    public static void main(String[] args) {

        // connecting to locacl host MongoDB
        // assumption that trivia DB and testing collection are created
        // TODO: change testing colletion to official questions collection
        MongoConnection connection = new MongoConnection("localhost", 27017, "trivia","testing");
        int id_add = 5; // just for general pupose so no need to change everywhere
        int id_fetch = 2;

        // create add object to be insert
        // changed Id after every insert manually for now. Inserts probably not needed
        Document document = new Document("_id", id_add)
                .append("question", "trivia question placeholder")
                .append("option 1", "1")
                .append("option 2", "2")
                .append("option 3", "3")
                .append("answer", "4");

        connection.insert(document);

        //get specific question with id number passed in
        HashMap<String, String> question = connection.getQuestion(id_fetch);
        question.forEach((key, value) -> {
            System.out.printf("%s: %s\n", key, value);
        });

        System.out.println();
        // testing update
        connection.update(1, "question", "new value");
        question = connection.getQuestion(1);
        question.forEach((key, value) -> {
            System.out.printf("%s: %s\n", key, value);
        });

        // deleting just created data for easier testing
        connection.delete(id_add);

    }
}
