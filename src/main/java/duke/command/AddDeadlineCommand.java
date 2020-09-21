package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Messages;
import duke.ui.UI;

public class AddDeadlineCommand extends Command{
    private Deadline deadline;

    public static final String COMMAND_WORD = "deadline";
    private static final String ERROR_MESSAGE_ADD_DEADLINE = "Unless you get me another pint, I only recognize:" +
            System.lineSeparator() + Messages.DEADLINE_FORMAT;
    private static final String DEADLINE_DELIMITER = " /by ";
    private static final String WRITE_FILE_ERROR = "Unable to save tasks to file";

    public AddDeadlineCommand(String userInput) throws DukeException {
        try {
            String[] deadlineDetails = userInput.split(DEADLINE_DELIMITER);
            String deadlineName = deadlineDetails[0];
            String deadlineBy = deadlineDetails[1];
            deadline = new Deadline(deadlineName, deadlineBy);
        } catch(IndexOutOfBoundsException e) {
            throw new DukeException(ERROR_MESSAGE_ADD_DEADLINE);
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        tasks.add(deadline);
        ui.printAddTaskSuccess(deadline);
        try {
            storage.saveTasksToTile(tasks);
        } catch (DukeException e) {
            throw new DukeException(WRITE_FILE_ERROR);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
