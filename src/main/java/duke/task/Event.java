package duke.task;

public class Event extends Task{
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    public char getType() {
        return 'E';
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (At: " + getAt() + ")";
    }
}
