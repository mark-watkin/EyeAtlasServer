package nz.ac.aucklanduni.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
            String imgFilePath = writeImageToTempStorage(mainPath + File.separator + keyName, image);

//            // TODO Resize image & Split image

            // Sequential
//            String subFolder = createFolder(mainPath + File.separator + "125");
//            ImageResizer.resizeImage(originalImage, subFolder, 25);
//
//            subFolder = createFolder(mainPath + File.separator + "250");
//            ImageResizer.resizeImage(originalImage, subFolder, 50);
//
//            subFolder = createFolder(mainPath + File.separator + "500");
//            ImageResizer.resizeImage(originalImage, subFolder, 75);
//
//            subFolder = createFolder(mainPath + File.separator + "1000");
//            ImageResizer.resizeImage(originalImage, subFolder, 100);


            // Parallel
            ExecutorService EXEC = Executors.newCachedThreadPool();
            List<Callable<Void>> tasks = new ArrayList<Callable<Void>>();

            ImageProcessTask task;
            task = new ImageProcessTask(imgFilePath, mainPath, "thumbnail").setResizePercent(10).enableSplit(false);
            tasks.add(task);
            task = new ImageProcessTask(imgFilePath, mainPath, "preview").setResizePercent(25).enableSplit(false);
            tasks.add(task);
            task = new ImageProcessTask(imgFilePath, mainPath, "125").setResizePercent(25);
            tasks.add(task);
            task = new ImageProcessTask(imgFilePath, mainPath, "250").setResizePercent(50);
            tasks.add(task);
            task = new ImageProcessTask(imgFilePath, mainPath, "500").setResizePercent(75);
            tasks.add(task);
            task = new ImageProcessTask(imgFilePath, mainPath, "1000").setResizePercent(100);
            tasks.add(task);
            EXEC.invokeAll(tasks);

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
}

class ImageProcessTask implements Callable<Void> {

    String folderPath;
    String fileName;
    String imagePath;
    Integer percentage = 20;
    Dimension2D dimension = new Dimension2D(1200, 1200);
    boolean split = true;

    public ImageProcessTask(String imagePath, String rootFolder, String fileName) {
        this.folderPath = rootFolder;
        this.imagePath = imagePath;
        this.fileName = fileName;
    }

    public ImageProcessTask setResizePercent(Integer percentage) {
        this.percentage = percentage;
        return this;
    }

    public ImageProcessTask setSplitDimension(Dimension2D dimension) {
        this.dimension = dimension;
        return this;
    }

    public ImageProcessTask enableSplit(boolean split) {
        this.split = split;
        return this;
    }

    @Override
    public Void call() throws Exception {

        String subFolder = ImageProcessor.createFolder(folderPath + File.separator + fileName);
        try {
            System.out.println("" + fileName + " START");
            String resizedImagePath = ImageResizer.resizeImage(imagePath, subFolder, fileName, percentage);

            if(split) {
                ImageSplitter.splitImageBySize(resizedImagePath, subFolder, dimension);

                // Delete the non splitted file
                File file = new File(resizedImagePath);
                file.delete();
            }

            System.out.println("" + fileName + " DONE");

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

        return null;
    }
}
