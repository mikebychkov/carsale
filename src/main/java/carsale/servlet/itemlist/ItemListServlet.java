package carsale.servlet.itemlist;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ItemListServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(ItemListServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.debug("=== doGet === start query ===");

        String listType = req.getParameter("list");
        String filter = req.getParameter("filter");
        String brand = req.getParameter("brand");

        Boolean actual = "actual".equals(listType);

        if (filter == null || filter.isEmpty()) {
            filter = "all";
        }

        //

        Map<String, Object> params = new HashMap<>();
        params.put("actual", actual);
        params.put("brand", brand);

        List<Item> list = new ItemGettingDescriptor().getItemList(filter, params);

        List<BeautyItem> bList = list.stream().map(i -> new BeautyItem(i)).collect(Collectors.toList());

        JSONArray jo = new JSONArray(bList);

        resp.setContentType("text/json");

        logger.debug("=== doGet === write query result into response ===");

        PrintWriter out = resp.getWriter();
        out.write(jo.toString());
        out.flush();
    }
}
