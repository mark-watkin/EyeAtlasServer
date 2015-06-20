package nz.ac.aucklanduni.util;

public class Dimension2D {

    private Integer horizontal;
    private Integer vertical;

    public Dimension2D() {}

    public Dimension2D(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public Integer getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(Integer horizontal) {
        this.horizontal = horizontal;
    }

    public Integer getVertical() {
        return vertical;
    }

    public void setVertical(Integer vertical) {
        this.vertical = vertical;
    }
}
