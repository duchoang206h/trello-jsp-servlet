package controllers;

import dao.BoardDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.BoardModel;

import java.io.IOException;

@WebServlet(name = "BoardsServlet", urlPatterns = { "/boards", "/boards/*"})
public class BoardsServlet extends HttpServlet {
    BoardDAO boardDAO = new BoardDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if(pathInfo == null){
            request.setAttribute("VIEW", "views/boards.jsp");
            RequestDispatcher rd = request.getRequestDispatcher("/layout.jsp");
            rd.forward(request, response);
        }else{
            request.setAttribute("VIEW", "views/boardDetail.jsp");
            RequestDispatcher rd = request.getRequestDispatcher("/layout.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
                HttpSession session = request.getSession();
                if(session.getAttribute("isAuthenticated") == null){
                    response.sendError(401, "unauthorized");
                }else{
                    String name = request.getParameter("name");
                    BoardModel board = new BoardModel((int)session.getAttribute("userId"),name);
                    boardDAO.create(board);
                    response.sendRedirect("/boards");
                }
        }catch (Exception e){
                response.sendRedirect("/boards");
        }
    }
}
