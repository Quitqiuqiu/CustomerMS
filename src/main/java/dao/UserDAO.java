package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import util.DBConnection;

public class UserDAO {

    // 验证用户的方法
    public User validateUser(String username, String password, String role) {
        User user = null;
        // 获取数据库连接
        Connection conn = DBConnection.getConnection();

        if (conn == null) {
            System.out.println("数据库连接失败，无法执行查询");
            return null;
        }

        String query = "SELECT * FROM user WHERE username = ? AND password = ? AND role = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            System.out.println("查询失败");
            e.printStackTrace();
        }

        return user;
    }

    // main 方法，用于调试
    public static void main(String[] args) {
        // 创建 UserDAO 实例
        UserDAO userDAO = new UserDAO();
        
        // 调用 validateUser 方法，传入用户名、密码和角色进行测试
        User user = userDAO.validateUser("Mary", "M040218", "admin");

        // 输出测试结果
        if (user != null) {
            System.out.println("用户验证成功");
            System.out.println("用户名: " + user.getUsername());
            System.out.println("角色: " + user.getRole());
        } else {
            System.out.println("验证失败");
        }
    }
}
