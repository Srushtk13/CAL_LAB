package com.sres;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

@WebServlet("/Vehicle")
public class Vehicle extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Vehicle() {
        super();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain"); // Set response type
        try {
            // Debugging: Print received parameters
            Map<String, String[]> paramMap = request.getParameterMap();
            System.out.println("Request Parameters: " + paramMap);

            // Get the choice parameter
            String choiceParam = request.getParameter("choice");
            System.out.println("Choice: " + choiceParam);

            if (choiceParam == null || choiceParam.trim().isEmpty()) {
                response.getWriter().println("Error: 'choice' parameter is missing.");
                return;
            }
            int x = Integer.parseInt(choiceParam);

            // Establish MySQL connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehicel", "root", "$ru$hti13");

            if (x == 1) { // Insert operation
                String owner = request.getParameter("owner");
                String model = request.getParameter("model");
                String type = request.getParameter("type");
                String date = request.getParameter("date");

                if (owner == null || model == null || type == null || date == null ||
                        owner.trim().isEmpty() || model.trim().isEmpty() || type.trim().isEmpty() || date.trim().isEmpty()) {
                    response.getWriter().println("Error: Missing one or more parameters.");
                    return;
                }

                PreparedStatement ps = con.prepareStatement("INSERT INTO service (owner, model, type, date) VALUES (?, ?, ?, ?)");
                ps.setString(1, owner);
                ps.setString(2, model);
                ps.setString(3, type);
                ps.setString(4, date);
                int rowAffected = ps.executeUpdate();
                response.getWriter().println("Rows affected: " + rowAffected);
            }

            if (x == 2) { // Update operation
                String idParam = request.getParameter("id");
                String owner = request.getParameter("owner");
                String model = request.getParameter("model");
                String type = request.getParameter("type");
                String date = request.getParameter("date");

                if (idParam == null || owner == null || model == null || type == null || date == null ||
                        idParam.trim().isEmpty() || owner.trim().isEmpty() || model.trim().isEmpty() || type.trim().isEmpty() || date.trim().isEmpty()) {
                    response.getWriter().println("Error: Missing one or more parameters.");
                    return;
                }

                int id = Integer.parseInt(idParam);
                PreparedStatement ps = con.prepareStatement("UPDATE service SET owner = ?, model = ?, type = ?, date = ? WHERE id = ?");
                ps.setString(1, owner);
                ps.setString(2, model);
                ps.setString(3, type);
                ps.setString(4, date);
                ps.setInt(5, id);
                int rowAffected = ps.executeUpdate();
                response.getWriter().println("Rows affected: " + rowAffected);
            }

            if (x == 3) { // Delete operation
                String idParam = request.getParameter("id");
                if (idParam == null || idParam.trim().isEmpty()) {
                    response.getWriter().println("Error: 'id' parameter is missing.");
                    return;
                }

                int id = Integer.parseInt(idParam);
                PreparedStatement ps = con.prepareStatement("DELETE FROM service WHERE id = ?");
                ps.setInt(1, id);
                int rowAffected = ps.executeUpdate();
                response.getWriter().println("Rows affected: " + rowAffected);
            }

            if (x == 4) { // Select operation
                PreparedStatement ps = con.prepareStatement("SELECT * FROM service");
                ResultSet rs = ps.executeQuery();
                response.getWriter().println("Service details:");

                while (rs.next()) {
                    response.getWriter().println("ID: " + rs.getInt(1));
                    response.getWriter().println("Owner: " + rs.getString(2));
                    response.getWriter().println("Model: " + rs.getString(3));
                    response.getWriter().println("Type: " + rs.getString(4));
                    response.getWriter().println("Date: " + rs.getDate(5));
                }
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace(); // Print error stack trace for debugging
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
