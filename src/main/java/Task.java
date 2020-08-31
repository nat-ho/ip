public class Task {
    protected String description;
    protected boolean isDone;

    protected static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
        this.isDone = true;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.getDescription();
    }
}
