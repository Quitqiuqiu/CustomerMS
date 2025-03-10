<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ page import="model.User, model.Customer, java.util.List" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("Login.jsp");
    }
    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>客户查询系统</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="text-primary">客户管理系统 <small class="text-muted">用户查询面板</small></h2>
            <a href="logout.jsp" class="btn btn-outline-danger">退出登录</a>
        </div>

        <!-- 搜索表单 -->
        <div class="card card-section">
            <div class="card-header bg-primary text-white">客户查询</div>
            <div class="card-body">
                <form class="row g-3" action="../SearchCustomerServlet" method="GET">
                    <div class="col-md-4">
                        <input type="text" class="form-control" name="name" placeholder="客户姓名">
                    </div>
                    <div class="col-md-4">
                        <input type="text" class="form-control" name="companyName" placeholder="公司名称">
                    </div>
                    <div class="col-md-3">
                        <select class="form-select" name="level">
                            <option value="">所有级别</option>
                            <option value="高">高</option>
                            <option value="中">中</option>
                            <option value="低">低</option>
                        </select>
                    </div>
                    <div class="col-md-1">
                        <button type="submit" class="btn btn-primary w-100">搜索</button>
                    </div>
                </form>
            </div>
        </div>

        <!-- 客户表格 -->
        <div class="card card-section">
            <div class="card-header bg-success text-white d-flex justify-content-between align-items-center">
                <span>客户列表</span>
                <span>共 <%= customers != null ? customers.size() : 0 %> 条记录</span>
            </div>
            <div class="card-body p-0">
                <table class="table table-striped table-hover m-0">
                    <thead class="table-dark">
                        <tr>
                            <th>姓名</th>
                            <th>公司</th>
                            <th>级别</th>
                            <th>联系人</th>
                            <th>联系方式</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if(customers != null) { 
                            for(Customer c : customers) { %>
                            <tr>
                                <td><%= c.getName() %></td>
                                <td><%= c.getCompanyName() %></td>
                                <td><%= c.getCustomerLevel() %></td>
                                <td><%= c.getContactName() %></td>
                                <td><%= c.getContactPhone() %></td>
                            </tr>
                        <% } } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
