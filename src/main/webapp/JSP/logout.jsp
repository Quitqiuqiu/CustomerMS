<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%
    // 销毁当前会话
    session.invalidate();
    
    // 重定向到登录页面
    response.sendRedirect("Login.jsp");
%>
