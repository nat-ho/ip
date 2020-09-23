package duke.task;

/**
 * Represents Superclass of all tasks.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a Task object given a description.
     *
     * @param description String description of a task
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Returns status of task in the form of an icon.
     *
     * @return tick(unicode 2713) if completed and cross(unicode 2718) if incomplete
     */
    public String getStatusIcon() {
        //return tick or X symbols
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    /**
     * Returns description of task.
     *
     * @return String containing description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks task as completed. Sets isDone to true.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return true if completed, false if incomplete
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * An abstract method that returns the type identifier for the task.
     *
     * @return Char that identifies the task type
     */
    public abstract char getType();

    /**
     * Format task object into a string.
     *
     * @return String representation of a task
     */
    @Override
    public String toString() {
        return getStatusIcon() + getDescription();
    }
}
