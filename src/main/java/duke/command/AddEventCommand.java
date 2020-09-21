package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Messages;
import duke.ui.UI;

public class AddEventCommand extends Command{
    private Event event;

    public static final String COMMAND_WORD = "event";
    private static final String ERROR_MESSAGE_ADD_EVENT= "Unless you download more ram, it's:"
            + System.lineSeparator() + Messages.EVENT_FORMAT;
    private static final String EVENT_DELIMITER = " /at ";

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
    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.add(event);
        ui.printAddTaskSuccess(event);
        storage.saveTasksToTile(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
