package calendar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class CalendarProgram {
    public static ArrayList<Date> allDates = new ArrayList<>();

    public static void main(String[] args) {
         try {
             //file loading
             //TODO: add more comments
             File tempfile = new File("resources/dates.txt");
             tempfile.createNewFile();
             Scanner file = new Scanner(tempfile);
             for (String date : file.nextLine().split("\\\\")) {
                 String[] dateObj = date.split("]\\[");
                 Date tempDate = new Date(LocalDate.parse(dateObj[0]));
                 for (String event : dateObj[1].split("//")) {
                     String[] eventData = event.split(",,");
                     tempDate.addEvent(new Event(eventData[0],eventData[1],LocalDate.parse(eventData[2]), LocalTime.parse(eventData[3]),LocalTime.parse(eventData[4])),true);
                 }
                 String[] diaryObj = dateObj[2].split(",,");
                 DiaryEntry diary = new DiaryEntry(LocalDate.parse(diaryObj[0]),diaryObj[1],LocalTime.parse(diaryObj[2]));
                 tempDate.setEntry(diary);
                 allDates.add(tempDate);
             }
             file.close();
         } catch (Exception e) {
            ;
         }
        try {

             //use
             boolean end = false;
             while (!end) {
                 Scanner scan = new Scanner(System.in);
                 System.out.print("What would you like to do? (View/edit/save/close): ");
                 String option = scan.nextLine().toLowerCase();
                 if (option.equals("view")) {
                     allDates.sort(Comparator.comparing(Date::getDate));
                     for (Date date : allDates) {
                         System.out.println(date.format());
                     }
                 } else if (option.equals("edit")) {
                     System.out.print("Would you like to edit events or diary entries? ");
                     String option1 = scan.nextLine().toLowerCase();
                     if (option1.equals("events")) {
                         System.out.print("Would you like to add, modify or delete an event? ");
                         String option2 = scan.nextLine().toLowerCase();
                         if (option2.equals("add")) {
                             System.out.print("What is the name of the event? ");
                             String name = scan.nextLine();
                             System.out.print("Please provide a short description of the event: ");
                             String description = scan.nextLine();
                             System.out.print("What date will the event begin? (YYYY-MM-DD) ");
                             LocalDate date = LocalDate.parse(scan.nextLine());
                             System.out.print("What time will the event start? ");
                             LocalTime startTime = LocalTime.parse(scan.nextLine());
                             System.out.print("What time will the event end? ");
                             LocalTime endTime = LocalTime.parse(scan.nextLine());
                             Event event = new Event(name,description,date,startTime,endTime);

                             boolean dateFound = false;
                             for (Date dateObj : allDates) {
                                 if (dateObj.getDate().isEqual(date)) {
                                     if (dateObj.addEvent(event,false) == 2) {
                                         String override = scan.nextLine().toLowerCase();
                                         if (override.equals("yes")) {
                                             dateObj.addEvent(event,true);
                                         } else {
                                             continue;
                                         }
                                     }
                                     dateFound = true;
                                     break;
                                 }
                             }
                             if (!dateFound) {
                                 ArrayList<Event> temp = new ArrayList<>();
                                 temp.add(event);
                                 Date newDate = new Date(date,temp);
                                 allDates.add(newDate);
                             }
                             System.out.println("Event added! Remember to save before exiting!");
                         } else if (option2.equals("edit")) {
                             System.out.print("What is the name of the event you would like to edit? ");
                             String name = scan.nextLine();
                             System.out.print("What date is this event on? (YYYY-MM-DD) ");
                             LocalDate dateIn = LocalDate.parse(scan.nextLine());

                             boolean found = false;
                             for (Date date : allDates) {
                                 if (date.getDate().isEqual(dateIn)) {
                                     for (Event event : date.getEvents()) {
                                         if (event.getName().equals(name)) {
                                             found = true;
                                             System.out.print("What would you like to edit?" );
                                             String choice = scan.nextLine();
                                             switch (choice) {
                                                 case "name":
                                                     System.out.print("Enter new name: ");
                                                     String newName = scan.nextLine();
                                                     event.setName(newName);
                                                     System.out.println("Name updated to " + newName);
                                                     break;
                                                 case "description":
                                                     System.out.print("Enter new description: ");
                                                     String newDescription = scan.nextLine();
                                                     event.setDescription(newDescription);
                                                     System.out.println("Description updated!");
                                                     break;
                                                 case "start time":
                                                     System.out.print("Enter new start time:" );
                                                     LocalTime newStartTime = LocalTime.parse(scan.nextLine());
                                                     event.setStartTime(newStartTime);
                                                     System.out.println("Start time updated to " + newStartTime.toString());
                                                     break;
                                                 case "end time":
                                                     System.out.print("Enter new end time: ");
                                                     LocalTime newEndTime = LocalTime.parse(scan.nextLine());
                                                     event.setEndTime(newEndTime);
                                                     System.out.println("End time updated to " + newEndTime.toString());
                                             }
                                         }
                                     }
                                 }
                             }
                             if (!found) {
                                 System.out.println("Event not found");
                             }
                         } else if (option2.equals("delete")) {
                             System.out.print("What's the name of the event you'd like to delete? ");
                             String name = scan.nextLine();
                             System.out.print("What date is the event you'd like to delete? ");
                             LocalDate date = LocalDate.parse(scan.nextLine());
                             for (Date dateObj : allDates) {
                                 if (dateObj.getDate().isEqual(date)) {
                                     if (dateObj.deleteEvent(name)) {
                                         System.out.println("Event deleted successfully");
                                     } else {
                                         System.out.println("Event not found");
                                     }
                                 }
                             }
                         }
                     } else if (option1.equals("diary entries")) {
                         System.out.print("What date would you like to add a diary entry for? ");
                         LocalDate date = LocalDate.parse(scan.nextLine());
                         for (Date dateObj : allDates) {
                             if (dateObj.getEntry() != null) {
                                 System.out.print("This will overwrite the existing diary entry. Are you sure you want to do this? ");
                                 String confirmation = scan.nextLine().toLowerCase();
                                 if (confirmation.equals("yes")) {
                                     System.out.print("Enter new diary entry: ");
                                     String entry = scan.nextLine();
                                     dateObj.setEntry(new DiaryEntry(date,entry));
                                     System.out.println("Diary entry set!");
                                 } else {
                                     System.out.println("Diary entry update cancelled");
                                     continue;
                                 }
                             } else {
                                 System.out.print("Enter new diary entry: ");
                                 String entry = scan.nextLine();
                                 dateObj.setEntry(new DiaryEntry(date,entry));
                                 System.out.println("Diary entry set!");
                             }
                         }
                     }
                 } else if (option.equals("save")) {
                     System.out.println("Saving to file...");
                     String saveString = "";
                     for (Date date : allDates) {
                         saveString += date.formatSave() + "\\\\";
                     }
                     FileWriter f1 = new FileWriter("resources/dates.txt");
                     BufferedWriter buff = new BufferedWriter(f1);
                     buff.write("saveString");
                     buff.close();
                     f1.close();
                     System.out.println("Entries and events saved. It is now safe to close the program!");
                 } else if (option.equals("close")) {
                     end = true;
                 }
             }
         } catch (Exception e) {
             System.out.println("An unexpected error was encountered");
             System.out.println(e.getMessage());
         }
    }
}
