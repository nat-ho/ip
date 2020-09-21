package duke.task;

public class Deadline extends Task{
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    public char getType() {
        return 'D';
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (By: " + getBy() + ")";
    }
}
