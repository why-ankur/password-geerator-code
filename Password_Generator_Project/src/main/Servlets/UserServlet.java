package main.servlets;

import main.JDBC_Classes.password_storage_manager;
import main.JDBC_Classes.PasswordUpdater;
import main.JDBC_Classes.UserInfoFetcher;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch user information based on phone number and platform
        String phoneNumber = request.getParameter("phone");
        String platform = request.getParameter("platform");

        response.setContentType("text/html");

        if (phoneNumber != null && platform != null) {
            UserInfoFetcher.fetchUserInfo(phoneNumber, platform);
            request.getRequestDispatcher("user_info.jsp").forward(request, response);
        } else {
            response.getWriter().println("Invalid request parameters for fetching user info.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("addUser".equals(action)) {
            // Add user data
            String phone = request.getParameter("phone");
            String dob = request.getParameter("dob");
            String name = request.getParameter("name");
            String platform = request.getParameter("platform");
            password_storage_manager.storeUserData(phone, dob, name, platform);
            response.getWriter().println("User data added successfully!");
        } else if ("updatePassword".equals(action)) {
            // Update user password
            String phone = request.getParameter("phone");
            String platform = request.getParameter("platform");
            String newPassword = request.getParameter("newPassword");
            PasswordUpdater.updatePassword(phone, platform, newPassword);
            response.getWriter().println("Password updated successfully!");
        } else {
            response.getWriter().println("Invalid action specified.");
        }
    }
}
