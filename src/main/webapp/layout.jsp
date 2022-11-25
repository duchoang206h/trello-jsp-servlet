<%--
  Created by IntelliJ IDEA.
  User: thngtrn
  Date: 26/11/2022
  Time: 00:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trello</title>

    <!-- Main css -->
    <link rel="stylesheet" href="public/css/homeStyle.css">
</head>
<body>
<div class="nav-bar">
    <div class="nav-logo">
        <img alt="brand logo" class="trello-logo" src="https://trello-clone-one.vercel.app/trello-logo.svg">
    </div>
    <div class="nav-right-container">
        <button class="logout-btn">Log out</button>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-3">
            <div class="sidebar">
                <ul class="sidebar-nav">
                    <li>
                        <a class="sidebar-nav-item" href="home">Home</a>
                    </li>
                    <li>
                        <a class="sidebar-nav-item" href="boards">Boards</a>
                    </li>
                    <li>
                        <a class="sidebar-nav-item" href="templates">Templates</a>
                    </li>
                    <li>
                        <a class="sidebar-nav-item" href="settings">Settings</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="col-9">
            <div class="content">
                <jsp:include page="${VIEW}"></jsp:include>
            </div>
        </div>
    </div>
</div>

</body>
</html>
