package challenges;

import RPGSimClasses.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RPGSim {
    public static void main(String[] args) throws Exception {
        Scanner scan0 = new Scanner(System.in);
        System.out.print("Would you like to create or load? ");
        String choice = scan0.nextLine();
        ArrayList<Creature> team = new ArrayList<>();
        if (choice.equalsIgnoreCase("new") || choice.equalsIgnoreCase("create")) {
            for (int i=0;i!=10;++i) {
                switch (new Random().nextInt(5)) {
                    case 0:
                        team.add(new Barbarian());
                        break;
                    case 1:
                        team.add(new Elf());
                        break;
                    case 2:
                        team.add(new Wizard());
                        break;
                    case 3:
                        team.add(new Dragon());
                        break;
                    case 4:
                        team.add(new Knight());
                        break;
                }
            }
            int x = 1;
            for (Creature i : team) {
                i.printInfo(x);
                ++x;
                Thread.sleep(500);
            }
        } else {
            Scanner scan1 = new Scanner(new File("resources/team.txt"));
            String stringTeam = scan1.nextLine();
            String[] tempArr = stringTeam.split(";;");
            ArrayList<String[]> temp = new ArrayList<>();
            for (String i : tempArr) {
                temp.add(i.split(";;"));
            }
            for (String[] i : temp) {
                for (String j : i) {
                    String[] k = j.split("//");
                    team.add(new Creature(k[0],k[1],Integer.parseInt(k[2]),Integer.parseInt(k[3]),Integer.parseInt(k[4]),Integer.parseInt(k[5])));
                }
            }
            scan1.close();
            int x = 1;
            for (Creature i : team) {
                i.printInfo(x);
                ++x;
                Thread.sleep(500);
            }
        }
        String input = "";
        while (!input.equalsIgnoreCase("no")) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Would you like to add, delete or edit a character or save your team? ");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("add")) {
                Creature temp = new Creature();
                switch (new Random().nextInt(5)) {
                    case 0:
                        team.add(temp = new Barbarian());
                        break;
                    case 1:
                        team.add(temp = new Elf());
                        break;
                    case 2:
                        team.add(temp = new Wizard());
                        break;
                    case 3:
                        team.add(temp = new Dragon());
                        break;
                    case 4:
                        team.add(temp = new Knight());
                        break;
                }
                System.out.println(temp.getName() + " (" + (team.indexOf(temp) + 1) + ") added with type " + temp.getType());
            } else if (input.equalsIgnoreCase("delete") || input.equalsIgnoreCase("del")) {
                System.out.print("Which character would you like to delete? ");
                String num = scan.nextLine();
                int character = Integer.parseInt(num);
                String name = team.get(character-1).getName();
                team.remove(character-1);
                System.out.println(name + " removed");
            } else if (input.equalsIgnoreCase("edit")) {
                System.out.print("Select a character to edit: ");
                int num = Integer.parseInt(scan.nextLine()) - 1;
                System.out.print("Select a stat to edit: ");
                String stat = scan.nextLine();
                System.out.print("Select new value: ");
                int newVal = Integer.parseInt(scan.nextLine());
                Creature character = team.get(num);
                switch(stat.toLowerCase()) {
                    case "health":
                        character.setHealth(newVal);
                        break;
                    case "power":
                        character.setPower(newVal);
                        break;
                    case "sppower":
                    case "special power":
                    case "special attack power":
                        character.setSpPower(newVal);
                        break;
                    case "speed":
                        character.setSpeed(newVal);
                        break;
                    default:
                        System.out.println("Invalid selection");
                }
                character.printInfo();
            } else if (input.equalsIgnoreCase("no")) {
                ;
            } else if (input.equalsIgnoreCase("save")) {
                String out = "";
                for (Creature i : team) {
                    out += i.toFormat();
                    Thread.sleep(500);
                }
                out = out.substring(0,out.length()-2);
                FileWriter file = new FileWriter("resources/team.txt");
                BufferedWriter buff = new BufferedWriter(file);
                buff.write(out);
                buff.close();
                file.close();
                System.out.println("Team saved");
            } else {
                System.out.println("Invalid input");
            }
        }
        System.exit(200);
    }
}
