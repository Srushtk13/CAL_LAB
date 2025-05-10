package com.vsms.controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class HomeController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("views/home.jsp");
        rd.forward(request, response);
    }
}