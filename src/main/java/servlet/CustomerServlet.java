package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Customer;
import service.CustomerService;

public class CustomerServlet extends HttpServlet {
	private CustomerService customerService = new CustomerService();

	// 显示所有客户
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("delete".equals(action)) {
			deleteCustomer(request, response);
		} else {
			// 获取所有客户
			List<Customer> customers = customerService.getAllCustomers();

			// 调试输出：检查获取到的客户列表是否为空
			System.out.println("Debug: Fetched customers - " + customers.size() + " customers found.");

			// 将客户列表传递给JSP页面
			request.setAttribute("customers", customers);

			// 获取当前请求来源页面，决定转发到哪个页面
			String referer = request.getHeader("referer");

			if (referer != null && referer.contains("/JSP/Admin.jsp")) {
				// 如果来源是admin.jsp，转发到admin.jsp
				request.getRequestDispatcher("/JSP/Admin.jsp").forward(request, response);
			} else {
				// 如果来源是customer.jsp，转发到customer.jsp
				request.getRequestDispatcher("/JSP/Customer.jsp").forward(request, response);
			}
		}
	}

	// 删除客户
	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		customerService.deleteCustomer(id); // 调用服务层删除客户

		// 删除完成后，重新加载客户列表并转发到admin.jsp
		List<Customer> customers = customerService.getAllCustomers(); // 获取所有客户
		request.setAttribute("customers", customers);

		// 转发到管理员页面
		request.getRequestDispatcher("/JSP/Admin.jsp").forward(request, response);
	}

	// 添加客户
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("add".equals(action)) {
			addCustomer(request, response);
		} else if ("update".equals(action)) {
			updateCustomer(request, response);
		}
	}

	// 添加客户
	private void addCustomer(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String name = request.getParameter("name");
		String companyName = request.getParameter("companyName");
		String contactName = request.getParameter("contactName");
		String contactPhone = request.getParameter("contactPhone");
		String level = request.getParameter("level");

		// 创建新客户对象
		Customer newCustomer = new Customer(0, name, companyName, contactName, contactPhone, level);
		customerService.addCustomer(newCustomer); // 调用服务层添加客户

		// 添加完成后，重新加载客户列表并转发到admin.jsp
		List<Customer> customers = customerService.getAllCustomers(); // 获取所有客户
		request.setAttribute("customers", customers);

		// 转发到管理员页面
		request.getRequestDispatcher("/JSP/Admin.jsp").forward(request, response);

	}

	// 更新客户信息
    private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String idStr = request.getParameter("id"); // 获取传入的客户id
        String name = request.getParameter("name");
        String companyName = request.getParameter("companyName");
        String contactName = request.getParameter("contactName");
        String contactPhone = request.getParameter("contactPhone");
        String level = request.getParameter("level");

        // 判断id是否为空或者无效
        if (idStr == null || idStr.isEmpty()) {
            // 返回错误提示，id不能为空
            response.sendRedirect("Admin.jsp?error=Customer ID cannot be empty");
            return;
        }

        try {
            // 尝试将id转换为整数
            int id = Integer.parseInt(idStr);

            // 创建更新后的客户对象
            Customer updatedCustomer = new Customer(id, name, companyName, contactName, contactPhone, level);
            customerService.updateCustomer(updatedCustomer); // 调用服务层更新客户

            // 更新完成后，重新加载客户列表并转发到admin.jsp
            List<Customer> customers = customerService.getAllCustomers(); // 获取所有客户
            request.setAttribute("customers", customers);

            // 转发到管理员页面
            request.getRequestDispatcher("/JSP/Admin.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            // 如果id格式不正确，返回错误提示
            response.sendRedirect("Admin.jsp?error=Invalid Customer ID format");
        }
    }

}
