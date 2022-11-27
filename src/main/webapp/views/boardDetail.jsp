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
<%
    BoardDAO boardDAO = new BoardDAO();
    ListDAO listDAO = new ListDAO();
    CardDAO cardDAO = new CardDAO();
    BoardModel board  = boardDAO.findOneById((int)session.getAttribute("boardId"));
    System.out.println((int)session.getAttribute("boardId"));
    ArrayList<ListModel> lists = listDAO.findByBoardId(board.getId());
    if(lists != null) for(ListModel list: lists){
    %> <p> list name: <%= list.getName()%>%></p> <%
        ArrayList<CardModel> cards = cardDAO.findByListIdAndBoardId(list.getId(), board.getId());
        if(cards != null) for(CardModel card: cards){
    %> <p> Card description: <%= card.getDescription()%>%></p> <%
    }}
%>
</body>
</html>
