<%@ page import="dao.UserDAO" %>
<%@ page import="models.UserModel" %><%--
  Created by IntelliJ IDEA.
  User: thngtrn
  Date: 25/11/2022
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div class="row">
        <div class="col-4">

        </div>
        <div class="col-8">
            <% UserDAO userDAO = new UserDAO();
                UserModel user = userDAO.findOneByEmail((String) session.getAttribute("email")); %>
            <form action="" method="post">
                <div class="mb-3">
                    <label for="fullname" class="form-label">Full name</label>

                    <input type="text" name="fullname" id="fullname" class="form-control" value="<%=user.getName()%>">

                </div>

                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" name="email" id="email" class="form-control" value="<%=user.getEmail()%>">
                </div>

                <div class="password-container">
                    <div class="mb-3">
                        <label for="oldPassword" class="form-label">Old password</label>
                        <input type="password" name="oldPassword" id="oldPassword" class="form-control">
                    </div>

                    <div class="mb-3">
                        <label for="newPassword" class="form-label">New password</label>
                        <input type="password" name="newPassword" id="newPassword" class="form-control">
                    </div>
                </div>
                <input type="submit" value="Update" class="btn btn-success">
            </form>
        </div>
    </div>
</div>
