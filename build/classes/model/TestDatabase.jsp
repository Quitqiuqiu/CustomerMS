<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.sql.Connection,java.sql.DriverManager,java.sql.Statement,java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<title>用户信息列表</title>
</head>
<body>
	<%
	//1.加载对应的MySQL驱动
	Class.forName("com.mysql.cj.jdbc.Driver");
	//2.创建数据库连接，connection 以及连接URL
	String url = "jdbc:mysql://localhost:3306/database_name?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
	Connection conn = DriverManager.getConnection(url, "root", "T17719690352");
	//3.Statement 构建sql语句（CURD）
	Statement sta = conn.createStatement();
	//4.执行SQL，返回结果，查询结果用ResultSet，其他为整型量
	//查询 executeQuery(sql)
	ResultSet rs = sta.executeQuery("select * from user");
	//增 删 改 executeUpdate(sql)
	//sta.executeUpdate(sql);
	//5.释放资源，倒序
	%>
	<table border="1">
		<thead>
			<tr>
				<th>id</th>
				<th>username</th>
				<th>password</th>
				<th>role</th>
				<th>operation</th>
			</tr>
		</thead>
		<tbody>
			<%
			while (rs.next()) {//读取下一条记录，直到最后没有记录为止
			%>
			<tr>
				<td><%=rs.getString("id")%></td>
				<td><%=rs.getString("username")%></td>
				<td><%=rs.getString("password")%></td>
				<td><%=rs.getString("role")%></td>
				<td><a href="#">操作</a></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

    

</body>
</html>