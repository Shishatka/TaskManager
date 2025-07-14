import java.util.ArrayList;

public class Epic extends Task{
    public ArrayList<SubTask> subTasks = new ArrayList<>();

    public Epic(String name, String description, String status) {
        super(name, description, status);
        for (SubTask sub : subTasks) {
            if (sub.getStatus().equals("NEW")) {
                status = "NEW";
            }
            else {
                status = "IN_PROGRESS";
                break;
            }
        }

        for (SubTask sub : subTasks) {
            if (sub.getStatus().equals("DONE")) {
                status = "DONE";
            }
            else {
                status = "IN_PROGRESS";
                break;
            }
        }
    }
}
