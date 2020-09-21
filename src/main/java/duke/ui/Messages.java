package duke.ui;

public class Messages {
    // Welcome and Goodbye messages
    public static final String DIVIDER_LINE = "____________________________________________" +
            "_____________________________";
    public static final String DUKE_LOGO = "  _  _     ___    _____   _____    ___\n"
            + " | \\| |   /   \\  |_   _| |_   _|  / _ \\\n"
            + " | .` |   | - |    | |     | |   | (_) |\n"
            + " |_|\\_|   |_|_|   _|_|_   _|_|_   \\___/\n"
            + "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|\n"
            + "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\n";
    public static final String MESSAGE_WELCOME = "Top of the mornin' to ya! Name's Natto" + System.lineSeparator()
            + "Need a pint, two, or somethin' else?";
    public static final String MESSAGE_GOODBYE = "What, so everyone's supposed to sleep every night now?" +
            System.lineSeparator() + "You realize that nighttime makes up half of all time?";

    //Task messages
    public static final String MESSAGE_ADD_TASK_SUCCESS = "Added that one to the list: ";
    public static final String MESSAGE_COMPLETE_TASK_SUCCESS = "Well aren't you Mr Productive! Checked it off for ya:";
    public static final String MESSAGE_DELETE_TASK_SUCCESS = "You've removed the task but not my disappointment:";

    public static final String TODO_FORMAT = "todo <todo information>";
    public static final String DEADLINE_FORMAT = "deadline <deadline name> /by <day/month/year hour minutes>";
    public static final String EVENT_FORMAT = "event <event name> /at <event information>";
    public static final String DONE_FORMAT = "done <task number>";
    public static final String DELETE_FORMAT = "delete <task number>";
    public static final String ALL_FORMATS = TODO_FORMAT + System.lineSeparator() + DEADLINE_FORMAT +
            System.lineSeparator() + EVENT_FORMAT + System.lineSeparator() + DONE_FORMAT + System.lineSeparator() +
            DELETE_FORMAT;
}
