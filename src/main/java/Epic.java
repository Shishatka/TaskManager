import java.util.ArrayList;
import java.util.List;

public class Epic extends Task{
    private final List<Integer> subTasksIds = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
    }

    public List<Integer> getSubtasksIds() {
        return subTasksIds;
    }
}
