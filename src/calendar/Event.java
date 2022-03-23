package calendar;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event {
    private String name;
    private String description;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public Event(String name,String description,LocalDate date,LocalTime startTime,LocalTime endTime) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String format() {
        return name + ",," + date + ",," + startTime + ",," + endTime;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setDescription(String newDescription) {
        description = newDescription;
    }

    public void setDate(LocalDate newDate) {
        date = newDate;
    }

    public void setStartTime(LocalTime newStartTime) {
        startTime = newStartTime;
    }

    public void setEndTime(LocalTime newEndTime) {
        endTime = newEndTime;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
