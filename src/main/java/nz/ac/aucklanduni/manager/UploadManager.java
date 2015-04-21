package nz.ac.aucklanduni.manager;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class UploadManager {

    public void uplaod(MultipartFile file) throws IOException {
        String path = setUpFolderStucture();
        writeImageToTempStorage(file, path);

        cleanUp(path);
    }

    private String setUpFolderStucture() {
        // Creating the directory to store file
        File dir = new File("tmp");
        //TODO create folder with name id that can be easly cleaned
        if (!dir.exists())
            dir.mkdirs();

        return null;
    }

    private void cleanUp(String path) {
    }

    private void writeImageToTempStorage(MultipartFile file, String path) throws IOException {
        byte[] bytes = file.getBytes();

        // Create the file on server
        File serverFile = new File(path
                + File.separator + file.getOriginalFilename()); // TODO Replace original filename with a unique ID.
        BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();
    }
}
