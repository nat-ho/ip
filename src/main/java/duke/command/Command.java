package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

public abstract class Command {
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
