package model;

import java.io.Serializable;

public class Bar implements Serializable, DominoModel {
    private int side1;
    private int side2;

    public Bar(int side1, int side2) {
        this.side1 = side1;
        this.side2 = side2;
    }

    public int sumSides() {
        return this.side1 + this.side2;
    }

    public void revertBar() {
        int temp = this.side2;
        this.side2 = this.side1;
        this.side1 = temp;
    }

    public String getString() {
        return this.side1 + ":" + this.side2;
    }

    public int getSide1() {
        return side1;
    }

    public int getSide2() {
        return side2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bar bar = (Bar) o;

        if (side1 != bar.side1) return false;
        return side2 == bar.side2;
    }

    @Override
    public int hashCode() {
        int result = side1;
        result = 31 * result + side2;
        return result;
    }
}
