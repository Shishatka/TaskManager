public class Main {

    public static void main(String[] args) {
        Task task = new Epic("уроки","Нужно сделать уроки");
        Task task2 = new Epic("уроки","Нужно сделать уроки");

        Manager manager = new Manager();


        System.out.println(manager.getById(1));
    }
}
