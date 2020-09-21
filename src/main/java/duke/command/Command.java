package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * Represents an executable command
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks contains list of tasks
     * @param ui UI object that manages information input and output to user
     * @param storage storage functions
     * @throws DukeException if command causes an error/exception
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;

    /**
     * Flag that checks if a command will lead to Duke exiting after execution.
     *
     * @return Boolean that returns true if exiting and false if not
     */
    public abstract boolean isExit();
}
