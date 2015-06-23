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

public class TestImageSplitter {

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
    public void testSplitImageBySize() throws Exception {

        System.out.println(System.getProperty("user.dir"));
        ImageSplitter.splitImageBySize(inputPath + inputFileName, outputPath, new Dimension2D(150, 150));

        assertThat(FileUtils.contentEquals(new File(expectedPath + "img_0_3.jpg"), new File(outputPath + "img_0_3.jpg")), is(true));
        assertThat(FileUtils.contentEquals(new File(expectedPath + "img_2_1.jpg"), new File(outputPath + "img_2_1.jpg")), is(true));
        assertThat(FileUtils.contentEquals(new File(expectedPath + "img_3_1.jpg"), new File(outputPath + "img_3_1.jpg")), is(true));
        assertThat(FileUtils.contentEquals(new File(expectedPath + "img_4_2.jpg"), new File(outputPath + "img_4_2.jpg")), is(true));
    }
}
