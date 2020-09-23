package duke.task;

import duke.ui.UI;

import java.util.ArrayList;

/**
 * Stores a list of tasks
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList object used to store tasks.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Adds a Task object into the list.
     *
     * @param task user created task object
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a Task object from the list.
     *
     * @param taskNumber integer identifying a specific task in the list
     */
    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber);
    }

    /**
     * Returns a specific Task object from the list.
     *
     * @param taskNumber integer identifying a specific task in the list
     * @return Task object
     */
    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    /**
     * Returns an iterable list.
     *
     * @return ArrayList containing Task objects
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     *Prints all Task objects stored in list.
     *
     * @param ui UI object that manages information input and output to user
     */
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

    /**
     * Returns current count of Task objects in list.
     *
     * @return integer containing size of list
     */
    public int getTaskCount() {
        return tasks.size();
    }
}