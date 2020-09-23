package duke.storage;

import duke.exception.DukeException;
import duke.task.*;
import duke.ui.Messages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * File operation including loading and saving data
 */
public class Storage {
    private static File file;

    public  static final String CREATED_FOLDER = "Data folder created";
    public  static final String CREATED_SAVE_FILE = "Save file TaskList.txt created";
    public  static final String CREATE_SAVE_FILE_ERROR = "Error occurred when creating ";
    public  static final String LOAD_SUCCESS = "Tasks successfully loaded";
    public  static final String FILE_NOT_FOUND = "TaskList.txt cannot be found";
    private static final String WRITE_FILE_ERROR = "Unable to write task to file ";
    private static final String TASK_CREATION_ERROR = "Error occurred when creating task from saved data";
    private static final String LOAD_DATA_ERROR = "Unable to load saved data";
    private static final String BACKSLASH = "\\";
    private static final String FORWARD_SLASH = "/";
    private static final String TASK_IDENTIFIER_DELIMITER = " \\| ";
    private static final String SAVE_FILE_DELIMITER = " | ";
    private static final String TASK_IDENTIFIER_TODO = "T";
    private static final String TASK_IDENTIFIER_DEADLINE = "D";
    private static final String TASK_IDENTIFIER_EVENT = "E";
    private static final String TASK_STATUS_COMPLETE = "true";

    /**
     * Creates a Storage object with save data filepath.
     * Saving and loading operation of tasks
     *
     * @param filePath filepath of save data
     */
    public Storage(String filePath) {
        filePath = filePath.replace(FORWARD_SLASH, File.separator).replace(BACKSLASH, File.separator);
        file = new File(filePath);
    }

    /**
     * Creates directory and save file if not created.
     * Loads data from save file into Duke during startup.
     *
     * @param tasks TaskList object used to store Tasks
     */
    public static void loadSaveData(TaskList tasks) throws DukeException{
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                System.out.println(CREATED_FOLDER);
            }
            if (!file.exists()) {
                file.createNewFile();
                System.out.println(CREATED_SAVE_FILE);
                System.out.println(Messages.DIVIDER_LINE + System.lineSeparator());
            }
        } catch(IOException e) {
            throw new DukeException(CREATE_SAVE_FILE_ERROR + file.getName());
        }

        try {
            Scanner reader = new Scanner(file);
            readSavedData(tasks, reader);
        } catch(FileNotFoundException e) {
            throw new DukeException(FILE_NOT_FOUND);
        }
        if (tasks.getTaskCount() != 0) {
            System.out.println(LOAD_SUCCESS);
        }
    }

    /**
     * Adds task created into TaskList.
     *
     * @param tasks TaskList object used to store Tasks
     * @param task Task object to be added into the list
     * @param status String containing completion status of the task
     */
    private static void addToCurrentTasks(TaskList tasks, Task task, String status) {
        if (status.equals(TASK_STATUS_COMPLETE)) {
            task.setDone();
        }
        tasks.add(task);
    }

    /**
     * Parse save data file and creates respective tasks.
     *
     * @param tasks TaskList object used to store Tasks
     * @throws FileNotFoundException
     */
    private static void readSavedData(TaskList tasks, Scanner reader) {
        while(reader.hasNextLine()) {
            String data = reader.nextLine();
            String[] taskInfoArray = data.split(TASK_IDENTIFIER_DELIMITER);

            switch(taskInfoArray[0].trim()) {
            case TASK_IDENTIFIER_TODO:
                ToDo todo = new ToDo(taskInfoArray[2]);
                addToCurrentTasks(tasks, todo, taskInfoArray[1]);
                break;
            case TASK_IDENTIFIER_DEADLINE:
                Deadline deadline = new Deadline(taskInfoArray[2], taskInfoArray[3]);
                addToCurrentTasks(tasks, deadline, taskInfoArray[1]);
                break;
            case TASK_IDENTIFIER_EVENT:
                Event event = new Event(taskInfoArray[2], taskInfoArray[3]);
                addToCurrentTasks(tasks, event, taskInfoArray[1]);
                break;
            default:
                break;
            }
        }
    }

    /**
     * Parses Task object into String to be saved.
     *
     * @param task object in list to be formatted
     * @return String String representation of a task
     */
    public static String parseTaskToSaveFormat(Task task) {
        switch(task.getType()) {
        case 'T':
            return task.getType() + SAVE_FILE_DELIMITER + task.isDone() + SAVE_FILE_DELIMITER
                    + task.getDescription();
        case 'D':
            Deadline d = (Deadline) task;
            return d.getType() + SAVE_FILE_DELIMITER + d.isDone() + SAVE_FILE_DELIMITER
                    + d.getDescription() + SAVE_FILE_DELIMITER + d.getBy();
        case 'E':
            Event e = (Event) task;
            return e.getType() + SAVE_FILE_DELIMITER + e.isDone() + SAVE_FILE_DELIMITER
                    + e.getDescription() + SAVE_FILE_DELIMITER + e.getAt();
        default:
            return "";
        }
    }

    /**
     * Saves current list of tasks into save file.
     *
     * @param tasks TaskList object containing list of tasks currently in Duke
     */
    public static void saveTasksToTile(TaskList tasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Task task : tasks.getTaskList()) {
                fileWriter.write(parseTaskToSaveFormat(task) + System.lineSeparator());
            }
            fileWriter.close();
        } catch(IOException e) {
            throw new DukeException(WRITE_FILE_ERROR + file.getName());
        }
    }
}