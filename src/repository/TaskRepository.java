package repository;

import model.Task;
import model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private final DataSource dataSource;

    public TaskRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createTaskTable() {
        try {
            Connection con = dataSource.getConnection();
            String sql = """
                    CREATE TABLE IF NOT EXISTS
                    task (
                    taskId INTEGER PRIMARY KEY AUTO_INCREMENT,
                    title TEXT NOT NULL,
                    taskStatus BOOLEAN,
                    startDate DATE,
                    accountant TEXT NOT NULL,
                    description VARCHAR(255),
                    userIdFk INTEGER,
                    FOREIGN KEY (userIdFk) REFERENCES user(userId) ON DELETE CASCADE
                    )
                    """;

            try (PreparedStatement pStatement = con.prepareStatement(sql)) {
                pStatement.executeUpdate();
                pStatement.close();
                con.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTask(Task taskToAdd) {
        try {
            Connection con = dataSource.getConnection();
            String sql = """
                    INSERT INTO task (
                    title, taskStatus, startDate, accountant, description )
                    VALUES (?, ?, ?, ?, ?)
                    """;

            try (PreparedStatement pStatement = con.prepareStatement(sql)) {
                pStatement.setString(1, taskToAdd.getTitle());
                pStatement.setBoolean(2, true);
                pStatement.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));

                // Si getAccountant() retourne un Integer, remplace la ligne ci-dessous par:
                // pStatement.setString(4, String.valueOf(taskToAdd.getAccountant()));
                pStatement.setString(4, taskToAdd.getAccountant().getName());

                pStatement.setString(5, null);

                pStatement.executeUpdate();

                pStatement.close();
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Task> getAllTask() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            Connection con = dataSource.getConnection();
            Statement statement = con.createStatement();
            String sql = """
                    SELECT *
                    FROM
                    task
                    """;

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Integer taskId = rs.getInt("taskId");
                String title = rs.getString("title");
                Boolean taskStatus = rs.getBoolean("taskStatus");
                java.time.LocalDate dbSqlDatertDate = rs.getDate("startDate").toLocalDate();
                String accountant = rs.getString("accountant");

                model.User userObject = new model.User(null, accountant);
                Task taskToList = new Task(title, taskId, taskStatus, dbSqlDatertDate, userObject);
                tasks.add(taskToList);
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public List<Task> getAllTaskByUsr(User connectedUser) {
        ArrayList<Task> tasks = new ArrayList<>();

        String sql = "SELECT * FROM task WHERE accountant = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement pstatement = con.prepareStatement(sql)) {

            pstatement.setString(1, connectedUser.getName());

            try (ResultSet rs = pstatement.executeQuery()) {

                while (rs.next()) {
                    Integer taskId = rs.getInt("taskId");
                    String title = rs.getString("title");
                    Boolean taskStatus = rs.getBoolean("taskStatus");
                    java.time.LocalDate dbSqlDate = rs.getDate("startDate").toLocalDate();
                    String accountant = rs.getString("accountant");

                    User userObject = new User(null, accountant);

                    Task taskToList = new Task(title, taskId, taskStatus, dbSqlDate, userObject);

                    tasks.add(taskToList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public Task getTaskByName(String taskname) {
        Task tsk = null;
        try {
            Connection con = dataSource.getConnection();
            String sql = "SELECT * FROM task WHERE title = ?";

            try (PreparedStatement pStatement = con.prepareStatement(sql)) {
                pStatement.setString(1, taskname);
                ResultSet rs = pStatement.executeQuery();

                while (rs.next()) {
                    Integer taskId = rs.getInt("taskId");
                    String title = rs.getString("title");
                    Boolean taskStatus = rs.getBoolean("taskStatus");
                    java.time.LocalDate dbSqlDate = rs.getDate("startDate").toLocalDate();
                    String accountantName = rs.getString("accountant");

                    User userObject = new User(null, accountantName);

                    tsk = new Task();
                    tsk.setTitle(title);
                    tsk.setTaskId(taskId);
                    tsk.setTaskStatus(taskStatus);
                    tsk.setStartDate(dbSqlDate);
                    tsk.setAccountant(userObject);
                }
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tsk;
    }

    public Task getTaskById(Integer id) {
        Task tsk = null;
        try {
            Connection con = dataSource.getConnection();
            String sql = """
                    SELECT
                    *
                    FROM task
                    WHERE taskId = ?
                    """;

            try (PreparedStatement pStatement = con.prepareStatement(sql)) {
                pStatement.setInt(1, id);
                ResultSet rs = pStatement.executeQuery();

                while (rs.next()) {
                    Integer taskId = rs.getInt("taskId");
                    String title = rs.getString("title");
                    Boolean taskStatus = rs.getBoolean("taskStatus");
                    java.time.LocalDate dbSqlDatertDate = rs.getDate("startDate").toLocalDate();
                    String accountant = rs.getString("accountant");

                    model.User userObject = new model.User(null, accountant);

                    tsk = new Task();
                    tsk.setTitle(title);
                    tsk.setTaskId(taskId);
                    tsk.setTaskStatus(taskStatus);
                    tsk.setStartDate(dbSqlDatertDate);
                    tsk.setAccountant(userObject);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tsk;
    }

    public void updateTask(String newTitle, Integer id, boolean bool) {
        try {
            Connection con = dataSource.getConnection();
            String sql = """
                    UPDATE task
                    SET title = ?, taskStatus = ?
                    WHERE
                    taskId = ?
                    """;

            try (PreparedStatement pStatement = con.prepareStatement(sql)) {
                pStatement.setString(1, newTitle);
                pStatement.setBoolean(2, bool);
                pStatement.setInt(3, id);
                pStatement.executeUpdate();

                pStatement.close();
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(Integer id) {
        try {
            Connection con = dataSource.getConnection();
            String sql = """
                    DELETE FROM
                    task
                    WHERE
                    taskId = ?""";

            try (PreparedStatement pStatement = con.prepareStatement(sql)) {
                pStatement.setInt(1, id);
                pStatement.executeUpdate();

                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}