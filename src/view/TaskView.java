package view;

import controller.AllController;
import repository.UserRepository;

import java.util.Scanner;

public class TaskView {
    private Scanner scanner = new Scanner(System.in);


    public TaskView() {
    }



    public void showMessage (String msg) {
        System.out.println(msg);
    }
}
