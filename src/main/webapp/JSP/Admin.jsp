<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ page session="true" %>
<%@ page import="model.User, model.Customer, java.util.List" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"admin".equals(user.getRole())) {
        response.sendRedirect("Login.jsp");
    }
    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>客户管理系统 - 管理员</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .card-section { margin: 20px 0; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        .table-hover tbody tr:hover { background-color: #f8f9fa; }
        .action-buttons .btn { margin: 0 3px; padding: 5px 10px; }
    </style>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <!-- 标题和退出 -->
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="text-primary">客户管理系统 <small class="text-muted">管理员面板</small></h2>
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
                            <th>操作</th>
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
                                <td>
                                    <a href="../CustomerServlet?action=delete&id=<%= c.getId() %>" class="btn btn-danger btn-sm">删除</a>
                                    <button class="btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#editModal" 
                                        data-id="<%= c.getId() %>" 
                                        data-name="<%= c.getName() %>" 
                                        data-company="<%= c.getCompanyName() %>" 
                                        data-level="<%= c.getCustomerLevel() %>" 
                                        data-contact-name="<%= c.getContactName() %>" 
                                        data-contact-phone="<%= c.getContactPhone() %>">
                                        编辑
                                    </button>
                                </td>
                            </tr>
                        <% } } %>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- 添加客户表单 -->
        <div class="card card-section">
            <div class="card-header bg-info text-white">添加新客户</div>
            <div class="card-body">
                <form action="../CustomerServlet" method="POST" class="row g-3">
                    <input type="hidden" name="action" value="add">
                    <div class="col-md-3">
                        <input type="text" class="form-control" name="name" placeholder="客户姓名" required>
                    </div>
                    <div class="col-md-3">
                        <input type="text" class="form-control" name="companyName" placeholder="公司名称" required>
                    </div>
                    <div class="col-md-2">
                        <select class="form-select" name="level">
                            <option value="高">高</option>
                            <option value="中">中</option>
                            <option value="低">低</option>
                        </select>
                    </div>
                    <div class="col-md-2">
                        <input type="text" class="form-control" name="contactName" placeholder="联系人" required>
                    </div>
                    <div class="col-md-2">
                        <input type="text" class="form-control" name="contactPhone" placeholder="联系方式" required>
                    </div>
                    <div class="col-12">
                        <button type="submit" class="btn btn-success">添加客户</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- 编辑模态框 -->
    <div class="modal fade" id="editModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="../CustomerServlet" method="POST">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="id" id="editId">
                    <div class="modal-header">
                        <h5 class="modal-title">编辑客户信息</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <label class="form-label">客户姓名</label>
                            <input type="text" class="form-control" name="name" id="editName" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">公司名称</label>
                            <input type="text" class="form-control" name="companyName" id="editCompany" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">客户级别</label>
                            <select class="form-select" name="level" id="editLevel">
                                <option value="高">高</option>
                                <option value="中">中</option>
                                <option value="低">低</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">联系人</label>
                            <input type="text" class="form-control" name="contactName" id="editContactName" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">联系方式</label>
                            <input type="text" class="form-control" name="contactPhone" id="editContactPhone" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                        <button type="submit" class="btn btn-primary">保存修改</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
