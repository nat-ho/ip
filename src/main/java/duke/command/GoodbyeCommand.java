package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * Prints goodbye message and exits Duke
 */
public class GoodbyeCommand extends Command{
    public static final String COMMAND_WORD = "bye";

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        storage.saveTasksToTile(tasks);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
