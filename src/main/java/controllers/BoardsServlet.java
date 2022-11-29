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
    private String cardsRegex = "^/\\d/lists/\\d$/cards";
    private String cardsDetaillRegex = "^/\\d/lists/\\d/cards/\\d$";
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
                        String name = request.getParameter("name");
                        int userId = (int)session.getAttribute("userId");
                        BoardModel board = new BoardModel(userId, name);
                        boardDAO.create(board);
                        response.sendRedirect("/boards");
                        return;
                    }
                    //create list
                    if(Pattern.matches(this.listsRegex, pathInfo)){
                        int boardId = Integer.parseInt(request.getParameter("boardId"));
                        String listName = request.getParameter("name");
                        System.out.println("listName" + listName);
                        System.out.println("boardId" + boardId);
                        int order = listDAO.getLatestOrder(boardId);
                        ListModel list = new ListModel(listName, boardId, order);
                        listDAO.create(list);
                        System.out.println("/boards/" + boardId);
                       response.sendRedirect("/boards/"+ boardId);
                       return;
                    }
                    // creat card
                    if(Pattern.matches(this.cardsRegex, pathInfo)){
                        int boardId = Integer.parseInt(request.getParameter("boardId"));
                        int listId = Integer.parseInt(request.getParameter("listId"));
                        String cardDescription = request.getParameter("description");

                        int order = cardDAO.findLatestOrderByBoardIdAndListId(boardId, listId);
                        CardModel card = new CardModel(boardId, listId, cardDescription, order);
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
            }else{
                if(Pattern.matches(this.boardDetailRegex, pathInfo)){
                    String name = request.getParameter("name");
                    int userId = (int)session.getAttribute("userId");

                    RequestDispatcher rd = getServletContext()
                            .getRequestDispatcher(pathInfo);
                    rd.forward(request, response);
                }
                if(Pattern.matches(this.listsRegex, pathInfo)){
                    int boardId = Integer.parseInt(request.getParameter("boardId"));
                    String listName = request.getParameter("name");
                    int order = listDAO.getLatestOrder(boardId);
                    ListModel list = new ListModel(listName, boardId, order);
                    listDAO.create(list);
                    RequestDispatcher rd = getServletContext()
                            .getRequestDispatcher(pathInfo);
                    rd.forward(request, response);
                }
            }
        }catch (Exception e){

        }
    }
}
