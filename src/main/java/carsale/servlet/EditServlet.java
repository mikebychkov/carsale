package carsale.servlet;

import carsale.store.CarBodyTypeDB;
import carsale.store.CarBrandDB;
import carsale.store.CarModelDB;
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

    private static final String NEW_ITEM_ID = "0";
    private static final String CHECKING_FLAG = "1";

    private GregorianCalendar getCurrent() {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(System.currentTimeMillis());
        return gc;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.debug("=== doGet ===");

        int id = getInt(req.getParameter("id"));

        Item item = null;
        if (id == 0) {
            item = new Item(0, "");
        } else {
            item = ItemDB.getItem(id);
        }
        req.setAttribute("item", item);

        // ITEM CHANGE AUTHORIZATION
        Author author = (Author) req.getSession().getAttribute("author");
        if (id != 0 && !author.equals(item.getAuthor())) {
            resp.sendRedirect(req.getContextPath() + "/index.do");
            return;
        }

        String check = req.getParameter("check");
        if (CHECKING_FLAG.equals(check)) {
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
        String photo = req.getParameter("photo");
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String body = req.getParameter("body");
        String color = req.getParameter("color");

        Author author = (Author) req.getSession().getAttribute("author");

        if (CHECKING_FLAG.equals(check)) {
            Item item = (Item) req.getAttribute("item");
            if (item.getDone() == null) {
                item.setDone(getCurrent());
            } else {
                item.setDone(null);
            }
            ItemDB.update(item);
        } else {

            Item item;
            if (NEW_ITEM_ID.equals(id)) {
                item = new Item(desc);
                item.setCreated(getCurrent());
                item.setAuthor(author);
            } else {
                item = ItemDB.getItem(Integer.parseInt(id));
            }

            item.setDesc(desc);
            item.setPhoto(photo);
            item.setColor(color);

            item.setBrand(CarBrandDB.getByNameOrNew(brand));
            item.setModel(CarModelDB.getByNameOrNew(model));
            item.setBody(CarBodyTypeDB.getByNameOrNew(body));

            if (NEW_ITEM_ID.equals(id)) {
                ItemDB.save(item);
            } else {
                ItemDB.update(item);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/index.do");
    }

    private int getInt(String param) {
        try {
            return Integer.parseInt(param);
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return 0;
    }
}
