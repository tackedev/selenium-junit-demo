package com.tackedev.seleniumdemo.controller;

import com.tackedev.seleniumdemo.Util.SHA256Util;
import com.tackedev.seleniumdemo.dao.UserDAO;
import com.tackedev.seleniumdemo.dto.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/loginServlet"})
public class LoginServlet extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String HOME_PAGE = "home.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = LOGIN_PAGE;

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            username = username.strip();

            if (password.isBlank()) {
                request.setAttribute("LOGIN_MESSAGE", "Username or password is blank!");
                return;
            }
            password = SHA256Util.getEncryptedPassword(password);

            UserDAO dao = new UserDAO();
            User user = dao.getUserByUsernameAndPassword(username, password);

            if (user != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("USER", user);
                url = HOME_PAGE;
            } else {
                request.setAttribute("LOGIN_MESSAGE", "Wrong username or password!");
            }

        } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } finally {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
            requestDispatcher.forward(request, response);
        }
    }
}
