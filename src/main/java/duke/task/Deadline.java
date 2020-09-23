package duke.task;

import duke.parser.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task
 * Subclass from Task superclass
 */
public class Deadline extends Task{
    private String by;
    private LocalDateTime datetime;

    private static final String DEADLINE_FORMAT_REMINDER = "I can read date and time if you need me to: " +
            System.lineSeparator() +  "deadline <deadline name> /by <day/month/year hourMinutes>";

    /**
     * Creates a Deadline object with description and by.
     * Parses String by to a LocalDateTime object if possible
     *
     * @param description description of deadline
     * @param by deadline of task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            datetime = Parser.parseStringToDateTime(by);
        } catch (DateTimeParseException e) {
            // Stores deadline info as String instead of LocalDateTime
            System.out.println(DEADLINE_FORMAT_REMINDER);
        }
    }

    /**
     * Returns deadline information.
     *
     * @return String containing deadline information
     */
    public String getBy() {
        //todo return localdatetime if possible
        return by;
    }

    @Override
    public char getType() {
        return 'D';
    }

    @Override
    public String toString() {
        try {
            return "[D]" + super.toString() + " (By: " + Parser.parseDateTimeToString(datetime) + ")";
        } catch (NullPointerException e) {
            return "[D]" + super.toString() + " (By: " + by + ")";
        }

    }
}
