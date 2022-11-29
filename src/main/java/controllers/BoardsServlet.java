package controllers;

import dao.BoardDAO;
import dao.CardDAO;
import dao.ListDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.BoardModel;
import models.CardModel;
import models.ListModel;

import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet(name = "BoardsServlet", urlPatterns = { "/boards", "/boards/*"})
public class BoardsServlet extends HttpServlet {
    BoardDAO boardDAO = new BoardDAO();
    ListDAO listDAO = new ListDAO();
    CardDAO cardDAO = new CardDAO();
   // private String boardsRegex = "^/boards$";
    private String boardDetailRegex = "^/\\d$";
    private String listsRegex = "^/\\d/lists$";
    private String listDetailRegex = "^/\\d/lists/\\d$";
    private String cardsRegex = "^/\\d/lists/\\d/cards$";
    private String cardDetailRegex = "^/\\d/lists/\\d/cards/\\d$";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        System.out.println(pathInfo);
        if(pathInfo == null){
            request.setAttribute("VIEW", "views/boards.jsp");
            RequestDispatcher rd = request.getRequestDispatcher("/layout.jsp");
            rd.forward(request, response);
        }else{
            int boardId = Integer.parseInt(pathInfo.replace("/", ""));
            HttpSession session = request.getSession();
            session.setAttribute("boardId", boardId);
            RequestDispatcher rd = request.getRequestDispatcher("/views/boardDetail.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
                HttpSession session = request.getSession();
                String pathInfo = request.getPathInfo();
                System.out.println(pathInfo);
                if(session.getAttribute("isAuthenticated") == null){
                    response.sendError(401, "unauthorized");
                }else{
                    //create board
                    if(pathInfo == null){
                        System.out.println("creat board");

                        String name = request.getParameter("name");
                        int userId = (int)session.getAttribute("userId");
                        BoardModel board = new BoardModel(userId, name);
                        boardDAO.create(board);
                        response.sendRedirect("/boards");
                        return;
                    }
                    //create list
                    if(Pattern.matches(this.listsRegex, pathInfo)){
                        System.out.println("creat list");

                        int boardId = Integer.parseInt(request.getParameter("boardId"));
                        String listName = request.getParameter("name");
                        System.out.println("listName" + listName);
                        System.out.println("boardId" + boardId);
                        int order = listDAO.getLatestOrder(boardId);
                        ListModel list = new ListModel(listName, boardId, order + 1 );
                        listDAO.create(list);
                        System.out.println("/boards/" + boardId);
                       response.sendRedirect("/boards/"+ boardId);
                       return;
                    }
                    // creat card
                    if(Pattern.matches(this.cardsRegex, pathInfo)){
                        System.out.println("creat card");
                        System.out.println("boardId" + request.getParameter("boardId"));
                        System.out.println("listId" + request.getParameter("listId"));

                        int boardId = Integer.parseInt(request.getParameter("boardId"));
                        int listId = Integer.parseInt(request.getParameter("listId"));
                        String cardDescription = request.getParameter("description");

                        int order = cardDAO.findLatestOrderByBoardIdAndListId(boardId, listId);
                        CardModel card = new CardModel(boardId, listId, cardDescription, order + 1 );
                        cardDAO.create(card);
                        response.sendRedirect("/boards/"+ boardId);
                        return;
                    }

                }
        }catch (Exception e){
                System.out.println(e);
                response.sendRedirect("/boards");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String pathInfo = request.getPathInfo();
            if(session.getAttribute("isAuthenticated") == null){
                response.sendError(401, "unauthorized");
                //update board
                if(Pattern.matches(this.boardDetailRegex, pathInfo)){
                    System.out.println("update board");
                    int boardId = Integer.parseInt(request.getParameter("boardId"));
                    String boardName = request.getParameter("name");
                    boardDAO.updateName(boardName, boardId);
                    response.sendRedirect("/boards/"+ boardId);
                    return;
                }
                // update list
                if(Pattern.matches(this.listDetailRegex, pathInfo)){
                    System.out.println("update list");
                    System.out.println("boardId" + request.getParameter("boardId"));
                    System.out.println("listId" + request.getParameter("listId"));

                    int boardId = Integer.parseInt(request.getParameter("boardId"));
                    int listId = Integer.parseInt(request.getParameter("listId"));
                    String listName = request.getParameter("name");
                    listDAO.updateListName(boardId, listId, listName);
                    response.sendRedirect("/boards/"+ boardId);
                    return;
                }
                if(Pattern.matches(this.cardDetailRegex, pathInfo)){
                    System.out.println("update card");
                    System.out.println("boardId" + request.getParameter("boardId"));
                    System.out.println("listId" + request.getParameter("listId"));

                    int boardId = Integer.parseInt(request.getParameter("boardId"));
                    int listId = Integer.parseInt(request.getParameter("listId"));
                    int cardId =  Integer.parseInt(request.getParameter("id"));
                    String description = request.getParameter("description");
                    cardDAO.updateCardDescription(boardId, listId, cardId, description);
                    response.sendRedirect("/boards/"+ boardId);
                    return;
                }

            }
        }catch (Exception e){

        }
    }
}
