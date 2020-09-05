package duke.task;

public class Event extends Task{
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (At: " + getAt() + ")";
    }
}