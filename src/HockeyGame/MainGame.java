package HockeyGame;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class MainGame {
    public static void main(String[] no) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.print("Would you like to view results or play? ");
        String opt1 = scan.nextLine();
        if (opt1.equalsIgnoreCase("view")) {
            System.out.print("Would you like to view a single team or the leaderboards? ");
            String opt2 = scan.nextLine();
            ArrayList<Team> allTeams = new ArrayList<>();
            for (int i=0;i<Files.lines(Path.of("resources/hockeyteams.txt")).count();++i) {
                allTeams.add(createFromFile(i));
            }
            if (opt2.equalsIgnoreCase("leaderboard") || opt2.equalsIgnoreCase("leaderboards")) {
                allTeams.sort(Comparator.comparingInt(Team::getGamesWon));
                Collections.reverse(allTeams);
                String leaderboards = "By wins:\n-- First --\n";
                for (int i=0;i<3;++i) {
                    leaderboards += allTeams.get(i).getName() + " - " + allTeams.get(i).getGamesWon() + " wins\n";
                    switch (i) {
                        case 0:
                            leaderboards += "-- Second --\n";
                            break;
                        case 1:
                            leaderboards += "-- Third --\n";
                    }
                }
                leaderboards += "\n\nBy goals conceded:\n-- First --\n";
                allTeams.sort(Comparator.comparingInt(Team::getGoalsConceded));
                for (int i=0;i<5;++i) {
                    leaderboards += allTeams.get(i).getName() + " - " + allTeams.get(i).getGoalsConceded() + " conceded\n";
                    switch (i) {
                        case 0:
                            leaderboards += "-- Second --\n";
                            break;
                        case 1:
                            leaderboards += "-- Third --\n";
                            break;
                        case 2:
                            leaderboards += "-- Fourth --\n";
                            break;
                        case 3:
                            leaderboards += "-- Fifth --\n";
                    }
                }
                System.out.println(leaderboards);
            } else {
                System.out.print("Enter team name: ");
                String tname = scan.nextLine();
                for (Team team : allTeams) {
                    if (team.getName().equalsIgnoreCase(tname)) {
                        System.out.println(team.getName() + "\nGames won: " + team.getGamesWon() + "\nGoals conceded: " + team.getGoalsConceded());
                    }
                }
            }
            return;
        }
        int teamsSelected = 0;
        Team[] teams = new Team[2];
        while (teamsSelected != 2) {
            System.out.print("Would you like to create or select a team (team " + (teamsSelected+1) + ")? ");
            String opt = scan.nextLine();
            if (opt.equalsIgnoreCase("create")) {
                System.out.print("What will the name of the team be? ");
                String name = scan.nextLine();
                System.out.println("The team will be called " + name);
                Player[] players = new Player[6];
                Team team = null;
                int x = 0;
                while (x < 6) {
                    System.out.print("Enter player name: ");
                    String pname = scan.nextLine();
                    System.out.print("Enter Attack (0-10): ");
                    int att = Integer.parseInt(scan.nextLine());
                    if (att < 0 || att > 10) {
                        System.out.println("Invalid attack value");
                        continue;
                    }
                    System.out.print("Enter Defence (0-7): ");
                    int def = Integer.parseInt(scan.nextLine());
                    if (def < 0 || def > 7) {
                        System.out.println("Invalid defence value");
                        continue;
                    }
                    players[x] = new Player(pname,att,def);
                    if (x == 5) {
                        int total = 0;
                        for (Player player : players) {
                            total += player.getAtt() + player.getDef();
                        }
                        if (total != 35) {
                            System.out.println("Team stats must equal to 35");
                            x = 0;
                            players = new Player[6];
                            continue;
                        } else {
                            team = new Team(name,players,0,0);
                        }
                    }
                    ++x;
                }
                assert team != null;
                team.saveToFile();
                ++teamsSelected;
                teams[teamsSelected-1] = team;
            } else if (opt.equalsIgnoreCase("select")) {
                File teamsFile = new File("resources/hockeyteams.txt");
                if (!teamsFile.exists()) {
                    System.out.println("No teams are available, please create a team");
                    continue;
                }
                Scanner file = new Scanner(teamsFile);
                int currentLine = 1;
                while (file.hasNextLine()) {
                    String[] currentTeam = file.nextLine().split("-");
                    String name = currentTeam[0];
                    System.out.println("TEAM " + currentLine + " - " + name);
                    System.out.println("Team members:");
                    for (int i=3;i<8;++i) {
                        String[] currentPlayer = currentTeam[i].split("/");
                        System.out.println("Name: " + currentPlayer[0]);
                        System.out.println("Attack: " + currentPlayer[1]);
                        System.out.println("Defence: " + currentPlayer[2]);
                        System.out.println("");
                    }
                    System.out.println("\n\n");
                    ++currentLine;
                }
                System.out.print("Please select a team by team number: ");
                int teamNumber = Integer.parseInt(scan.nextLine());
                Team team = createFromFile(teamNumber-1);
                if (teamsSelected == 1) {
                    if (team.getName().equals(teams[0].getName())) {
                        System.out.println("This team has already been selected, please select another");
                        continue;
                    }
                }
                if (team.getName().equals("fail")) {
                    System.out.println("Error selecting team");
                    continue;
                }
                ++teamsSelected;
                teams[teamsSelected-1] = team;
            }
        }
        System.out.println("Player 1 team members:");
        int pcount = 0;
        for (Player player : teams[0].getTeam()) {
            System.out.println((pcount+1) + ") " + player.getName() + " -- Attack: " + player.getAtt() + " -- Defence: " + player.getDef());
            System.out.println("");
            ++pcount;
        }
        ArrayList<Player> p1Used = new ArrayList<>();
        ArrayList<Player> p2Used = new ArrayList<>();
        System.out.print("Player 1, select a goalkeeper: ");
        Player p1goal = teams[0].getTeam()[Integer.parseInt(scan.nextLine())-1];
        p1Used.add(p1goal);
        System.out.println("Player 2 team members:");
        pcount = 0;
        for (Player player : teams[1].getTeam()) {
            System.out.println((pcount+1) + ") " + player.getName() + " -- Attack: " + player.getAtt() + " -- Defence: " + player.getDef());
            System.out.println("");
            ++pcount;
        }
        int p1hits = 0;
        int p2hits = 0;
        System.out.print("Player 2, select a goalkeeper: ");
        Player p2goal = teams[1].getTeam()[Integer.parseInt(scan.nextLine())-1];
        p2Used.add(p2goal);
        System.out.println("\n\nThe penalties will now begin.");
        int playersPlayed = 0;
        while (playersPlayed < 10) {
            System.out.println("Player 1 penalty");
            System.out.print("Select attacker: ");
            Player p1att = teams[0].getTeam()[Integer.parseInt(scan.nextLine())-1];
            while (p1Used.contains(p1att)) {
                System.out.println("This player has already been selected.");
                System.out.print("Select attacker: ");
                p1att = teams[0].getTeam()[Integer.parseInt(scan.nextLine())-1];
            }
            p1Used.add(p1att);
            ++playersPlayed;
            int rand = new Random().nextInt(4)+1;
            int success = p1att.getAtt() - p2goal.getDef() + rand;
            if (success < 0) {
                System.out.println(p1att.getName() + " missed!");
            } else {
                System.out.println(p1att.getName() + " scored!");
                ++p2hits;
                System.out.println("Team 1 has now scored " + p1hits + " goals!");
            }
            System.out.println("\nPlayer 2 penalty");
            System.out.print("Select attacker: ");
            Player p2att = teams[1].getTeam()[Integer.parseInt(scan.nextLine())-1];
            while (p2Used.contains(p2att)) {
                System.out.println("This player has already been selected.");
                System.out.print("Select attacker: ");
                p2att = teams[1].getTeam()[Integer.parseInt(scan.nextLine())-1];
            }
            p2Used.add(p2att);
            ++playersPlayed;
            rand = new Random().nextInt(4)+1;
            success = p2att.getAtt() - p1goal.getDef() + rand;
            if (success < 0) {
                System.out.println(p2att.getName() + " missed!");
            } else {
                System.out.println(p2att.getName() + " scored!");
                ++p1hits;
                System.out.println("Team 2 has now scored " + p2hits + " goals!");
            }
        }
        teams[0].addGoalConceded(p1hits);
        teams[1].addGoalConceded(p2hits);
        if (p1hits == p2hits) {
            System.out.println("With both teams having " + p1hits + ", it's a draw!");
        } else if (p1hits > p2hits) {
            System.out.println("With a " + (p1hits - p2hits) + " goal lead, team 1 wins! (" + p1hits + " goals)");
            teams[0].winGame();
        } else {
            System.out.println("With a " + (p2hits - p1hits) + " goal lead, team 2 wins! (" + p2hits + " goals");
            teams[1].winGame();
        }
        teams[0].saveToFile();
        teams[1].saveToFile();
    }

    public static Team createFromFile(int lineNumber) {
        //pname-gameswon-goalconceded-p1name/p1att/p1def-p2name/p2att/p2def-p3name/p3att/p3def-p4name/p4att/p4def-p5name/p5att/p5def-p6name/p6att/p6def
        try {
            File teams = new File("resources/hockeyteams.txt");
            if (teams.createNewFile()) {
                System.out.println("No teams have been created");
                teams.delete();
                return new Team("fail",new Player[0],-1,-1);
            } else {
                Scanner scan = new Scanner(teams);
                int x = 0;
                while (scan.hasNextLine() && x != lineNumber) {
                    scan.nextLine();
                    ++x;
                }
                String[] teamData = scan.nextLine().split("-");
                String teamName = teamData[0];
                int gamesWon = Integer.parseInt(teamData[1]);
                int penaltiesHit = Integer.parseInt(teamData[2]);
                Player[] players = new Player[6];
                for (int i=3;i<=8;++i) {
                    String[] playerData = teamData[i].split("/");
                    players[i-3] = new Player(playerData[0],Integer.parseInt(playerData[1]),Integer.parseInt(playerData[2]));
                }
                return new Team(teamName,players,gamesWon,penaltiesHit);
            }
        } catch (Exception e) {
            System.out.println("Error caught in Team.createFromFile(int lineNummber)");
            System.out.println(e.getMessage());
            return new Team("fail",new Player[0],-1,-1);
        }
    }
}