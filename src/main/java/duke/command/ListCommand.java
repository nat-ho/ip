package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * List all tasks from TaskList
 */
public class ListCommand extends Command{
    public static final String COMMAND_WORD = "list";
    private static final String MESSAGE_EMPTY_TASK_LIST = "Got nothing for you, stop asking me";

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (tasks.getTaskCount() == 0) {
            ui.printTextWithDividerLine(MESSAGE_EMPTY_TASK_LIST);
        } else {
            tasks.printAllTasks(ui);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
