package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Customer;
import service.CustomerService;
@WebServlet("/JSP/admin")
public class SearchCustomerServlet extends HttpServlet {
    private CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取查询条件
        String name = request.getParameter("name");  // 客户姓名
        String companyName = request.getParameter("companyName");  // 公司名称
        String level = request.getParameter("level");  // 客户级别
        String contactPhone = request.getParameter("contactPhone");  // 联系方式

        // 调用CustomerService获取查询结果
        List<Customer> customers = customerService.searchCustomers(name, companyName, level, contactPhone);

        // 将查询结果存储到request中，以便JSP显示
        request.setAttribute("customers", customers);

        // 获取当前请求来源页面，决定转发到哪个页面
        String referer = request.getHeader("referer");

        if (referer != null && referer.contains("JSP/Admin.jsp")) {
            // 如果来源是admin.jsp，转发到admin.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/Admin.jsp");
            dispatcher.forward(request, response);
        } else {
            // 如果来源是customer.jsp，转发到customer.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/Customer.jsp");
            dispatcher.forward(request, response);
        }
    }
}
