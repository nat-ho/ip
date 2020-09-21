package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.UI;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Duke(String filePath) {
        ui = new UI();
        tasks = new TaskList();
        storage = new Storage(filePath);
    }

    public void start() {
        ui.printWelcomeMessage();
        try {
            storage.loadSaveData(tasks);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        tasks.printAllTasks(ui);
    }

    public void exit() {
        ui.printGoodbyeMessage();
        System.exit(0);
    }

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

    public void run() {
        start();
        runLoopUntilExitCommand();
        exit();
    }

    public static void main(String[] args) {
        new Duke("data/TaskList.txt").run();
    }
}