# User Guide
Duke is a simple command line application that helps you track day to day tasks

## Duke Quick Start
1. Ensure that **Java 11** or above is installed on your computer

2. Head over [here](https://github.com/nat-ho/ip/releases) to download the latest release of Duke

3. Move the JAR file to your desired location, preferably in an empty folder

4. **Windows Users**
    * Launch Command Line
    * Right click on the Command prompt title bar, select properties and change the font to NSimSun
    * Navigate to the directory where the Duke application is located
    * Change Windows default code page to UTF-8 with ```chcp 65001```
    * Run the command ```java -Dfile.encoding=UTF-8 -jar Duke.jar```

5. **Linux and macOS Users**
    * Launch Terminal
    * Navigate to the directory where the Duke application is located
    * Run the command ```java -jar Duke.jar```

6. Duke will startup, and you should see the following welcome text:

    ```
    _________________________________________________________________________
      _  _     ___    _____   _____    ___
     | \| |   /   \  |_   _| |_   _|  / _ \
     | .` |   | - |    | |     | |   | (_) |
     |_|\_|   |_|_|   _|_|_   _|_|_   \___/
    _|"""""|_|"""""|_|"""""|_|"""""|_|"""""|
    "`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'
    
    Top of the mornin' to ya! Name's Natto
    Need a pint, two, or somethin' else?
    _________________________________________________________________________
    ```
   
7. You can now enter commands for Duke to execute. 

8. Refer to the usage guide below for a more detailed explanation on Duke's command list



## Features 

#### Task Manager
Duke manages a list of tasks that can be added to, deleted, checked off, and also provides a search function

#### Persistent Data
Any changes to the task list will be saved in a file locally which will be loaded on Duke's startup 


## Usage

### Adding a ToDo task - `todo`

Adds a ToDo task to the task list with completion status as false. 

**Command:** 

`todo task_name`

**Example of usage:** 

`todo throw out the trash`

**Expected outcome:**
```
_________________________________________________________________________
Added that one to the list:
[T][✘]throw out the trash
You've got 1 remaining tasks
_________________________________________________________________________
```


### Adding a Deadline task - `deadline`

Adds a deadline task to the task list with completion status as false. 

**Command:** 

`deadline task_name /by deadline_info`

`deadline task_name /by day/month/year hourMinute`

**Example of usage:** 

`deadline complete IP level 9 /by 04/10/2020 2359`

**Expected outcome:**
```
_________________________________________________________________________
Added that one to the list:
[D][✘]complete IP level 9 (By: Oct 04 2020 11:59PM)
You've got 2 remaining tasks
_________________________________________________________________________
```


### Adding an Event task - `event`

Adds an event task to the task list with completion status as false. 

**Command:** 

`event task_name /at event_info`

**Example of usage:** 

`event table tennis training /at OCBC Arena Monday 0930`

**Expected outcome:**
```
_________________________________________________________________________
Added that one to the list:
[E][✘]table tennis training (At: OCBC Arena Monday 0930)
You've got 3 remaining tasks
_________________________________________________________________________
```


### Listing all tasks - `list`

Displays all tasks currently in the task list. 

**Command:** 

`list`

**Example of usage:** 

`list`

**Expected outcome:**
```
_________________________________________________________________________
1. [T][✘]throw out the trash
2. [D][✘]complete IP level 9 (By: Oct 04 2020 11:59PM)
3. [E][✘]table tennis training (At: OCBC Arena Monday 0930)
_________________________________________________________________________
```


### Marking a task as done - `done`

Marks a task's completion status as done. 

**Command:** 

`done task_number`

**Example of usage:** 

`done 1`

**Expected outcome:**
```
_________________________________________________________________________
Well aren't you Mr Productive! Checked it off for ya:
[T][✓]throw out the trash
_________________________________________________________________________
```


### Deleting a task - `delete`

Deletes a task from the task list. 

**Command:** 

`delete task_number`

**Example of usage:** 

`delete 2`

**Expected outcome:**
```
_________________________________________________________________________
You've removed the task but not my disappointment:
[D][✘]complete IP level 9 (By: Oct 04 2020 11:59PM)
You've got 2 more tasks to delete instead of complete
_________________________________________________________________________
```


### Finding a task - `find`

Finds and displays all tasks that contains the search keyword. 

**Command:** 

`find keyword`

**Example of usage:** 

`find training`

**Expected outcome:**
```
_________________________________________________________________________
Sherlock Holmes at your service! It was the butler!
Here are my findings:
1. [E][✘]table tennis training (At: OCBC Arena Monday 0930)
_________________________________________________________________________
```


### Exiting Duke - `bye`

Terminates the application and exits Duke.

**Command:** 

`bye`

**Example of usage:** 

`bye`

**Expected outcome:**
```
_________________________________________________________________________
What, so everyone's supposed to sleep every night now?
You realize that nighttime makes up half of all time?
_________________________________________________________________________
```