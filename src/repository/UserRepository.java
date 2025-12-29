package repository;

import model.User;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private final DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createUserTable() {
        try {
            Connection con = dataSource.getConnection();
            String sql = """
                            CREATE TABLE IF NOT EXISTS
                            user (
                            userId INTEGER PRIMARY KEY AUTO_INCREMENT,
                            name TEXT NOT NULL)
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

    public void createUser(User userToAdd){
        try {
            Connection con = dataSource.getConnection();

            String sql = """
                        INSERT INTO user (name) VALUES (?)
                        """;

            try(PreparedStatement pStatement = con.prepareStatement(sql)) {
                pStatement.setString(1, userToAdd.getName());

                pStatement.executeUpdate();

                pStatement.close();
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUser() {
        ArrayList<User> users = new ArrayList<>();

        try {
            Connection con = dataSource.getConnection();
            Statement statement = con.createStatement();
            String sql = """
                    SELECT *
                    FROM
                    user
                    """;
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Integer userId = rs.getInt("userId");
                String name = rs.getString("name");

                User userToList = new User(userId, name);

                users.add(userToList);
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserByName(String Usrname) {
        User user = null;
        try {
            Connection con = dataSource.getConnection();
            String sql = """
                    SELECT
                    *
                    FROM user
                    WHERE name = ?""";

            try (PreparedStatement pStatement = con.prepareStatement(sql)) {
                pStatement.setString(1, Usrname);
                ResultSet rs = pStatement.executeQuery();

                while (rs.next()) {
                    Integer userId = rs.getInt("userId");
                    String name = rs.getString("name");
                    user = new User(userId, name);
                }
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUserById(Integer id) {
        User user = null;
        try {
            Connection con = dataSource.getConnection();
            String sql = """
                    SELECT
                    *
                    FROM user
                    WHERE userId = ?
                    """;

            try (PreparedStatement pStatement = con.prepareStatement(sql)) {
                pStatement.setInt(1, id);
                ResultSet rs = pStatement.executeQuery();

                while (rs.next()) {
                    Integer userId = rs.getInt("userId");
                    String name = rs.getString("name");
                    user = new User(userId, name);
                }
            }
            con.close();
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

            try (PreparedStatement pStatement = con.prepareStatement(sql)) {
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
        try {
            Connection con = dataSource.getConnection();
            String sql = """
                    DELETE FROM user
                    WHERE
                    userId = ?""";

            try (PreparedStatement pStatement = con.prepareStatement(sql)) {
                pStatement.setInt(1, id);
                pStatement.executeUpdate();

                pStatement.close();
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}