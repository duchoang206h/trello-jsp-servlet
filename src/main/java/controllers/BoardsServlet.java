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
import utils.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "BoardsServlet", urlPatterns = { "/boards", "/boards/*"})
public class BoardsServlet extends HttpServlet {
    BoardDAO boardDAO = new BoardDAO();
    ListDAO listDAO = new ListDAO();
    CardDAO cardDAO = new CardDAO();

    Util util = new Util();
   // private String boardsRegex = "^/boards$";
    private String boardDetailRegex = "^/\\d+$";
    private String listsRegex = "^/\\d+/lists$";
    private String listDetailRegex = "^/\\d+/lists/\\d+$";
    private String cardsRegex = "^/\\d+/lists/\\d+/cards$";
    private String cardDetailRegex = "^/\\d+/lists/\\d+/cards/\\d+$";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        HttpSession session = request.getSession();
        if(session.getAttribute("isAuthenticated") == null) response.sendRedirect("/login");
        String search = request.getParameter("search");
        if(pathInfo == null){
            int page = Integer.parseInt(request.getParameter("page") == null ? "1": request.getParameter("page"));
            page = page <= 0 ? 1: page;
            int userId = (int)session.getAttribute("userId");
            ArrayList<BoardModel> boards = search != null && search!= ""? boardDAO.searchByName(search, userId):boardDAO.findAllByUserId(userId);
            int totalPage = (boards.size()-1)/6+1;
            int limit = 6;
            int offset = (page - 1)*6;
            ArrayList<BoardModel> paginateBoards = search != null && search!= ""? boardDAO.searchByNameLimitAndOffset(search, userId, limit, offset ):boardDAO.findByUserIdLimitAndOffset(userId, limit, offset );
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("boards", paginateBoards);
            request.setAttribute("VIEW", "views/boards.jsp");
            RequestDispatcher rd = request.getRequestDispatcher("/layout.jsp");
            rd.forward(request, response);
        }else{
            int boardId = Integer.parseInt(pathInfo.replace("/", ""));
            BoardModel board = boardDAO.findOneById(boardId);
            if(board == null) {
                RequestDispatcher rd = request.getRequestDispatcher("/views/pageNotFound.jsp");
                rd.forward(request, response);
                return;
            }
            board.setLists(listDAO.findByBoardId(board.getId()));
            for(ListModel list : board.getLists()){
                list.setCards(cardDAO.findByListIdAndBoardId(list.getId(), board.getId()));
            }

            request.setAttribute("board", board);
            RequestDispatcher rd = request.getRequestDispatcher("/views/boardDetail.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
                HttpSession session = request.getSession();
                String pathInfo = request.getPathInfo();
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
                        int order = listDAO.getLatestOrder(boardId);
                        ListModel list = new ListModel(listName, boardId, order + 1 );
                        listDAO.create(list);
                        response.sendRedirect("/boards/"+ boardId);
                        return;
                    }
                    // creat card
                    if(Pattern.matches(this.cardsRegex, pathInfo)){
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
                e.printStackTrace();
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
                    int boardId = Integer.parseInt(body.get("id").toString());
                    String boardName = body.get("name").toString();
                    boardDAO.updateName(boardName, boardId);
                    response.setStatus(200);
                    return;
                }
                // update list
                if (Pattern.matches(this.listDetailRegex, pathInfo)) {
                    int boardId = Integer.parseInt(body.get("boardId").toString());
                    int listId = Integer.parseInt(body.get("listId").toString());
                    String listName = body.get("name").toString();
                    listDAO.updateListName(boardId, listId, listName);
                    response.setStatus(200);
                    return;
                }
                if (Pattern.matches(this.cardDetailRegex, pathInfo)) {
                    int boardId = Integer.parseInt(body.get("boardId").toString());
                    int listId = Integer.parseInt(body.get("listId").toString());
                    int cardId = Integer.parseInt(body.get("cardId").toString());
                    String description = body.get("description").toString();
                    cardDAO.updateCardDescription(boardId, listId, cardId, description);
                    response.setStatus(200);
                    return;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
            response.sendError(500, "internal server error");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();

            String pathInfo = request.getPathInfo();
            if(session.getAttribute("isAuthenticated") == null)
                response.sendError(401, "unauthorized");
            else {
                //update board
                ArrayList<Integer> idsFromPath = util.getDigitsFromString(pathInfo);
                if (Pattern.matches(this.boardDetailRegex, pathInfo)) {
                        int boardId = idsFromPath.get(0);
                        if (boardDAO.deleteOneById(boardId)) response.setStatus(200);
                        else response.setStatus(406);
                        return;
                }
                // update list
                if (Pattern.matches(this.listDetailRegex, pathInfo)) {
                        int boardId = idsFromPath.get(0);
                        int listId = idsFromPath.get(1);
                        if (listDAO.deleteOneByBoardIdAndListId(boardId, listId)) response.setStatus(200);
                        else response.setStatus(406);
                        return;
                }
                if (Pattern.matches(this.cardDetailRegex, pathInfo)) {

                        int boardId = idsFromPath.get(0);
                        int listId = idsFromPath.get(1);
                        int cardId = idsFromPath.get(2);
                        if (cardDAO.deleteOne(boardId, listId, cardId)) response.setStatus(200);
                        else response.setStatus(406);
                        return;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            response.sendError(500, "internal server error");
        }
    }
}
