package carsale.servlet;

import carsale.model.Item;
import carsale.store.ImgStore;
import carsale.store.ItemDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@MultipartConfig
public class PhotoServlet extends HttpServlet {

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
        String id = req.getParameter("id");
        if (!"0".equals(id)) {
            Item item = ItemDB.getItem(Integer.parseInt(id));
            item.setPhoto(fileName);
            ItemDB.update(item);
        }
        //
        req.setAttribute("id", Integer.parseInt(id));
        req.setAttribute("fileName", fileName);
    }
}
