package HockeyGame;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Team {
    private String name;
    private Player[] team;
    private int gamesWon;
    private int goalsConceded;

    public Team(String name,Player[] team,int gamesWon,int goalsConceded) {
        this.name = name;
        this.team = team;
        this.gamesWon = gamesWon;
        this.goalsConceded = goalsConceded;
    }

    public void saveToFile() {
         try {
             String formatted = getName() + "-" + getGamesWon() + "-" + getGoalsConceded();
             for (Player player : getTeam()) {
                 formatted += "-" + player.getName() + "/" + player.getAtt() + "/" + player.getDef();
             }
             File teams = new File("resources/hockeyteams.txt");
             Scanner scan = new Scanner(teams);
             String fullText = "";
             boolean found = false;
             while (scan.hasNextLine()) {
                 String currentLine = scan.nextLine();
                 if (currentLine.split("-")[0].equals(getName())) {
                     fullText += formatted + "\n";
                     found = true;
                 } else {
                     fullText += currentLine + "\n";
                 }
             }
             if (!found) {
                 fullText += formatted;
             }

             FileWriter write = new FileWriter("resources/hockeyteams.txt");
             write.write(fullText);
             write.close();
         } catch (Exception e) {
             System.out.println(e.getMessage());
         }
    }

    public String getName() {
        return this.name;
    }

    public Player[] getTeam() {
        return this.team;
    }

    public int getGamesWon() {
        return this.gamesWon;
    }

    public int getGoalsConceded() {
        return this.goalsConceded;
    }

    public void addGoalConceded(int hit) {
        goalsConceded += hit;
    }

    public void winGame() {
        ++gamesWon;
    }
}
