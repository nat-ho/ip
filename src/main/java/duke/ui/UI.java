package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class UI {
    private final Scanner in;

    public UI() {
        in = new Scanner(System.in);
    }

    public String readUserCommand() {
        return in.nextLine().trim();
    }

    public void printDividerLine() {
        System.out.println(Messages.DIVIDER_LINE);
    }

    public void printTextWithDividerLine (String ... messages) {
        printDividerLine();
        for (String message : messages) {
            System.out.println(message);
        }
        printDividerLine();
        System.out.println(System.lineSeparator());
    }

    public void printWelcomeMessage() {
        printTextWithDividerLine(Messages.DUKE_LOGO, Messages.MESSAGE_WELCOME);
    }

    public void printGoodbyeMessage() {
        printTextWithDividerLine(Messages.MESSAGE_GOODBYE);
    }

    public void printAddTaskSuccess(Task task) {
        printTextWithDividerLine(Messages.MESSAGE_ADD_TASK_SUCCESS + task);
    }

    public void printCompleteTaskSuccess(Task task) {
        printTextWithDividerLine(Messages.MESSAGE_COMPLETE_TASK_SUCCESS, task.toString());
    }

    public void printDeleteTaskSuccess(Task task, int taskCount) {
        String taskLeft = "You've got " + taskCount + " more tasks to delete instead of complete";
        printTextWithDividerLine(Messages.MESSAGE_DELETE_TASK_SUCCESS, task.toString(), taskLeft);
    }

    public void printFindTaskSuccess(String searchResult, UI ui) {
        printTextWithDividerLine("Sherlock Holmes at your service! It was the butler!" +
                System.lineSeparator() + "Here are my findings: " + System.lineSeparator() + searchResult);
    }

    public void printAllTaskFormat() {
        printTextWithDividerLine(Messages.TODO_FORMAT, Messages.DEADLINE_FORMAT,
                Messages.EVENT_FORMAT);
    }
}
