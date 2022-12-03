<%@ page import="models.UserModel" %><%--
  Created by IntelliJ IDEA.
  User: thngtrn
  Date: 25/11/2022
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserModel user = (UserModel) session.getAttribute("user");
    %>
<h2>Welcome <%=user.getName()%></h2>
<div>
    <p>Task management, planing</p>
    <a href="/boards">Let's go</a>
</div>
    <%
%>

