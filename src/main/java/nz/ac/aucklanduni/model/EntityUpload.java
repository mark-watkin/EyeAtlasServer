package nz.ac.aucklanduni.model;

import java.util.ArrayList;
import java.util.List;

public class EntityUpload {
    private String name;
    private String category;
    private List<String> tags = new ArrayList<String>();
    private byte[] image;
}
