
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.BoardModel" %><%--
  Created by IntelliJ IDEA.
  User: thngtrn
  Date: 25/11/2022
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- Content--%>
<div class="d-flex justify-content-between align-items-center m-2">
    <button class="btn btn-success btn-lg" onclick="showModal()">+ Create a board</button>
    <form class="d-flex" role="search">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
    </form>
</div>
<div class="board-wrapper">
    <div class="row">
        <%
            ArrayList<BoardModel> boards = (ArrayList<BoardModel>) request.getAttribute("boards");
            if (boards.size() != 0) for(BoardModel board: boards){
        %>
        <div class="col-4">
            <a href="boards/<%= board.getId() %>" class="board-container m-2">
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
        </div>
        <% } %>
    </div>
</div>
    <%-- Navigation page--%>
    <div class="d-flex justify-content-center">
        <nav aria-label="Page d-flex justify-content-center navigation example mt-3" >
            <ul class="pagination">
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                        <span class="sr-only">Previous</span>
                    </a>
                </li>
                <%
                    int totalPage = (int)request.getAttribute("totalPage");
                    if(totalPage> 5) totalPage = 5;
                    for(int i = 1; i<=totalPage; i++){ %>
                <li class="page-item"><a class="page-link" href="<%= "/boards?page=" + i%>"><%=i%></a></li>
                <%}
                %> <li class="page-item"><a class="page-link" href="">...</a></li> <%
            %>
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                        <span class="sr-only">Next</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>

<%-- Modal --%>
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

<%-- Card Modal--%>
<div class="board-modal js-modal">
    <div class="board-modal-container js-modal-container">
        <form action="" method="post" id="form-edit-board" class="modal-body">
            <input type="text" name="board-id" id="board-id" class="board-id">
            <textarea class="input-edit-board" name="card-content" id="edit-board" rows="2"></textarea>

            <div class="d-flex justify-content-end">
                <button class="btn btn-outline-success w-50">
                    Update
                </button>

                <button type="button" class="btn btn-outline-danger w-50">
                    Delete
                </button>
            </div>
        </form>
    </div>
</div>

<script src="public/js/main.js"></script>