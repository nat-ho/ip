package duke.task;

import duke.exception.DukeException;
import duke.parser.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task
 * Subclass from Task superclass
 */
public class Deadline extends Task{
    private String by;
    private LocalDateTime datetime;

    private static final String ERROR_WRONG_DATE_FORMAT = "Date time format error. Try: " + System.lineSeparator() +
            "<day/month/year hour minutes>  (eg. 21/09/2020 0930)";

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
            //todo include help message to remind date time format to users
//            throw new DukeException(ERROR_WRONG_DATE_FORMAT);
        }
        System.out.println(datetime);
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
