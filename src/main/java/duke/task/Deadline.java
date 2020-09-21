package duke.task;

/**
 * Represents a Deadline task
 * Subclass from Task superclass
 */
public class Deadline extends Task{
    private String by;

    /**
     * Creates a Deadline object with description and by.
     *
     * @param description description of deadline
     * @param by task deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns deadline information.
     *
     * @return String containing deadline information
     */
    public String getBy() {
        return this.by;
    }

    @Override
    public char getType() {
        return 'D';
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (By: " + getBy() + ")";
    }
}
