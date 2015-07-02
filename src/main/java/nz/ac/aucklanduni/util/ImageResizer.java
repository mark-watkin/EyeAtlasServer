package nz.ac.aucklanduni.util;

import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ImageResizer {

    public static final int MINIMUM_FILE_SIZE_IN_BYTE = 200 * 1024; // 200 kB

    /**
     * Resize an image based on percentage.
     *
     * @param inputFilePath
     * @param outputPath
     * @param percentage
     * @throws IOException
     */
    public static String resizeImage(String inputFilePath, String outputPath, String outputName, int percentage) throws IOException {

        // Open up the input file
        File inputFile = new File(inputFilePath);



        // If the image is not being re-sized (100% of original image), or if the image size is already small enough (200 kB),
        // then simply copy the file
        if (percentage == 100 || inputFile.length() <  MINIMUM_FILE_SIZE_IN_BYTE) {
            String outputFilePath;
            if(outputPath.charAt(outputPath.length()-1) == '/') {
                outputFilePath = outputPath + outputName + ".jpg";
            } else {
                outputFilePath = outputPath + File.separator + outputName + ".jpg";
            }

            File outputFile = new File(outputFilePath);
            FileUtils.copyFile(inputFile, outputFile);
            return outputFile.getPath();
        }

        File file = new File(inputFilePath);
        FileInputStream fis = new FileInputStream(file);
        BufferedImage originalImage = ImageIO.read(fis); //reading the image file

        final int IMG_WIDTH = originalImage.getWidth() * percentage / 100;
        final int IMG_HEIGHT = originalImage.getHeight() * percentage / 100;

        String result = resizeImage(originalImage, outputPath, outputName, IMG_WIDTH, IMG_HEIGHT);
        fis.close();
        return result;

    }

    public static String resizeImage(BufferedImage originalImage, String outputPath, String outputName, int percentage) throws IOException {

        final int IMG_WIDTH = originalImage.getWidth() * percentage / 100;
        final int IMG_HEIGHT = originalImage.getHeight() * percentage / 100;

        String result = resizeImage(originalImage, outputPath, outputName, IMG_WIDTH, IMG_HEIGHT);
        return result;
    }

    /**
     * Resize an image based on specified width and height
     *
     * @param originalImage original image path
     * @param width width of new image
     * @param height height of new image
     * @throws IOException
     */
    private static String resizeImage(BufferedImage originalImage, String outputPath, String outputName, int width, int height)
            throws IOException {
        BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());

        Graphics2D g = resizedImage.createGraphics();
        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(originalImage, 0, 0, width, height, null);

        File outFile;
        if(outputPath.charAt(outputPath.length()-1) == '/') {
            outFile = new File(outputPath + outputName + ".jpg");
        } else {
            outFile = new File(outputPath + File.separator + outputName + ".jpg");
        }
        ImageIO.write(resizedImage, "jpg", outFile);

        g.dispose();

        return outFile.getPath();
    }
}
