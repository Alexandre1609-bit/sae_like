package view;

import controller.AllController;
import repository.UserRepository;

import java.util.Scanner;

public class UserView {

    private Scanner scanner = new Scanner(System.in);
    private final UserRepository userRepository;
    private final AllController allController;

    public UserView (UserRepository userRepository, AllController allController) {
        this.userRepository = userRepository;
        this.allController = allController;
    }

    public int startMenue() {

        System.out.println("### Welcome ###");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. quit");

        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public String askName() {
        System.out.println("Enter your name");
        return scanner.nextLine();
    }

    public void showMessage (String msg) {
        System.out.println(msg);
    }
}
