package duke.task;

import duke.ui.UI;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber);
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public  ArrayList<Task> getTaskList() {
        return tasks;
    }

    public void printAllTasks(UI ui) {
        if (tasks.size() != 0) {
            ui.printDividerLine();
            for (Task task  : tasks) {
                System.out.println(tasks.indexOf(task)+1 + ". " + task);
            }
            ui.printDividerLine();
            System.out.println(System.lineSeparator());
        }
    }

    public int getTaskCount() {
        return tasks.size();
    }
}