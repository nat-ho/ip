public class Task {
    private String description;
    private boolean isDone;

    private static int reminderCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        reminderCount++;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public static int getReminderCount() {
        return reminderCount;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getFullTaskInfo() {
        return this.getStatusIcon() + this.getDescription();
    }
}
