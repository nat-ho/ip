package duke.task;

/**
 * Represents a ToDo task
 * Subclass from Task superclass
 */
public class ToDo extends Task {
    /**
     * Creates a todo object with description.
     *
     * @param description Description of task
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public char getType() {
        return 'T';
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
