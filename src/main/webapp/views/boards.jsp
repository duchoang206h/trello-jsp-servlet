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
    %>
    <a href="boards/<%= board.getId() %>" class="board-container">
        <span class="board-name"><%= board.getName() %></span>
        <div class="board-btn-container">
            <button class="board-edit-btn" onclick="showBoardModal()" data-index="<%=board.getId()%>">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                    <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                    <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                </svg>
            </button>
        </div>
    </a>
    <% } %>
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

            <input type="text" placeholder="Board's name" class="modal-input" id="quantity-tickets">

            <button class="create-btn">
                Create
            </button>

            <div class="modal-footer">
                Need
                <a href="#">help?</a>
            </div>
        </form>
    </div>
</div>

<%--  Card  Modal   --%>
<div class="board-modal js-modal">
    <div class="board-modal-container js-modal-container">
        <div class="modal-close js-modal-close">
            <i>X</i>
        </div>

        <form action="" method="post" id="form-edit-board" class="modal-body">
            <input type="text" name="board-id" id="board-id">
            <textarea class="input-edit-board" name="card-content" id="edit-board" rows="2"></textarea>

            <div class="board-modal-btn-container">
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

<script src="public/js/main.js"></script>