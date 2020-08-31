package carsale.servlet;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import carsale.model.Author;
import carsale.model.Item;
import carsale.store.ItemDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.GregorianCalendar;

public class EditServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(EditServlet.class);

    private static final String NEW_USER_ID = "0";
    private static final String CHECKING_FLAG = "1";

    private GregorianCalendar getCurrent() {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(System.currentTimeMillis());
        return gc;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.debug("=== doGet ===");

        String id = req.getParameter("id");

        if ("new".equals(id)) {
            req.setAttribute("item", new Item(0, ""));
        } else {
            req.setAttribute("item", ItemDB.getItem(Integer.parseInt(id)));
        }

        String check = req.getParameter("check");
        if ("1".equals(check)) {
            doPost(req, resp);
        } else {
            req.getRequestDispatcher("WEB-INF/templates/edit.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.debug("=== doPost ===");

        String id = req.getParameter("id");
        String desc = req.getParameter("desc");
        String check = req.getParameter("check");
        Author author = (Author) req.getSession().getAttribute("author");

        if (NEW_USER_ID.equals(id)) {
            Item item = new Item(desc);
            item.setCreated(getCurrent());
            item.setAuthor(author);
            ItemDB.save(item);
        } else if (CHECKING_FLAG.equals(check)) {
            Item item = (Item) req.getAttribute("item");
            if (item.getDone() == null) {
                item.setDone(getCurrent());
            } else {
                item.setDone(null);
            }
            ItemDB.saveOrUpdate(item);
        } else {
            Item item = ItemDB.getItem(Integer.parseInt(id));
            item.setDesc(desc);
            ItemDB.saveOrUpdate(item);
        }

        resp.sendRedirect(req.getContextPath() + "/index.do");
    }
}
