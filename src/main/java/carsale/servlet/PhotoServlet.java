package carsale.servlet;

import carsale.model.Item;
import carsale.store.ImgStore;
import carsale.store.ItemDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.type.descriptor.sql.NumericTypeDescriptor;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@MultipartConfig
public class PhotoServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(PhotoServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        resp.setContentType("name=" + name);
        resp.setContentType("image/png");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
        File file = new File(ImgStore.folderName() + File.separator + name);
        try (FileInputStream in = new FileInputStream(file)) {
            resp.getOutputStream().write(in.readAllBytes());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        //
        File folder = ImgStore.folder();
        File file = new File(folder + File.separator + fileName);
        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(filePart.getInputStream().readAllBytes());
        }
        //
        int id = getInt(req.getParameter("id"));
        if (id != 0) {
            Item item = ItemDB.getItem(id);
            item.setPhoto(fileName);
            ItemDB.update(item);
        }
        //
        PrintWriter writer = resp.getWriter();
        writer.println(fileName);
        writer.flush();
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
