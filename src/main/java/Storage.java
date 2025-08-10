import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage extends Manager{
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, SubTask> subTasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private int id = 0;

    @Override
    public void addTask(Task task) {
        id++;
        task.setId(id);
        tasks.put(id, task);
    }

    @Override
    public void addSubTask(SubTask subTask){
        id++;
        subTask.setId(id);
        subTasks.put(id, subTask);
    }

    @Override
    public void addEpic(Epic epic){
        id++;
        epic.setId(id);
        epics.put(id, epic);
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public void deleteAllSubTasks() {
        subTasks.clear();
        for (Epic epic : epics.values()) {
            updateEpicStatus(epic);
        }
    }


    @Override
    public void deleteAllEpics() {
        epics.clear();
    }

    @Override
    public void deleteTask(int id) {
        tasks.remove(id);
    }

    @Override
    public void deleteEpic(int id){
        epics.remove(id);
    }

    @Override
    public void deleteSubTask(int id){
        subTasks.remove(id);
    }

    @Override
    public Task getById(int id) {
        return tasks.get(id);
    }

    @Override
    public void updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        } else {
            throw new IllegalArgumentException("Task with id " + task.getId() + " does not exist");
        }
    }

    public void updateSubTask(SubTask subtask) {
        if (subTasks.containsKey(subtask.getId())) {
            SubTask existingSubtask = subTasks.get(subtask.getId());
            int oldEpicId = existingSubtask.getEpicId();
            int newEpicId = subtask.getEpicId();

            if (oldEpicId != newEpicId) {
                if (!epics.containsKey(newEpicId)) {
                    throw new IllegalArgumentException("New epic with id " + newEpicId + " does not exist");
                }
                // Удаляем подзадачу из старого эпика
                Epic oldEpic = epics.get(oldEpicId);
                oldEpic.getSubtasksIds().remove((Integer) subtask.getId());
                updateEpicStatus(oldEpic);

                // Добавляем подзадачу в новый эпик
                Epic newEpic = epics.get(newEpicId);
                newEpic.getSubtasksIds().add(subtask.getId());
                updateEpicStatus(newEpic);
            }

            subTasks.put(subtask.getId(), subtask);
            updateEpicStatus(epics.get(subtask.getEpicId())); // Обновляем статус эпика
        } else {
            throw new IllegalArgumentException("Subtask with id " + subtask.getId() + " does not exist");
        }
    }

    public List<SubTask> getSubtasksByEpicId(int epicId) {
        if (!epics.containsKey(epicId)) {
            throw new IllegalArgumentException("Epic with id " + epicId + " does not exist");
        }
        List<SubTask> result = new ArrayList<>();
        for (int subtaskId : epics.get(epicId).getSubtasksIds()) {
            result.add(subTasks.get(subtaskId));
        }
        return result;
    }

    public void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            Epic existingEpic = epics.get(epic.getId());
            existingEpic.setName(epic.getName());
            existingEpic.setDescription(epic.getDescription());
            // Не обновляем подзадачи и статус здесь, так как это делается в updateEpicStatus
        } else {
            throw new IllegalArgumentException("Epic with id " + epic.getId() + " does not exist");
        }
    }

    private void updateEpicStatus(Epic epic) {
        if (epic.getSubtasksIds().isEmpty()) {
            epic.setStatus(Status.NEW);
            return;
        }

        boolean allDone = true;
        boolean allNew = true;

        for (int subtaskId : epic.getSubtasksIds()) {
            Status subtaskStatus = subTasks.get(subtaskId).getStatus();
            if (subtaskStatus != Status.DONE) {
                allDone = false;
            }
            if (subtaskStatus != Status.NEW) {
                allNew = false;
            }
        }

        if (allDone) {
            epic.setStatus(Status.DONE);
        } else if (allNew) {
            epic.setStatus(Status.NEW);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }
}

