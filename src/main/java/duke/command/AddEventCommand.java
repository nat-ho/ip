package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Messages;
import duke.ui.UI;

/**
 * Adds a Event task into TaskList
 */
public class AddEventCommand extends Command{
    private Event event;

    public static final String COMMAND_WORD = "event";
    private static final String ERROR_MESSAGE_ADD_EVENT= "Unless you download more ram, it's:"
            + System.lineSeparator() + Messages.EVENT_FORMAT;
    private static final String EVENT_DELIMITER = " /at ";
    private static final String WRITE_FILE_ERROR = "Unable to save tasks to file";

    /**
     * Constructor for command AddEventCommand.
     *
     * @param userInput event arguments provided by user
     * @throws DukeException if wrong arguments were provided by user
     */
    public AddEventCommand(String userInput) throws DukeException {
        try {
            String[] eventDetails = userInput.split(EVENT_DELIMITER);
            String eventName = eventDetails[0];
            String eventAt = eventDetails[1];
            event = new Event(eventName, eventAt);
        } catch(IndexOutOfBoundsException e) {
            throw new DukeException(ERROR_MESSAGE_ADD_EVENT);
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException{
        tasks.add(event);
        ui.printAddTaskSuccess(event);
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
