package nz.ac.aucklanduni.util;

import imageUtil.Image;
import imageUtil.ImageLoader;

import java.io.File;
import java.io.IOException;


public class ImageSplitter {

    /**
     *  Splits image based on fixed tile width and tile height
     *
     * @param inputFilePath
     * @param outputPath
     * @param dimension
     * @throws java.io.IOException
     */
    public static Dimension2D splitImageBySize(String inputFilePath, String outputPath, Dimension2D dimension, float quality) throws IOException {
        File file = new File(inputFilePath);
        Image image = ImageLoader.fromFile(file);

        int tileWidth = dimension.getHorizontal();
        int tileHeight = dimension.getVertical();

        int rows = (int)(Math.ceil((float)image.getHeight() / tileHeight));
        int cols = (int)(Math.ceil((float)image.getWidth() / tileWidth));

        int width;
        int height;
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {

                //Initialize the image array with image chunks
                if(x < rows - 1 && y < cols - 1) {
                    width = tileWidth;
                    height = tileHeight;
                } else if (x < rows -1 && y >= cols -1) {
                    width = image.getWidth() - y * tileWidth;
                    height = tileHeight;
                } else if (x >= rows -1 && y < cols -1) {
                    width = tileWidth;
                    height = image.getHeight() - x * tileHeight;
                } else {
                    width = image.getWidth() - y * tileWidth;
                    height = image.getHeight() - x * tileHeight;
                }

                File outFile;
                if(outputPath.charAt(outputPath.length()-1) == '/') {
                    outFile = new File(outputPath + "img_" + y + "_" + x + ".jpg");
                } else {
                    outFile = new File(outputPath + File.separator + "img_" + y + "_" + x + ".jpg");
                }

                image.crop(tileWidth * y, tileHeight * x, tileWidth * y + width, tileHeight * x + height).writeToJPG(outFile, quality);

            }
        }

        return new Dimension2D(cols, rows);
    }
}
