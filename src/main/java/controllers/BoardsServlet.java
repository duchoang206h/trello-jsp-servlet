package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.BoardDAO;
import dao.CardDAO;
import dao.ListDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.BoardModel;
import models.CardModel;
import models.ListModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
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
        HttpSession session = request.getSession();
        if(session.getAttribute("isAuthenticated") == null) response.sendRedirect("/login");
        if(pathInfo == null){
            int page = Integer.parseInt(request.getParameter("page") == null ? "1": request.getParameter("page"));
            page = page <= 0 ? 1: page;
            System.out.println(page)    ;
            int userId = (int)session.getAttribute("userId");
            System.out.println("userId"+ userId);
            ArrayList<BoardModel> boards = boardDAO.findAllByUserId(userId);
            ArrayList<BoardModel> paginateBoards = boardDAO.findByUserIdLimitAndOffset(userId, 9, (page - 1)*9);
            System.out.println("totalPage" + paginateBoards.size());
            request.setAttribute("totalPage", (boards.size()-1)/9+1);
            request.setAttribute("boards", paginateBoards);
            request.setAttribute("VIEW", "views/boards.jsp");
            RequestDispatcher rd = request.getRequestDispatcher("/layout.jsp");
            rd.forward(request, response);
        }else{
            int boardId = Integer.parseInt(pathInfo.replace("/", ""));
            BoardModel board = boardDAO.findOneById(boardId);
            board.setLists(listDAO.findByBoardId(board.getId()));
            for(ListModel list : board.getLists()){
                list.setCards(cardDAO.findByListIdAndBoardId(list.getId(), board.getId()));
            }
            if(board == null) {
                RequestDispatcher rd = request.getRequestDispatcher("/views/pageNotFound.jsp");
                rd.forward(request, response);
            }else{
                request.setAttribute("board", board);
                RequestDispatcher rd = request.getRequestDispatcher("/views/boardDetail.jsp");
                rd.forward(request, response);
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
                HttpSession session = request.getSession();
                String pathInfo = request.getPathInfo();
                System.out.println(pathInfo);
                if(session.getAttribute("isAuthenticated") == null){
                    System.out.println("unauthorized");
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
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String data = br.readLine();

            String pathInfo = request.getPathInfo();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> body = mapper.readValue(
                    data, new TypeReference<Map<String, Object>>() {
                    });

            if(session.getAttribute("isAuthenticated") == null)
                response.sendError(401, "unauthorized");
            else {
                //update board
                if (Pattern.matches(this.boardDetailRegex, pathInfo)) {
                    System.out.println("update board");
                    int boardId = Integer.parseInt((String) body.get("boardId"));
                    String boardName = (String) body.get("name");
                    boardDAO.updateName(boardName, boardId);
                    response.setStatus(200);
                    return;
                }
                // update list
                if (Pattern.matches(this.listDetailRegex, pathInfo)) {
                    System.out.println("update list");
                    System.out.println("boardId" + request.getParameter("boardId"));
                    System.out.println("listId" + request.getParameter("listId"));

                    int boardId = Integer.parseInt((String) body.get("boardId"));
                    int listId = Integer.parseInt((String) body.get("listId"));
                    String listName = (String) body.get("name");
                    listDAO.updateListName(boardId, listId, listName);
                    response.setStatus(200);
                    return;
                }
                if (Pattern.matches(this.cardDetailRegex, pathInfo)) {
                    System.out.println("update card");

                    int boardId = Integer.parseInt(body.get("boardId").toString());
                    int listId = Integer.parseInt(body.get("listId").toString());
                    int cardId = Integer.parseInt(body.get("cardId").toString());
                    String description = body.get("description").toString();
                    cardDAO.updateCardDescription(boardId, listId, cardId, description);
                    System.out.println("boardId" + boardId);
                    System.out.println("listId" + listId);
                    System.out.println("cardId" + cardId);
                    System.out.println("description" + description);
                    response.setStatus(200);
                    return;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
            response.sendError(500, "internal server error");
        }
    }
}
