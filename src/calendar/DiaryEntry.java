package calendar;

import java.time.LocalDate;
import java.time.LocalTime;

public class DiaryEntry {
    private LocalDate date;
    private String content;
    private LocalTime writeTime;

    public DiaryEntry(LocalDate date,String content) {
        this.date = date;
        this.content = content;
        writeTime = LocalTime.now();
    }

    public DiaryEntry(LocalDate date,String content,LocalTime writeTime) {
        this.date = date;
        this.content = content;
        this.writeTime = writeTime;
    }

    public String format() {
        return date + ",," + content + ",," + writeTime;
    }

    public void setDate(LocalDate newDate) {
        date = newDate;
    }

    public void setContent(String newContent) {
        content = newContent;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getWriteTime() {
        return writeTime;
    }

    public String getContent() {
        return content;
    }
}
