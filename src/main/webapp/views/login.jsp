<%--
  Created by IntelliJ IDEA.
  User: thngtrn
  Date: 24/11/2022
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Sign Up Form by Colorlib</title>

  <!-- Font Icon -->
  <link rel="stylesheet" href="public/fonts/material-icon/css/material-design-iconic-font.min.css">

  <!-- Main css -->
  <link rel="stylesheet" href="public/css/style.css">
</head>
<body>

<div class="main">

  <!-- Sing in  Form -->
  <section class="sign-in">
    <div class="container">
      <div class="signin-content">
        <div class="signin-image">
          <figure><img src="public/images/signin-image.jpg" alt="sing up image"></figure>
          <a href="register" class="signup-image-link">Create an account</a>
        </div>

        <div class="signin-form">
          <h2 class="form-title">Sign in</h2>
          <form  method="POST" class="register-form" id="login-form">
            <div class="form-group">
              <label for="email"><i class="zmdi zmdi-account material-icons-name"></i></label>
              <input type="text" name="email" id="email" placeholder="Email"/>
            </div>
            <div class="form-group">
              <label for="password"><i class="zmdi zmdi-lock"></i></label>
              <input type="password" name="password" id="password" placeholder="Password"/>
            </div>
            <div class="form-group form-button">
              <input type="submit" name="signin" id="signin" class="form-submit" value="Log in"/>
            </div>
            <%
              if(session.getAttribute("loginError")!=null){ %>
              <p style="color: red"><%=session.getAttribute("loginError")%></p>
            <%}
            %>
          </form>
        </div>
      </div>
    </div>
  </section>

</div>

<!-- JS -->
<script src="public/vendor/jquery/jquery.min.js"></script>
<script src="public/js/main.js"></script>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
