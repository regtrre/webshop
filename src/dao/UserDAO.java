package dao;

import model.User;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // 检查用户名是否已经存在
    public boolean isUsernameTaken(String username) {
        String query = "SELECT COUNT(*) FROM login WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 添加用户到数据库，isAdmin默认为0
    public boolean addUser(User user) {
        String query = "INSERT INTO login (username, password, email, address, isadmin) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getAddress());
            pstmt.setInt(5, user.getIsAdmin());  // 默认值是0

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 验证用户登录
    public boolean validateUser(String username, String password) {
        String query = "SELECT * FROM login WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 获取所有用户信息
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM login"; // 假设表名为 login

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                User user = new User(rs.getString("username"), rs.getString("password"),
                        rs.getString("email"), rs.getString("address"));
                user.setIsAdmin(rs.getInt("isadmin")); // 假设有 isadmin 字段
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // 获取指定用户名的用户信息
    public User getUserByUsername(String username) {
        User user = null;
        String query = "SELECT * FROM login WHERE username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User(rs.getString("username"), rs.getString("password"),
                        rs.getString("email"), rs.getString("address"));
                user.setIsAdmin(rs.getInt("isadmin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // 更新用户信息
    public boolean updateUser(User user) {
        String query = "UPDATE login SET email = ?, address = ?, isadmin = ? WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getAddress());
            pstmt.setInt(3, user.getIsAdmin());
            pstmt.setString(4, user.getUsername());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
