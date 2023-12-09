import com.mongodb.client.*;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class db {
    private static final String uri = "Your MongoDB URI :)";
    private static final MongoClient client = MongoClients.create(uri);
    private static final MongoDatabase db = client.getDatabase("firstDB");
    private static final MongoCollection<Document> col = db.getCollection("timer");
    db(){

        }
        public static void writeData(Long milis){
            int i = 1;
            Document find = col.find(new Document("time", i)).first();
            while (find != null) {
                i++;
                find = col.find(new Document("time", i)).first();
            }
            Document find2 = col.find(new Document("time", i-1)).first();

            Document input = new Document("time", i);
            Long sumOfTwoMilis = (find2!=null) ? (find2.getLong("sumOfMilis") + milis) : (milis);
            input.append("timePerDay", Timer.formatDuration(milis)).append("sumOfTime" ,Timer.formatDuration(sumOfTwoMilis)).append("sumOfMilis", sumOfTwoMilis);
            input.append("timePerDay", Timer.formatDuration(milis)).append("sumOfTime" ,Timer.formatDuration(sumOfTwoMilis)).append("sumOfMilis", sumOfTwoMilis);
            col.insertOne(input);
            col.find();
        }

        public static void saveDataTofIle() throws IOException {
            FindIterable<Document> iterDoc = col.find();
            FileWriter writer = new FileWriter("savedData.txt");
            FileReader reader = new FileReader("savedData.txt");
            int data = reader.read();
            Iterator it = iterDoc.iterator();
            for (int i = 1; it.hasNext(); i++){
                String tempStr = (it.next().toString());
                String str = tempStr.substring(40, tempStr.length()-2) + "\n";
                String temp = new String();
                try (BufferedReader br = new BufferedReader(new FileReader("savedData.txt"))) {
                    temp = br.readLine();
                }
                if (!str.equals(temp)) {
                    writer.write(str);
                }
            }
            writer.close();
        }
    }