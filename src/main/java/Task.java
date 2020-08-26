public class Task {
    private String description;
    private boolean isDone;

    private static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskCount++;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getFullTaskInfo() {
        return this.getStatusIcon() + this.getDescription();
    }
}
