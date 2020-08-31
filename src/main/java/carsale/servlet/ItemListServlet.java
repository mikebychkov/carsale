package carsale.servlet;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.json.JSONArray;
import carsale.model.BeautyItem;
import carsale.model.Item;
import carsale.store.ItemDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class ItemListServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(ItemListServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.debug("=== doGet === start query ===");

        String listType = req.getParameter("list");

        List<Item> list;
        if ("actual".equals(listType)) {
            list = ItemDB.getActualItemList();
        } else {
            list = ItemDB.getItemList();
        }
        List<BeautyItem> bList = list.stream().map(i -> new BeautyItem(i)).collect(Collectors.toList());

        JSONArray jo = new JSONArray(bList);

        resp.setContentType("text/json");

        logger.debug("=== doGet === write query result into response ===");

        PrintWriter out = resp.getWriter();
        out.write(jo.toString());
        out.flush();
    }
}
