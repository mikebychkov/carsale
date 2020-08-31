package carsale.servlet;

import carsale.model.Author;
import carsale.store.AuthorDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/templates/reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Author author = new Author(0, name);
        author.setEmail(email);
        author.setPassword(password);

        try {
            AuthorDB.save(author);
            resp.sendRedirect(req.getContextPath() + "/auth.do");
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            doGet(req, resp);
        }
    }
}

