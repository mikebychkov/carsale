package carsale.store;

import java.io.File;

public class ImgStore {

    public static File folder() {
        File folder = new File(folderName());
        if (!folder.exists()) {
            folder.mkdir();
        }
        return folder;
    }

    public static String folderName() {
        return "carsale/images";
    }
}
