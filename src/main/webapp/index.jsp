<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <%
        if(session.getAttribute("isAuthenticated") != null){
            response.sendRedirect("dashboard.jsp");
        }else{
            response.sendRedirect("login.jsp");

        }
    %>
</body>
</html>