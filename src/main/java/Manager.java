import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

public abstract class Manager {

    List<Task> getTasks() {
        return null;
    }

    void deleteAllTasks() {}

    void deleteAllSubTasks() {}

    void deleteAllEpics() {}

    Task getById(int id) {
        return null;
    }

    void addTask(Task task) {}

    void addSubTask(SubTask task) {}

    void addEpic(Epic task){}

    void updateTask(Task task) {
    }

    void updateSubTask(Task task){
    }

    void updateEpic(Task task){
    }

    void deleteTask(int id) {}

    void deleteEpic(int id) {}

    void deleteSubTask(int id) {}

    List<SubTask> getAllSubTasks(Epic ep) {
        return null;
    }
}

