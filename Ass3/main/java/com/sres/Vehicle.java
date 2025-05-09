package com.sres;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.hibernate.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/Vehicle")
public class Vehicle extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Vehicle() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            int choice = Integer.parseInt(request.getParameter("choice"));

            if (choice == 1) {
                Service s = new Service();
                s.setOwner(request.getParameter("owner"));
                s.setModel(request.getParameter("model"));
                s.setType(request.getParameter("type"));
                s.setDate(request.getParameter("date"));
                session.save(s);
                response.getWriter().println("Inserted Successfully");

            } else if (choice == 2) {
                int id = Integer.parseInt(request.getParameter("id"));
                Service s = session.get(Service.class, id);
                if (s != null) {
                    s.setOwner(request.getParameter("owner"));
                    s.setModel(request.getParameter("model"));
                    s.setType(request.getParameter("type"));
                    s.setDate(request.getParameter("date"));
                    session.update(s);
                    response.getWriter().println("Updated Successfully");
                } else {
                    response.getWriter().println("Service not found.");
                }

            } else if (choice == 3) {
                int id = Integer.parseInt(request.getParameter("id"));
                Service s = session.get(Service.class, id);
                if (s != null) {
                    session.delete(s);
                    response.getWriter().println("Deleted Successfully");
                } else {
                    response.getWriter().println("Service not found.");
                }

            } else if (choice == 4) {
                List<Service> list = session.createQuery("from Service", Service.class).list();
                for (Service s : list) {
                    response.getWriter().println("ID: " + s.getId());
                    response.getWriter().println("Owner: " + s.getOwner());
                    response.getWriter().println("Model: " + s.getModel());
                    response.getWriter().println("Type: " + s.getType());
                    response.getWriter().println("Date: " + s.getDate());
                    response.getWriter().println("------------------------");
                }
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
