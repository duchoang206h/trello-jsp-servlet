package controllers.Auth;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "login", value = "/login")
public class Login extends HttpServlet {
    private String message;

    public void init() {
        message = "Login!";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username != "" && password != "") {
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<h2>" + "username: " + username + "</h2>");
        out.println("<h2>" + "password: " + password + "</h2>");

        out.println("</body></html>");
    }

    public void destroy() {
    }
}
