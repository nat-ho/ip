package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Messages;
import duke.ui.UI;

public class DeleteCommand extends Command{
    private int taskNumber;
    public static final String COMMAND_WORD = "delete";

    private static final String ERROR_MESSAGE_EXCEED_ONE_ARGUMENT = "How about you take it one at a time. Try:"
            + System.lineSeparator() + Messages.DELETE_FORMAT;
    private static final String ERROR_MESSAGE_DELETE_STRING_PROVIDED = "I'd  prefer if you have me a number. Try:"
            + System.lineSeparator() + Messages.DELETE_FORMAT;
    private static final String ERROR_MESSAGE_NONEXISTENT_TASK = "How about you tell me a task that actually exists?";

    public DeleteCommand(String userInput) throws DukeException {
        String[] deleteDetails = userInput.split(" ");
        if (deleteDetails.length != 1) {
            throw new DukeException(ERROR_MESSAGE_EXCEED_ONE_ARGUMENT);
        }

        try {
            taskNumber = Integer.parseInt(userInput) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(ERROR_MESSAGE_DELETE_STRING_PROVIDED);
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        try {
            Task taskToRemove = tasks.getTask(taskNumber);
            tasks.deleteTask(taskNumber);
            ui.printDeleteTaskSuccess(taskToRemove, tasks.getTaskCount());
            storage.saveTasksToTile(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ERROR_MESSAGE_NONEXISTENT_TASK);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
