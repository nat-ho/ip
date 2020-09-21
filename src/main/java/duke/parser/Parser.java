package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.ui.Messages;

/**
 * Parses user input for Duke
 */
public class Parser {
    private static final String ERROR_MESSAGE_BLANK_INPUT = "Doing nothing is hard, you never know when you're done";
    private static final String ERROR_MESSAGE_UNRECOGNISED_COMMAND = "If only I could add \"Read instruction " +
            "manual\". Regardless, here it is: " + System.lineSeparator() + Messages.ALL_FORMATS;

    /**
     * Parses user input into command for execution.
     *
     * @param userInput input entered by user
     * @return Command object based on the user input
     * @throws DukeException unrecognised input or exception caused during command object creation
     */
    public static Command parse(String userInput) throws DukeException {
        String[] userInputArray = userInput.split(" ", 2);
        if (userInputArray.length != 2) {
            userInputArray = new String[]{userInputArray[0], ""};
        }
        String command = userInputArray[0].trim().toLowerCase();
        String details = userInputArray[1].trim();

        switch(command){
        case AddToDoCommand.COMMAND_WORD:
            return new AddToDoCommand(details);
        case AddDeadlineCommand.COMMAND_WORD:
            return new AddDeadlineCommand(details);
        case AddEventCommand.COMMAND_WORD:
            return new AddEventCommand(details);
        case DoneCommand.COMMAND_WORD:
            return new DoneCommand(details);
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(details);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case GoodbyeCommand.COMMAND_WORD:
            return new GoodbyeCommand();
        default:
            if (command.equals("")) {
                throw new DukeException(ERROR_MESSAGE_BLANK_INPUT);
            } else {
                throw new DukeException(ERROR_MESSAGE_UNRECOGNISED_COMMAND);
            }
        }
    }
}
