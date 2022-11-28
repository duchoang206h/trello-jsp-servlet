<%@ page import="dao.BoardDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.BoardModel" %><%--
  Created by IntelliJ IDEA.
  User: thngtrn
  Date: 25/11/2022
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<button class="btn create-board-btn" onclick="showModal()">+ Creat a board</button>
<div class="row">
    <%
        BoardDAO boardDAO = new BoardDAO();
        System.out.println((int)session.getAttribute("userId"));
        ArrayList<BoardModel> boards = boardDAO.findAllByUserId((int)session.getAttribute("userId"));
        if (boards.size() != 0) for(BoardModel board: boards){
    %> <a href="boards/<%= board.getId() %>" class="board-container">
    <span class="board-name"><%= board.getName() %></span>
</a> <%
    }
%>
</div>
<%--Modal--%>
<div class="modal js-modal">
    <div class="modal-container js-modal-container">
        <div class="modal-close js-modal-close">
            <i>X</i>
        </div>

        <div class="modal-header">
            <p class="modal-heading">
                Create a board
            </p>
        </div>

        <form action="" method="post" id="form-create-board" class="modal-body">

            <input type="text" placeholder="Board's name" class="modal-input" id="boardName" name="name">

            <button class="create-btn">
                Create
            </button>
        </form>
    </div>
</div>

<script src="public/js/main.js"></script>