package controllers;

import dao.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.UserModel;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("views/login.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String err = "";
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            HttpSession session = request.getSession();
            UserModel user = this.userDAO.validate(email, password);
            if(user !=null){
                session.setAttribute("isAuthenticated", true);
                session.setAttribute("userId", user.getId());
                session.setAttribute("user", user);
                response.sendRedirect("/home");
            }else{
                err+="invalid username or password";
                System.out.println(err);
                session.setAttribute("loginError", err);
                response.sendRedirect("/login");
            }

        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/login");
        }

    }
}
