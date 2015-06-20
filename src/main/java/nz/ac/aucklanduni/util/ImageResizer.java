package nz.ac.aucklanduni.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageResizer {

    /**
     * Resize an image based on percentage.
     *
     * @param inputFilePath
     * @param outputPath
     * @param percentage
     * @throws IOException
     */
    public static String resizeImage(String inputFilePath, String outputPath, String outputName, int percentage) throws IOException {

        File file = new File(inputFilePath);
        FileInputStream fis = new FileInputStream(file);
        BufferedImage originalImage = ImageIO.read(fis); //reading the image file

        final int IMG_WIDTH = originalImage.getWidth() * percentage / 100;
        final int IMG_HEIGHT = originalImage.getHeight() * percentage / 100;

        return resizeImage(originalImage, outputPath, outputName, IMG_WIDTH, IMG_HEIGHT, percentage);

    }

    public static String resizeImage(BufferedImage originalImage, String outputPath, String outputName, int percentage) throws IOException {

        final int IMG_WIDTH = originalImage.getWidth() * percentage / 100;
        final int IMG_HEIGHT = originalImage.getHeight() * percentage / 100;

        return resizeImage(originalImage, outputPath, outputName, IMG_WIDTH, IMG_HEIGHT, percentage);

    }

    /**
     * Resize an image based on specified width and height
     *
     * @param originalImage original image path
     * @param width width of new image
     * @param height height of new image
     * @throws IOException
     */
    private static String resizeImage(BufferedImage originalImage, String outputPath, String outputName, int width, int height, int percentage)
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
            outFile = new File(outputPath + outputName + ".png");
        } else {
            outFile = new File(outputPath + File.separator + outputName + ".png");
        }
        ImageIO.write(resizedImage, "png", outFile);

        g.dispose();

        return outFile.getPath();
    }
}
