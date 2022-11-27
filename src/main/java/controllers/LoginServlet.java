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
            UserModel user = this.userDAO.validate(email, password);
            if(user !=null){
                HttpSession session = request.getSession();
                session.setAttribute("isAuthenticated", true);
                session.setAttribute("userId", user.getId());
                Cookie loginCookie = new Cookie("email",email);
                loginCookie.setMaxAge(30*60);
                response.addCookie(loginCookie);
                response.sendRedirect("/home");
            }else{
                RequestDispatcher rd = getServletContext()
                        .getRequestDispatcher("/login");
                rd.forward(request, response);
            }

        }catch (Exception e){
           System.out.print(e);

        }

    }
}
