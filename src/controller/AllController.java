package controller;

import model.Task;
import model.User;
import repository.TaskRepository;
import repository.UserRepository;
import view.TaskView;
import view.UserView;

import java.time.LocalDate;
import java.util.List;

public class AllController {

    private User connectedUser = null;
    private final UserRepository userRepository;
    private final UserView userView;
    private final TaskView taskView;
    private final TaskRepository taskRepository;

    public AllController(UserRepository userRepository, UserView userView, TaskView taskView, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.userView = userView;
        this.taskView = taskView;
        this.taskRepository = taskRepository;
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
                        } else {
                            userView.showMessage("User not found");
                        }
                        break;

                    case 3:
                        this.connectedUser = null;
                        break;
                }
            }
            if (connectedUser != null) {
                int choice = taskView.defaultMenue();
                System.out.println("### Welcome ###");

                switch (choice) {

                    case 1:
                        String tskTitle = taskView.getTaskTitle();
                        Task tskModel = new Task(tskTitle, null, true, LocalDate.now(), connectedUser);
                        taskRepository.createTask(tskModel);
                        taskView.showMessage("Task saved in database");
                        break;

                    case 2:
                        List<Task> listAll = taskRepository.getAllTask();
                        taskView.displayTasks(listAll);
                        break;

                    case 3:
                        List<Task> tasksToDelete = taskRepository.getAllTask();
                        taskView.displayTasks(tasksToDelete);

                        int idToDelete = taskView.askTaskId();
                        taskRepository.deleteTask(idToDelete);
                        taskView.showMessage("Task deleted.");
                        break;

                    case 4:
                        taskView.showMessage("Enter the name of the task you want to update");
                        String tskTitleToend = taskView.getTaskTitle();
                        Task taskFound = taskRepository.getTaskByName(tskTitleToend);

                        if (taskFound != null) {

                            taskFound.setTaskStatus(true);

                            taskRepository.updateTask(taskFound.getTitle(), taskFound.getTaskId(), true);

                            taskView.showMessage("Task status updated in database.");
                        } else {
                            taskView.showMessage("Task not found.");
                        }
                        break;

                    case 5:
                        taskView.showMessage("### Loading your data...###");
                        List<Task> myTasks = taskRepository.getAllTaskByUsr(connectedUser);
                        taskView.displayTasks(myTasks);
                        break;

                    case 6:
                        taskView.showMessage("### Logging out... ###");
                        this.connectedUser = null;
                        break;
                }


            }
        }
    }
}