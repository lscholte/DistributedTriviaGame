package database;

import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBMainTest {

    public static void main(String[] args) {

        // connecting to locacl host MongoDB
        // assumption that trivia DB and testing collection are created
        // TODO: change testing collection to official questions collection
        MongoConnection connection = new MongoConnection("localhost", 27017, "trivia","questions");
        int id_add = 101; // just for general pupose so no need to change everywhere
        int id_fetch = 2;

        // create add object to be insert
        // changed Id after every insert manually for now. Inserts probably not needed
        Document document = new Document("_id", id_add)
                .append("question", "trivia question placeholder")
                .append("correct_answer", "1")
                .append("incorrect_answers", new ArrayList<String>(List.of("2", "3", "4")));

        connection.insert(document);

        //get specific question with id number passed in
        HashMap<String, String> question = connection.getQuestion(id_fetch);
        question.forEach((key, value) -> {
            System.out.printf("%s: %s\n", key, value);
        });

        System.out.println();
        // testing update
        connection.update(id_add, "question", "new value");
        question = connection.getQuestion(id_add);
        question.forEach((key, value) -> {
            System.out.printf("%s: %s\n", key, value);
        });

        // deleting just created data for easier testing
        //connection.delete(id_add);

    }
}
