public class Task {
    private String name;
    private String description;
    private int id;
    private String status;
    private static int count = 0;

    public Task(String name, String description, String status) {
        this.name = name;
        this.description = description;
        this.id = count++;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "name: " + name + "\ndescription: " + description + "\nstatus: " + status;
    }
}
