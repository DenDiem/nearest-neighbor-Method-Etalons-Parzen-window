package dendiem.com;

import java.awt.*;

public class MyClass {
    public MyClass(int dClass, Color color) {
        this.dClass = dClass;
        this.color = color;
        this.sumWeight=0;
        this.numObj=0;
    }

    public  void normalize(){
        this.sumWeight=0;
        this.numObj=0;
    }
    public int getdClass() {
        return dClass;
    }

    public void setdClass(int dClass) {
        this.dClass = dClass;
    }

    public double getSumWeight() {
        return sumWeight;
    }

    public void setSumWeight(double sumWeight) {
        this.sumWeight += sumWeight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private int dClass;
    private double sumWeight;
    private Color color;
    private int numObj;

    public int getNumObj() {
        return numObj;
    }

    public void setNumObj(int numObj) {
        this.numObj += numObj;
    }

    @Override
    public String toString() {
        return "{" +
                "" + dClass +
                ", sum=" + sumWeight +
                ", numO=" + numObj +
                '}';
    }
}
