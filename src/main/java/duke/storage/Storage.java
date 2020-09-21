package duke.storage;

import duke.task.*;
import duke.ui.Messages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static File file;

    public  static final String MESSAGE_CREATED_FOLDER = "Data folder created";
    public  static final String MESSAGE_CREATED_SAVE_FILE = "Save file TaskList.txt created";
    public  static final String MESSAGE_CREATE_SAVE_FILE_ERROR = "Error occurred when creating ";
    public  static final String MESSAGE_LOAD_SUCCESS = "Tasks successfully loaded";
    public  static final String MESSAGE_FILE_NOT_FOUND = "TaskList.txt cannot be found";
    private static final String MESSAGE_WRITE_FILE_ERROR = "Unable to write to file ";
    private static final String BACKSLASH = "\\";
    private static final String FORWARD_SLASH = "/";
    private static final String TASK_IDENTIFIER_DELIMITER = " \\| ";
    private static final String SAVE_FILE_DELIMITER = " | ";
    private static final String TASK_IDENTIFIER_TODO = "T";
    private static final String TASK_IDENTIFIER_DEADLINE = "D";
    private static final String TASK_IDENTIFIER_EVENT = "E";
    private static final String TASK_STATUS_COMPLETE = "true";

    public Storage(String filePath) {
        filePath = filePath.replace(FORWARD_SLASH, File.separator).replace(BACKSLASH, File.separator);
        file = new File(filePath);
    }

    public static void loadSaveData(TaskList tasks) {
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                System.out.println(MESSAGE_CREATED_FOLDER);
            }
            if (!file.exists()) {
                file.createNewFile();
                System.out.println(MESSAGE_CREATED_SAVE_FILE);
                System.out.println(Messages.DIVIDER_LINE + System.lineSeparator());
            }
        } catch(IOException e) {
            System.out.println(MESSAGE_CREATE_SAVE_FILE_ERROR + file.getName());
        }

        try {
            readSavedData(tasks);
        } catch(FileNotFoundException e) {
            System.out.println(MESSAGE_FILE_NOT_FOUND);
        }
        if (tasks.getTaskCount() != 0) {
            System.out.println(MESSAGE_LOAD_SUCCESS);
        }
    }

    private static void addToCurrentTasks(TaskList tasks, Task task, String status) {
        if (status.equals(TASK_STATUS_COMPLETE)) {
            task.setDone();
        }
        tasks.add(task);
    }

    private static void readSavedData(TaskList tasks) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
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

    public static void saveTasksToTile(TaskList tasks) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Task task : tasks.getTaskList()) {
                fileWriter.write(parseTaskToSaveFormat(task) + System.lineSeparator());
            }
            fileWriter.close();
        } catch(IOException e) {
            System.out.println(MESSAGE_WRITE_FILE_ERROR + file.getName());
        }
    }
}