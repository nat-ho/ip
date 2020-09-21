package duke.task;

/**
 * Represents an Event task
 * Subclass from task superclass
 */
public class Event extends Task{
    private String at;

    /**
     * Creates an Event object with description and by.
     *
     * @param description description of event
     * @param at event information
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns event information.
     *
     * @return String containing event information
     */
    public String getAt() {
        return this.at;
    }

    @Override
    public char getType() {
        return 'E';
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (At: " + getAt() + ")";
    }
}
