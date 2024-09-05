public class DeleteCommand extends Command {
    private String input;
    public DeleteCommand(String input, boolean isExit) {
        super(isExit);
        this.input = input;
    }
    private boolean isValidIndex(TaskList tasks, String index) throws HienException {
        if (index.isEmpty() || !index.matches("-?(0|[1-9]\\d*)")) {
            throw new HienException("☹ OOPS!!! The index of the task is either empty or not integer. Please try again!");
        }
        int i = Integer.parseInt(index);
        if (i < 1) {
            throw new HienException("☹ OOPS!!! Task index cannot be less than 1");
        } else if (i > tasks.size()) {
            throw new HienException("☹ OOPS!!! Task index cannot be greater than current number of tasks. You currently only have " + tasks.size() + " tasks.");
        } else {
            return true;
        }
    }
    private void deleteTask(TaskList tasks, String input, Storage storage, UI ui) throws HienException {
        String index = input.substring(6).trim();
        if (isValidIndex(tasks, index)) {
            int i = Integer.parseInt(index);
            Task removedTask = tasks.getTask(i - 1);
            tasks.deleteTask(i - 1);
            storage.save(tasks);
            ui.showMessage(" Got it. I've deleted this task:");
            ui.showMessage("   " + removedTask);
            ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
        }
    }


    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws HienException {
        deleteTask(tasks, input, storage, ui);
    }
}
