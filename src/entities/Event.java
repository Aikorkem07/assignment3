package entities;

public class Event {
    private int id;
    private String name;
    private String date;
    private boolean cancelled;

    public Event(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getName() { return name; }
    public String getDate() { return date; }
}
