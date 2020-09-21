package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Messages;
import duke.ui.UI;


public class FindCommand extends Command{
    private String keyword;

    public static final String COMMAND_WORD = "find";
    private static final String ERROR_MESSAGE_FIND = "So you want me to find nothing? Try:" +
            System.lineSeparator() + Messages.FIND_FORMAT;
    private static final String NO_MATCHING_TASKS = "Is your list actually that big you need to search? Try:" +
            System.lineSeparator() + Messages.FIND_FORMAT;

    public FindCommand(String keyword) throws DukeException{
        if(keyword.isEmpty()) {
            throw new DukeException(ERROR_MESSAGE_FIND);
        }
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        String searchResult = "";
        int numberOfMatches = 0;
        for(int i = 0; i < tasks.getTaskCount(); i++) {
            Task task = tasks.getTask(i);
            if(task.getDescription().contains(keyword)) {
                searchResult += (++numberOfMatches) + (". ") + (task.toString()) + System.lineSeparator();
            }
        }
        if(numberOfMatches > 0) {
            ui.printFindTaskSuccess(searchResult.trim(), ui);
        } else {
            throw new DukeException(NO_MATCHING_TASKS);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
