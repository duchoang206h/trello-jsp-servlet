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
<button class="btn create-board-btn">+ Creat a board</button>
<div class="row">
    <%
        BoardDAO boardDAO = new BoardDAO();
        System.out.println((int)session.getAttribute("userId"));
        ArrayList<BoardModel> boards = boardDAO.findAllByUserId((int)session.getAttribute("userId"));
        for(BoardModel board: boards){
            %> <div>  <%= board.getName() %></div> <%
        }
    %>
    <a href="views/boardHadId.jsp" class="board-container">
        <span class="board-name">Hello</span>
    </a>
</div>
