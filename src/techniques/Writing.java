package techniques;

import java.io.*;

public class Writing {
    public static void main(String[] args) throws Exception {
        FileWriter file = new FileWriter("resources/stats.txt");
        BufferedWriter buff = new BufferedWriter(file);
        buff.write("People who understand java: 7\nPeople who are on the fence: 11\nPeople who don't: 4");
        buff.close();
        file.close();
    }
}
