package controllers;

import dao.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.UserModel;

import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("views/register.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String error = "";
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            UserModel user = new UserModel(name, email,password);
            UserModel existedUser = userDAO.findOneByEmail(email);
            HttpSession session = request.getSession();
            if(null == existedUser){
                userDAO.create(user);
                session.setAttribute("isAuthenticated", true);
                response.sendRedirect("/home");
            }else {
                error += "Email exist";
                session.setAttribute("loginError", error);
                response.sendRedirect("/register");
            }
        }catch (Exception e){
            response.sendRedirect("/register");
        }
    }
}
