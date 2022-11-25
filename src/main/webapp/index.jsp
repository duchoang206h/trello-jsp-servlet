<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>

    <!-- Main css -->
    <link rel="stylesheet" href="public/css/homeStyle.css">
</head>
<body>
<div class="nav-bar">
    <a href="home" class="nav-logo">
        <img alt="brand logo" class="trello-logo" src="https://trello-clone-one.vercel.app/trello-logo.svg">
    </a>
    <div class="nav-right-container">
        <a class="btn login-btn" href="login.jsp">Log in</a>
        <a class="btn signup-btn" href="registed.jsp">Sign up</a>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-8">
            <h1>Trello helps teams move work forward.</h1>
            <h4>Collaborate, manage projects, and reach new productivity peaks. From high rises to the home office, the way your team works is unique - accomplish it all with Trello.</h4>
        </div>
        <div class="col-4">
            <img class="content-img" src="https://trello-clone-one.vercel.app/homepage/home-illustration.svg" alt="brand logo">
        </div>
    </div>
</div>
</body>
</html>