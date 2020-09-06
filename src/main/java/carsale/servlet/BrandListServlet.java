package carsale.servlet;

import carsale.store.CarBrandDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class BrandListServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(BrandListServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.debug("=== doGet === start query ===");

        List<String> list = CarBrandDB.getAllNameList();

        JSONArray jo = new JSONArray(list);

        resp.setContentType("text/json");

        logger.debug("=== doGet === write query result into response ===");

        PrintWriter out = resp.getWriter();
        out.write(jo.toString());
        out.flush();
    }
}
