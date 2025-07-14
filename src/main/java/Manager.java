import javax.xml.namespace.QName;
import java.util.ArrayList;

public class Manager {
    private ArrayList<Task> tasks = new ArrayList<>();

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public Task getById(int id) {
        return tasks.get(id);
    }

    public void createNewTask(Task task) {
        tasks.add(task);
    }

    public void refresh() {}

    public void deleteById(int id) {
        tasks.remove(id);
    }

    public ArrayList<SubTask> getAllSubTasks(Epic ep) {
        return ep.subTasks;
    }
}

