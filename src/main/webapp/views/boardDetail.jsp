<%@ page import="dao.BoardDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.BoardModel" %>
<%@ page import="models.ListModel" %>
<%@ page import="models.CardModel" %>
<%@ page import="javax.swing.*" %>
<%@ page import="dao.ListDAO" %>
<%@ page import="dao.CardDAO" %><%--
  Created by IntelliJ IDEA.
  User: thngtrn
  Date: 26/11/2022
  Time: 01:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Board</title>
<%--    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">--%>
    <link rel="stylesheet" href="../public/css/boardStyle.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>

<div class="wrapper">
<!-- Masthead -->
<header class="masthead">

    <a href="/home" class="home-btn btn">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-house-door" viewBox="0 0 16 16">
            <path d="M8.354 1.146a.5.5 0 0 0-.708 0l-6 6A.5.5 0 0 0 1.5 7.5v7a.5.5 0 0 0 .5.5h4.5a.5.5 0 0 0 .5-.5v-4h2v4a.5.5 0 0 0 .5.5H14a.5.5 0 0 0 .5-.5v-7a.5.5 0 0 0-.146-.354L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.354 1.146ZM2.5 14V7.707l5.5-5.5 5.5 5.5V14H10v-4a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5v4H2.5Z"/>
        </svg>
    </a>

    <div class="boards-menu">

        <a href="/boards" class="boards-btn btn">Boards</a>

        <div class="board-search">
            <input type="search" class="board-search-input" aria-label="Board Search">
            <i class="fas fa-search search-icon" aria-hidden="true"></i>
        </div>

    </div>

    <div class="logo">

        <h1><i class="fab fa-trello logo-icon" aria-hidden="true"></i>Trello</h1>

    </div>

    <div class="user-settings">

        <button class="user-settings-btn btn" aria-label="Create">
            <i class="fas fa-plus" aria-hidden="true"></i>
        </button>

        <button class="user-settings-btn btn" aria-label="Information">
            <i class="fas fa-info-circle" aria-hidden="true"></i>
        </button>

        <button class="user-settings-btn btn" aria-label="Notifications">
            <i class="fas fa-bell" aria-hidden="true"></i>
        </button>

        <button class="user-settings-btn btn" aria-label="User Settings">
            <i class="fas fa-user-circle" aria-hidden="true"></i>
        </button>

    </div>

</header>
<!-- End of masthead -->


<!-- Board info bar -->
<section class="board-info-bar">

    <div class="board-controls">

        <button class="board-title btn">
            <h2>Web Development</h2>
        </button>

        <button class="star-btn btn" aria-label="Star Board">
            <i class="far fa-star" aria-hidden="true"></i>
        </button>

        <button class="personal-btn btn">Personal</button>

        <button class="private-btn btn"><i class="fas fa-briefcase private-btn-icon" aria-hidden="true"></i>Private</button>

    </div>

    <button class="menu-btn btn"><i class="fas fa-ellipsis-h menu-btn-icon" aria-hidden="true"></i>Show Menu</button>

</section>
<!-- End of board info bar -->

<!-- Lists container -->
<section class="lists-container">

    <%
        BoardDAO boardDAO = new BoardDAO();
        ListDAO listDAO = new ListDAO();
        CardDAO cardDAO = new CardDAO();
        BoardModel board  = boardDAO.findOneById((int)session.getAttribute("boardId"));
        System.out.println((int)session.getAttribute("boardId"));
        ArrayList<ListModel> lists = listDAO.findByBoardId(board.getId());
        if(lists != null) for(ListModel list: lists){
    %>
    <div data-index="<%=board.getId()%>" class="list">
        <h3 class="list-title" data-index="<%=list.getId()%>"><%= list.getName()%></h3>
    <%
    ArrayList<CardModel> cards = cardDAO.findByListIdAndBoardId(list.getId(), board.getId());
    if(cards != null) {
        %>
        <ul data-index="<%=list.getId()%>" class="list-items">
        <%for(CardModel card: cards){ %>
            <li data-index="<%=card.getId()%>" class="list-item"><%= card.getDescription()%></li> <% } %>
        </ul> <% } %>

        <form action="" method="post" class="form-add-card">
            <textarea rows="2" class="input-add-card" name="input-card"></textarea>
            <button type="button" class="add-card-btn btn">Add a card</button>
        </form>
    <% } %>
    </div>

    <div class="list">

        <h3 class="list-title">JavaScript Project Ideas</h3>

        <ul class="list-items">
            <li class="list-item">Analog Clock</li>
            <li class="list-item">Basic Quiz</li>
            <li class="list-item">Bill/Cost Splitter</li>
            <li class="list-item">Countdown Timer</li>
            <li class="list-item">Form Validator</li>

        </ul>
        <form action="" method="post" class="form-add-card">
            <textarea rows="2" class="input-add-card" name="input-card"></textarea>
            <button type="button" class="add-card-btn btn">Add a card</button>
        </form>


    </div>

    <button class="add-list-btn btn" onclick="showModal()">Add a list</button>

</section>
<!-- End of lists container -->
</div>
<%--    Modal   --%>
<div class="modal js-modal">
    <div class="modal-container js-modal-container">
        <div class="modal-close js-modal-close">
            <i>X</i>
        </div>

        <div class="modal-header">
            <p class="modal-heading">
                Add a list
            </p>
        </div>

        <form action="" method="post" id="form-add-list" class="modal-body">

            <input type="text" placeholder="Title" class="modal-input" id="quantity-tickets">

            <button class="create-btn">
                Add
            </button>

            <div class="modal-footer">
            </div>
        </form>
    </div>
</div>

<%--  List  Modal   --%>
<div class="list-modal js-modal">
    <div class="card-modal-container js-modal-container">
        <div class="modal-close js-modal-close">
            <i>X</i>
        </div>

        <form action="" method="post" id="form-edit-list" class="modal-body">
            <input type="text" name="list-id" class="list-id">
            <input type="text" name="board-id" class="board-id">
            <label class="label-edit-card" for="edit-card">Card</label>
            <textarea class="input-edit-card" name="card-content" id="edit-list" rows="4"></textarea>

            <div class="card-btn-container">
                <button class="btn update-btn">
                    Update
                </button>

                <button type="button" class="btn delete-btn">
                    Delete
                </button>
            </div>
        </form>
    </div>
</div>

<%--  Card  Modal   --%>
<div class="card-modal js-modal">
    <div class="card-modal-container js-modal-container">
        <div class="modal-close js-modal-close">
            <i>X</i>
        </div>

        <form action="" method="post" id="form-edit-card" class="modal-body">
            <input type="text" name="card-id" id="card-id" class="card-id">
            <input type="text" name="list-id" id="list-id" class="list-id">
            <input type="text" name="board-id" id="board-id" class="board-id">
            <label class="label-edit-card" for="edit-card">Card</label>
            <textarea class="input-edit-card" name="card-content" id="edit-card" rows="4"></textarea>

            <div class="card-btn-container">
                <button class="btn update-btn">
                    Update
                </button>

                <button type="button" class="btn delete-btn">
                    Delete
                </button>
            </div>
        </form>
    </div>
</div>

<script src="../public/js/main.js"></script>
</body>
</html>

