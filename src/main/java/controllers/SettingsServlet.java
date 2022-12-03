package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.UserModel;
import org.mindrot.jbcrypt.BCrypt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.regex.Pattern;

@WebServlet(name = "SettingsServlet", urlPatterns = { "/settings", "/settings/*"})
public class SettingsServlet extends HttpServlet {
    UserDAO userDAO = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("isAuthenticated") ==null){
            response.sendRedirect("/login");
            return;
        }
        request.setAttribute("VIEW", "views/settings.jsp");
        RequestDispatcher rd = request.getRequestDispatcher("/layout.jsp");
        rd.forward(request, response);
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
                if (pathInfo.equals("/profile")) {
                    String name = body.get("name").toString();
                    int userId = (int) session.getAttribute("userId");
                    if(userDAO.updateName(name, userId)) {
                        session.setAttribute("user", userDAO.findOneById(userId));
                        response.setStatus(200);
                    }
                    else response.sendError(409);
                    return;
                }
                // update list
                if (pathInfo.equals("/reset-password")) {
                    String oldPassword  = body.get("oldPassword").toString();
                    String newPassword = body.get("newPassword").toString();
                    int userId = (int) session.getAttribute("userId");
                    UserModel user = userDAO.findOneById(userId);
                    if(BCrypt.checkpw(oldPassword, user.getPassword())){
                        userDAO.updatePassword(newPassword, userId);
                        response.setStatus(200);
                    }else response.sendError(409, "invalid password");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            response.sendError(500, "internal server error");
        }
    }
}
