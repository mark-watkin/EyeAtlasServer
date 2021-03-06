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


        File img = new File(fileName + ".jpg");
        ImageIO.write(bi, "jpg", img);
        return img.getPath();
    }

    public static void process(String keyName, String image) throws IOException, InterruptedException {

        String mainPath = null;
        try {
            mainPath = createFolder(ROOT_FOLDER + keyName);
            String imgPath = writeImageToTempStorage(mainPath + File.separator + keyName, image);

            // Sequential
            processImage(imgPath, mainPath, "thumbnail", false, 10, null);
            processImage(imgPath, mainPath, "preview", false, 25, null);
            processImage(imgPath, mainPath, "125", true, 25, null);
            processImage(imgPath, mainPath, "250", true, 50, null);
            processImage(imgPath, mainPath, "500", true, 75, null);
            processImage(imgPath, mainPath, "1000", true, 100, null);

            // Upload whole image dir to S3
            S3ImageAdapter.uploadDirectory(mainPath, keyName);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        catch (InterruptedException e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            cleanUpTmpStorage(mainPath);
        }
    }

    public static void deleteImage(String keyName) {
        try {
            S3ImageAdapter.delete(keyName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cleanUpTmpStorage(String folderPath) {
        if(folderPath == null) {
            return;
        }
        try {
            File folderToRemove = new File(folderPath);
            FileUtils.cleanDirectory(folderToRemove);
            FileUtils.deleteDirectory(folderToRemove);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String createFolder(String folderName) {
        File outdir = new File(folderName);

        if(!outdir.exists()) {
            outdir.mkdir();
        } else if(!outdir.isDirectory()) {
            outdir.delete();
            outdir.mkdir();
        }

        return outdir.getPath();

    }

    private static void processImage(String imagePath, String mainFolderPath, String fileName, boolean split,
                              int percentage, Dimension2D dimension) throws IOException {

        Dimension2D tileDimension = new Dimension2D(2000, 2000);

        if (dimension != null) {
            tileDimension = dimension;
        }

        String subFolder = ImageProcessor.createFolder(mainFolderPath + File.separator + fileName);

        try {
            System.out.println(fileName + " START");
            String resizedImagePath = ImageResizer.resizeImage(imagePath, subFolder, fileName, percentage);
            if(split) {
                ImageSplitter.splitImageBySize(resizedImagePath, subFolder, tileDimension);

                // Delete the non splitted file
                File file = new File(resizedImagePath);
                file.delete();
            }
            System.out.println(fileName + " DONE");
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
