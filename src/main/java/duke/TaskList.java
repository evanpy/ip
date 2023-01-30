package duke;

import static java.util.stream.Collectors.toCollection;

import java.util.ArrayList;

import duke.task.Task;


public class TaskList {
    private ArrayList<Task> tasks;
    private boolean isInitializingData = true;

    /** A public constructor to initialize TaskList instance. */
    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * A public constructor to initialize TaskList instance.
     *
     * @param tasks A list of tasks.
     * */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /** Adds new task to task list and outputs success message. */
    protected void finishInitialization() {
        this.isInitializingData = false;
    }

    /**
     * Gets specific task.
     *
     * @param taskIndex The index of the task.
     */
    protected Task getTask(int taskIndex) {
        return this.tasks.get(taskIndex);
    }

    /**
     * Gets list of tasks.
     *
     * @return Task list.
     */
    protected ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Adds new task to task list and outputs success message.
     *
     * @param task Task to be added to task list.
     * @return Success message.
     */
    protected String addTask(Task task) {
        this.tasks.add(task);

        if (this.isInitializingData) {
            return "initializing";
        }

        return Ui.addTaskMsg(task, this.tasks.size());
    }

    /**
     * Removes task from task list and outputs success message.
     *
     * @param taskIndex Index of task to be removed.
     * @return Success message.
     */
    protected String removeTask(int taskIndex) {
        Task task = this.tasks.remove(taskIndex);
        return Ui.removeTaskMsg(task, this.tasks.size());

    }

    /**
     * Outputs all the tasks stored in task list.
     *
     * @return List of tasks message.
     */
    protected String listTasks() {
        String listOfTasks = "";

        for (int idx = 0; idx < tasks.size(); idx++) {
            Task task = this.tasks.get(idx);
            listOfTasks = listOfTasks + "  " + (idx + 1) + "." + task + "\n";
        }

        return Ui.listTasksMsg(listOfTasks);
    }

    /**
     * Marks task as completed and outputs success message.
     *
     * @param task Task to be marked.
     * @return Success message.
     */
    protected String markTask(Task task) {
        task.mark();
        return Ui.markTaskMsg(task);
    }

    /** 
     * Marks task as uncompleted and outputs success message.
     * 
     * @param task Task to be unmarked.
     * @return Success message.
     */
    protected String unmarkTask(Task task) {
        task.unmark();
        return Ui.unmarkTaskMsg(task);
    }

    protected ArrayList<Task> filteredTaskList(String query) {
        ArrayList<Task> filteredList = tasks.stream()
                .filter(task -> task.toString().contains(query))
                .collect(toCollection(ArrayList::new));

        return filteredList;
    }
}
