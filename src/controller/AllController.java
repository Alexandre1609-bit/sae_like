package controller;

import model.User;
import repository.UserRepository;
import view.UserView;

public class AllController {

    private User connectedUser = null;
    private final UserRepository userRepository;
    private final UserView userView;

    public AllController(UserRepository userRepository, UserView userView) {
        this.userRepository = userRepository;
        this.userView = userView;
    }

    public void start() {
        boolean running = true;

        while (running) {

            if (connectedUser == null) {
                int choice = userView.startMenue();

            System.out.println("=== Welcome ===");
            switch (choice) {


                case 1:
                    String name = userView.askName();
                    User user = new User(null, name);
                    userRepository.createUser(user);
                    userView.showMessage("You are now registered.");
                    break;

                case 2:
                    userView.showMessage("Enter you name to login");
                    String nameLog = userView.askName();
                    User userFnd = userRepository.getUserByName(nameLog);
                    if (userFnd != null) {
                        this.connectedUser = userFnd;
                    }

                case 3:
                    this.connectedUser = null;
                    break;
            }
            }
            if (connectedUser != null) {}

        }
    }
}