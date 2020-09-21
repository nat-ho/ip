package duke.exception;

/**
 * Exception for Duke inheriting from Exception class
 */
public class DukeException extends Exception{
    /**
     * Creates a DukeException object to throw.
     *
     * @param errorMessage Error message to describe exception
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
