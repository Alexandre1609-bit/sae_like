import com.mysql.cj.jdbc.MysqlDataSource;
import repository.TaskRepository;
import repository.UserRepository;
import view.TaskView;
import view.UserView;
import controller.AllController;

import java.sql.Connection;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/taskmanager?serverTimezone=UTC");
        dataSource.setUser("root");
        dataSource.setPassword("");

        UserRepository userRepo = new UserRepository(dataSource);
        TaskRepository taskRepo = new TaskRepository(dataSource);

        userRepo.createUserTable();
        taskRepo.createTaskTable();

        UserView usrView = new UserView();
        TaskView tskView = new TaskView();
        AllController allctrl = new AllController(userRepo, usrView, tskView, taskRepo);

        System.out.println(">>> Application réinitialisée et prête !");
        allctrl.start();
    }
}