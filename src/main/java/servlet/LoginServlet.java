package servlet;

import java.io.IOException;
import java.util.List;
import service.CustomerRepository;  // 使用你提供的 CustomerRepository
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import model.Customer;

@WebServlet("/JSP/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // 打印输入的用户名、密码和角色，帮助调试
        System.out.println("登录尝试 - 用户名: " + username + ", 密码: " + password + ", 角色: " + role);

        UserDAO userDAO = new UserDAO();
        User user = userDAO.validateUser(username, password, role);

        if (user != null) {
            // 用户存在且验证通过
            System.out.println("用户验证成功: " + user.getUsername() + ", 角色: " + user.getRole());
            HttpSession session = request.getSession();
            session.setAttribute("user", user);  // 将用户信息存入Session

            // 如果是管理员，获取客户列表并转发到管理员主页
            if ("admin".equals(user.getRole())) {
                // 获取所有客户数据
                CustomerRepository customerRepository = new CustomerRepository();
                List<Customer> customers = customerRepository.getAllCustomers();  // 获取所有客户

                // 将客户数据设置为请求属性
                request.setAttribute("customers", customers);

                // 转发到 Admin.jsp
                request.getRequestDispatcher("/JSP/Admin.jsp").forward(request, response);
            } else {
                // 如果是客户，跳转到客户主页
            	 CustomerRepository customerRepository = new CustomerRepository();
                 List<Customer> customers = customerRepository.getAllCustomers();  // 获取所有客户

                 // 将客户数据设置为请求属性
                 request.setAttribute("customers", customers);

                 // 转发到 Admin.jsp
                 request.getRequestDispatcher("Customer.jsp").forward(request, response);
            }
        } else {
            // 登录失败，返回登录页面并显示错误信息
            System.out.println("用户验证失败");
            response.sendRedirect("Login.jsp?error=true");
        }
    }
}
