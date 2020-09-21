package duke.task;

import duke.exception.DukeException;
import duke.parser.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private String by;
    private LocalDateTime datetime;

    private static final String ERROR_WRONG_DATE_FORMAT = "Date time format error. Try: " + System.lineSeparator() +
            "<day/month/year hour minutes>  (eg. 21/09/2020 0930)";

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
        try {
            datetime = Parser.parseStringToDateTime(by);
        } catch (DateTimeParseException e) {
            throw new DukeException(ERROR_WRONG_DATE_FORMAT);
        }

    }

    public String getBy() {
        return by;
    }

    public char getType() {
        return 'D';
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (By: " + Parser.parseDateTimeToString(datetime) + ")";
    }
}
