package duke.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public char getType() {
        return 'T';
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
