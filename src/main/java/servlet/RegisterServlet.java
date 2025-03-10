package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.DBConnection;

@WebServlet("/JSP/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = request.getParameter("email");
        String role = request.getParameter("role");

        // 验证密码是否一致
        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "密码不一致");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 获取数据库连接
            conn = DBConnection.getConnection();

            // 检查用户名是否已经存在
            String checkSql = "SELECT username FROM user WHERE username = ?";
            pstmt = conn.prepareStatement(checkSql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // 用户名已存在，返回注册页面并显示错误信息
                request.setAttribute("error", "用户名已存在");
                request.getRequestDispatcher("Register.jsp").forward(request, response);
                return;
            }

            // 插入新用户
            String insertSql = "INSERT INTO user (username, password, email, role) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(insertSql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);  // 实际应用中应对密码进行加密存储（例如使用BCrypt）
            pstmt.setString(3, email);
            pstmt.setString(4, role);

            int result = pstmt.executeUpdate();

            if (result > 0) {
                // 注册成功，重定向到登录页面
                response.sendRedirect("Login.jsp");
            } else {
                // 插入失败，返回注册页面并显示错误信息
                request.setAttribute("error", "注册失败，请稍后重试");
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            // 处理SQL异常
            e.printStackTrace();
            request.setAttribute("error", "数据库错误，请稍后重试");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        } finally {
            // 关闭数据库资源
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}