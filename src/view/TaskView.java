package view;

import controller.AllController;
import model.Task;
import repository.UserRepository;

import java.util.List;
import java.util.Scanner;

public class TaskView {
    private Scanner scanner = new Scanner(System.in);


    public TaskView() {
    }

    public int defaultMenue(){
        System.out.println("Select the action you want to perform");
        System.out.println("1. Create task");
        System.out.println("2. Show tasks");
        System.out.println("3. Delete task");
        System.out.println("4. Update task's status");
        System.out.println("5. Show my taks");
        System.out.println("6. Quit");

        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public String getTaskTitle() {
        System.out.println("Enter the task's title");
        String tskTitle = scanner.nextLine();
        return tskTitle;
    }


    public String getTaskDesc() {
        System.out.println("Feel free to write a short description");
        String tskDecp = scanner.nextLine();
        return tskDecp;
    }


    public void displayTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Aucune tâche disponible.");
            return;
        }

        System.out.println("### LISTE DES TÂCHES ###");
        for (Task t : tasks) {
            System.out.println("[ID: " + t.getTaskId() + "] - " + t.getTitle());
        }
        System.out.println("------------------------");
    }

    public int askTaskId() {
        System.out.println("Entrez l'ID de la tâche à supprimer :");
        return Integer.parseInt(scanner.nextLine());
    }


    public void showMessage (String msg) {
        System.out.println(msg);
    }
}
