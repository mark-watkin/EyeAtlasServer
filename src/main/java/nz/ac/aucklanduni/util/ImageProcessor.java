package nz.ac.aucklanduni.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class ImageProcessor {

    public static final String ROOT_FOLDER = "/tmp/";
    public static final String IMAGE_HEADER = "^data:image/[^;]*;base64,?";

    private static String writeImageToTempStorage(String fileName, String image) throws IOException {
        String imageData = image.replaceFirst(IMAGE_HEADER,"");
        byte[] byteArray = Base64.decodeBase64(imageData);

        BufferedImage bi = ImageIO.read(new ByteArrayInputStream(byteArray));


        File img = new File(fileName + ".png");
        ImageIO.write(bi, "png", img);
        return img.getPath();
    }

    public static void uploadImage(String keyName, String image) {
        try {
            String folderPath = createFolder(keyName);
            String filePath = writeImageToTempStorage(folderPath + File.separator + keyName, image);

//            ImageSplitter.splitImageBySize(filePath, folderPath, 1000, 1000);
            S3ImageAdapter.upload(filePath, image);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void deleteImage(String keyName) {
        try {
            S3ImageAdapter.delete(keyName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cleanUpTmpFolder(String folderPath) {
        try {
            File folderToRemove = new File(folderPath);
            FileUtils.cleanDirectory(folderToRemove);
            FileUtils.deleteDirectory(folderToRemove);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String createFolder(String keyName) {
        File outdir = new File(ROOT_FOLDER + keyName);

        if(!outdir.exists()) {
            outdir.mkdir();
        } else if(!outdir.isDirectory()) {
            outdir.delete();
            outdir.mkdir();
        }

        return outdir.getPath();

    }
}
