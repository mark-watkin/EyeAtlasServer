package nz.ac.aucklanduni.util;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestImageResizer {

    private final String inputPath = "src/test/resources/tmp/";
    private final String inputFileName = "test_seal.jpg";

    private final String outputPath = "src/test/resources/out/";
    private final String expectedPath = "src/test/resources/expected/";


    @Before
    public void setUp() throws IOException {
        File outdir = new File(outputPath);

        if(!outdir.exists()) {
            outdir.mkdir();
        } else if(!outdir.isDirectory()) {
            outdir.delete();
            outdir.mkdir();
        }

        FileUtils.cleanDirectory(outdir);
    }

    @After
    public void tearDown() throws IOException {
        FileUtils.cleanDirectory(new File(outputPath));
    }

    @Test
    public void testResizeSmallImage() throws Exception {
        ImageResizer.resizeImage(inputPath + inputFileName, outputPath, String.valueOf(50), 50, 1.0f);

        File file = new File(inputPath + inputFileName);
        FileInputStream fis = new FileInputStream(file);
        BufferedImage originalImage = ImageIO.read(fis); //reading the image file

        file = new File(outputPath + "50.jpg");
        fis = new FileInputStream(file);
        BufferedImage expectedImage = ImageIO.read(fis); //reading the image file

        assertThat(originalImage.getWidth(), is(expectedImage.getWidth()));
        assertThat(originalImage.getHeight(), is(expectedImage.getHeight()));

        fis.close();
    }
}