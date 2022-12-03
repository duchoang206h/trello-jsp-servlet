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
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

    <!-- Font awesome cdn -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />

    <link rel="stylesheet" href="public/css/homeStyle.css">
</head>
<body>
<div class="nav-bar">

    <div class="container d-flex justify-content-between h-100">
        <a href="home" class="nav-logo">
            <img alt="brand logo" class="trello-logo" src="https://trello-clone-one.vercel.app/trello-logo.svg">
        </a>
        <div class="nav-right-container">
            <a href="/logout" class="btn btn-logout">
                <i class="fa-solid fa-right-from-bracket"></i>
            </a>
        </div>
    </div>
</div>

<div class="container">
    <div class="row p-3">
        <div class="col-3">
            <div class="sidebar">
                <ul class="sidebar-nav">
                    <li>
                        <a class="sidebar-nav-item" href="home">
                            <i class="fa-solid fa-house sidebar-item-icon"></i>
                            Home
                        </a>
                    </li>
                    <li>
                        <a class="sidebar-nav-item" href="boards">
                            <i class="fa-brands fa-trello sidebar-item-icon"></i>
                            Boards
                        </a>
                    </li>

                    <li>
                        <a class="sidebar-nav-item" href="settings">
                            <i class="fa-solid fa-gear sidebar-item-icon"></i>
                            Settings
                        </a>
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
