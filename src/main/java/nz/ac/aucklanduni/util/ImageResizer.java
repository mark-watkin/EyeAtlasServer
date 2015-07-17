package nz.ac.aucklanduni.util;

import imageUtil.Image;
import imageUtil.ImageLoader;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

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
    public static String resizeImage(String inputFilePath, String outputPath, String outputName, int percentage, float quality) throws IOException {

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
        Image image = ImageLoader.fromFile(file);

        int IMG_WIDTH = image.getWidth() * percentage / 100;
        int IMG_HEIGHT = image.getHeight() * percentage / 100;

        // Set Minimum Width
        if (IMG_WIDTH < 512) {
            IMG_WIDTH = 512;
        }

        File outFile;
        if(outputPath.charAt(outputPath.length()-1) == '/') {
            outFile = new File(outputPath + outputName + ".jpg");
        } else {
            outFile = new File(outputPath + File.separator + outputName + ".jpg");
        }

        image.getResizedToWidth(IMG_WIDTH).writeToJPG(outFile, quality);
        return outFile.getPath();

    }
}
