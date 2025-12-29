package repository;
import model.Task;
import model.User;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TaskRepository {

    private final DataSource dataSource;

    public TaskRepository (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createTaskTable() {
        try {
            Connection con = dataSource.getConnection();
            String sql = """
                            CREATE TABLE IF NOT EXISTS
                            task (
                            taskId INTEGER PRIMARY KEY,
                            title TEXT NOT NULL,
                            taskStatus BOOLEAN,
                            startDate DATE,
                            accountant TEXT NOT NULL,
                            description VARCHAR(255),
                            userIdFk INTEGER,
                            FOREIGN KEY (userIdFk) REFERENCES user(name) ON DELETE CASCADE
                            )
                            """;

            try(PreparedStatement pStatement = con.prepareStatement(sql)) {
                pStatement.executeUpdate();
                pStatement.close();
                con.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTask(Task taskToAdd, User usr){
        try {
            Connection con = dataSource.getConnection();
            String sql = """
                        INSERT INTO task (
                        taskStatus, startDate, accountant, description )
                        VALUES = (?, ?, ?, ?)
                        """;

            try(PreparedStatement pStatement = con.prepareStatement(sql)) {
                pStatement.setString(1, taskToAdd.getTitle());
                pStatement.setBoolean(2, true);
                pStatement.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
                pStatement.setObject(4, taskToAdd.getAccountant());
                pStatement.setString(5, null);

                pStatement.executeUpdate();

                pStatement.close();
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllTask(){
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
                LocalDate startDate = rs.getDate("startDate");
                String accountant = rs.getString("accountant");
                String description = rs.getString("description");


                Task taskToList = new Task(title, taskId, taskStatus, startDate, accountant);

                tasks.add(taskToList);
            }
            con.close();

        } catch (Exception e ) {
            e.printStackTrace();
        }
        return users;
    }
    public User getUserByName (String Usrname) {
        User user = null;
        try {
            Connection con = dataSource.getConnection();
            String sql = """
                    SELECT 
                    name
                    FROM user
                    WHERE name = ?""";

            try(PreparedStatement pStatement = con.prepareStatement(sql)) {
                pStatement.setString(1, Usrname);
                ResultSet rs = pStatement.getResultSet();

                while (rs.next()) {
                    String name = rs.getString("name");

                    user.setName(name);
                }
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    public User getUserById (Integer id) {
        User user = null;
        try {
            Connection con = dataSource.getConnection();
            String sql = """
                    SELECT
                    *
                    FROM user
                    WHERE userId = ?
                    """;

            try(PreparedStatement pStetament = con.prepareStatement("sql")) {
                pStetament.setInt(1, id);
                ResultSet rs = pStetament.getResultSet();

                while (rs.next()) {
                    Integer userId = rs.getInt("userId");
                    String name = rs.getString("name");

                    user.setUserId(userId);
                    user.setName(name);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updateUser(String newName, Integer id) {
        try {
            Connection con = dataSource.getConnection();
            String sql = """
                        UPDATE user
                        SET name = ?
                        WHERE
                        userId = ?
                        """;

            try(PreparedStatement pStatement = con.prepareStatement(sql)) {
                pStatement.setString(1, newName);
                pStatement.setInt(2, id);
                pStatement.executeUpdate();

                pStatement.close();
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(Integer id) {
        try{
            Connection con = dataSource.getConnection();
            String sql = """
                        DELETE user
                        WHERE
                        id = ?""";

            try(PreparedStatement pStatement = con.prepareStatement(sql)) {
                pStatement.setInt(1, id);
                pStatement.executeUpdate();

                pStatement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


