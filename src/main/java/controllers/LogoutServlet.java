package controllers;

import dao.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.UserModel;

import java.io.IOException;

@WebServlet(name = "LogoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            session.removeAttribute("isAuthenticated");
            session.removeAttribute("userId");
            response.sendRedirect("/index.jsp");
        }catch (Exception e){
            System.out.print(e);
            response.sendRedirect("/home");
        }

    }
}
