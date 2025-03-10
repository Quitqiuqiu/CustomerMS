package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/database_name?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "T17719690352";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 加载 MySQL 驱动
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 检查连接是否成功
            if (connection != null) {
                System.out.println("数据库连接成功！");
            } else {
                System.out.println("数据库连接失败！");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("数据库连接失败，错误信息：" + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    // 用来调试数据库连接的 main 方法
    public static void main(String[] args) {
        Connection connection = DBConnection.getConnection();
        
        if (connection != null) {
            try {
                System.out.println("数据库连接验证成功！");
                connection.close(); // 关闭连接
            } catch (SQLException e) {
                System.out.println("关闭数据库连接时出错：" + e.getMessage());
            }
        }
    }

	public static void close(Connection conn, PreparedStatement pstmt, Object object) {
		// TODO Auto-generated method stub
		
	}
}
