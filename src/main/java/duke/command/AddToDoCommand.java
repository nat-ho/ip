package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Messages;
import duke.ui.UI;

public class AddToDoCommand extends Command{
    private ToDo  todo;

    public static final String COMMAND_WORD = "todo";
    private static final String ERROR_MESSAGE_ADD_TODO= "How about giving that task a name?" +
            System.lineSeparator() + Messages.TODO_FORMAT;
    private static final String WRITE_FILE_ERROR = "Unable to save tasks to file";

    public AddToDoCommand(String todoName) throws DukeException{
        if(todoName.isEmpty()) {
            throw new DukeException(ERROR_MESSAGE_ADD_TODO);
        }
        todo = new ToDo(todoName);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException{
        tasks.add(todo);
        ui.printAddTaskSuccess(todo);
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
