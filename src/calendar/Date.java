package calendar;

import java.time.LocalDate;
import java.util.ArrayList;

public class Date {
    private final LocalDate date;
    private ArrayList<Event> events;
    private DiaryEntry entry;

    public Date(LocalDate date) {
        this.date = date;
    }

    public Date(LocalDate date,ArrayList<Event> events) {
        this.date = date;
        this.events = events;
    }

    public Date(LocalDate date,DiaryEntry entry) {
        this.date = date;
        this.entry = entry;
    }

    public Date(LocalDate date,ArrayList<Event> events,DiaryEntry entry) {
        this.date = date;
        this.events = events;
        this.entry = entry;
    }

    public String formatSave() {
        String formatted = date.toString() + "][";
        ArrayList<Event> eventsArr;
        for (Event event : events) {
            formatted += event.format() + "//";
        }
        try {
            formatted += "][" + entry.format();
        } catch (Exception e) {
            formatted += "][";
        }
        return formatted;
    }

    public String format() {
        String formatted = date.toString() + "\n";
        try {
            formatted += "Diary entry:\n" + entry.getContent() + "\n";
        } catch (Exception ignored) {
            ;
        }
        for (Event event : events) {
            formatted += event.getName() + "\n" + event.getStartTime() + " - " + event.getEndTime() + "\n" + event.getDescription();
        }
        return formatted;
    }

    public int addEvent(Event newEvent,boolean overlapOverride) {
        for (Event event : events) {
            if ((((newEvent.getStartTime()).isBefore(event.getEndTime()) && newEvent.getStartTime().isAfter(event.getStartTime())) ||
                    newEvent.getEndTime().isBefore(event.getEndTime()) && newEvent.getEndTime().isAfter(event.getStartTime())) &&  !overlapOverride) {
                System.out.println("This event overlaps with " + event.getName() + ". Are you sure you want to add it?");
                return -2;
            }
        }
        events.add(newEvent);
        System.out.println("Event added!");
        return 1;
    }

    public boolean deleteEvent(String deleting) {
        int x = 0;
        boolean found = false;
        for (Event event : events) {
            if (event.getName().equals(deleting)) {
                found = true;
                events.remove(event);
            }
            ++x;
        }
        return found;
    }

    public void setEvents(ArrayList<Event> newEvents) {
        events = newEvents;
    }

    public void setEntry(DiaryEntry newEntry) {
        entry = newEntry;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public DiaryEntry getEntry() {
        return entry;
    }

    public LocalDate getDate() {
        return date;
    }
}
