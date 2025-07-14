public class Main {

    public static void main(String[] args) {
        Task task = new Epic("уроки","Нужно сделать уроки", "IN_PROGRESS");
        Task task2 = new Epic("уроки","Нужно сделать уроки", "IN_PROGRESS");

        Manager manager = new Manager();
        manager.createNewTask(task);
        manager.createNewTask(task2);

        System.out.println(manager.getById(1));
    }
}
