<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Trello - Hello World</title>

    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <!-- Main css -->
    <link rel="stylesheet" href="public/css/homeStyle.css">
</head>
<body>
<div class="nav-bar">
    <a href="home" class="nav-logo index-page">
        <img alt="brand logo" class="trello-logo" src="https://trello-clone-one.vercel.app/trello-logo.svg">
    </a>
    <div class="nav-right-container">
        <a class="btn login-btn" href="login">Sign in</a>
        <a class="btn signup-btn" href="register">Register</a>
    </div>
</div>
<div class="d-flex flex-row">
    <div class="row p-3">
        <div class="col-8">
            <h1>Trello helps teams move work forward.</h1>
            <p>Collaborate, manage projects, and reach new productivity peaks. From high rises to the home office, the way your team works is unique - accomplish it all with Trello.</p>
        </div>
        <div class="col-4">
            <img class="content-img" src="https://trello-clone-one.vercel.app/homepage/home-illustration.svg" alt="brand logo">
        </div>
    </div>
</div>
</body>
</html>