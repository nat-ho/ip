package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.UI;

/**
 * Entry point of Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    /**
     * Sets up the required objects.
     * @param filePath save data filepath
     */
    public Duke(String filePath) {
        ui = new UI();
        tasks = new TaskList();
        storage = new Storage(filePath);
    }

    /**
     * Prints welcome message and loads up the data from the storage file.
     */
    public void start() {
        ui.printWelcomeMessage();
        try {
            storage.loadSaveData(tasks);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        tasks.printAllTasks(ui);
    }

    /**
     * Prints goodbye message and exits.
     */
    public void exit() {
        ui.printGoodbyeMessage();
        System.exit(0);
    }

    /**
     * Reads the user command and executes it, until the user issues the exit command.
     */
    public void runLoopUntilExitCommand() {
        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand = ui.readUserCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printTextWithDividerLine(e.getMessage());
            }
        }
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        start();
        runLoopUntilExitCommand();
        exit();
    }

    /**
     * Main method of Duke
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/TaskList.txt").run();
    }
}