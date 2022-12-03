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

        <div class="col-8">
            <%
                UserModel user = (UserModel) session.getAttribute("user");

            %>
             <form action="" id="form-update-profile">
                    <div class="mb-3">
                        <label for="fullname" class="form-label">Full name</label>

                        <input type="text" name="fullname" id="fullname" class="form-control" value="<%=user.getName()%>">

                    </div>

                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" name="email" id="email" disabled class="form-control" value="<%=user.getEmail()%>">
                    </div>
                    <button type="submit"   value="" class="btn btn-success" >Update</button>
                </form>
            <form action="" id="form-reset-password">
                <div class="password-container">
                    <div class="mb-2">
                        <label for="oldPassword" class="form-label">Old password</label>
                        <input type="password" name="oldPassword" id="oldPassword" class="form-control" placeholder="type your old password">
                    </div>

                    <div class="mb-2">
                        <label for="newPassword" class="form-label">New password</label>
                        <input type="password" name="newPassword" id="newPassword" class="form-control" placeholder="new password">
                    </div>
                    <p id="passwordError" style="color: red; display: none"></p>
                </div>
                <input type="submit" value="Reset password" class="btn btn-success">
            </form>
        </div>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.2.0/axios.min.js" integrity="sha512-OdkysyYNjK4CZHgB+dkw9xQp66hZ9TLqmS2vXaBrftfyJeduVhyy1cOfoxiKdi4/bfgpco6REu6Rb+V2oVIRWg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<script src="public/js/main.js"></script>
