package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Messages;
import duke.ui.UI;

/**
 * Marks a task from TaskList as completed
 */
public class DoneCommand extends Command{
    private int taskNumber;

    public static final String COMMAND_WORD = "done";
    private static final String ERROR_MESSAGE_DONE_STRING_PROVIDED = "I'd  prefer if you gave me a number. Try:"
            + System.lineSeparator() + Messages.DONE_FORMAT;
    private static final String ERROR_MESSAGE_NONEXISTENT_TASK = "How about you tell me a task that actually exists?";

    /**
     * Constructor for command DoneCommand.
     *
     * @param taskNumber task number provided by user
     * @throws DukeException if wrong arguments provided by user
     */
    public DoneCommand(String taskNumber) throws DukeException {
        try {
            this.taskNumber = Integer.parseInt(taskNumber) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(ERROR_MESSAGE_DONE_STRING_PROVIDED);
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        try {
            tasks.getTask(taskNumber).setDone();
            ui.printCompleteTaskSuccess( tasks.getTask(taskNumber));
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
