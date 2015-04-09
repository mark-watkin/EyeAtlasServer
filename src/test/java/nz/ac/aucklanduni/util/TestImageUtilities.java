package nz.ac.aucklanduni.util;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.testng.FileAssert.fail;

public class TestImageUtilities {

    private final String inputPath = "src/test/resources/tmp/";
    private final String inputFileName = "test_seal.jpg";

    private final String outputPath = "src/test/resources/out/";
    private final String expectedPath = "src/test/resources/expected/";


    @Before
    public void setUp() throws IOException {
        FileUtils.cleanDirectory(new File(outputPath));
    }

    @Test
    public void testResizeImage() throws Exception {

    }

    @Test
    public void testSplitImageBySize() throws Exception {



        System.out.println(System.getProperty("user.dir"));
        ImageUtilities.splitImageBySize(inputPath + inputFileName, outputPath, 150, 150);

        assertThat(FileUtils.contentEquals(new File(expectedPath + "img_0_3.png"), new File(outputPath + "img_0_3.png")), is(true));
        assertThat(FileUtils.contentEquals(new File(expectedPath + "img_2_1.png"), new File(outputPath + "img_2_1.png")), is(true));
        assertThat(FileUtils.contentEquals(new File(expectedPath + "img_3_1.png"), new File(outputPath + "img_3_1.png")), is(true));
        assertThat(FileUtils.contentEquals(new File(expectedPath + "img_4_2.png"), new File(outputPath + "img_4_2.png")), is(true));
    }
}
