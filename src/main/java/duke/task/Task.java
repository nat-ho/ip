package duke.task;

public class Task {
    private static int taskCount = 0;

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
        taskCount++;
    }

    public String getStatusIcon() {
        //return tick or X symbols
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    public String getDescription() {
        return this.description;
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + getDescription();
    }
}
