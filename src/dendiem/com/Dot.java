package dendiem.com;

import java.awt.*;

public class Dot implements Comparable<Dot>{
    private int x;
    private int y;
    private int dClass;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    private double weight;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    private double distance;
    public Dot(int x, int y, int dClass) {
        this.x = x;
        this.y = y;
        this.dClass = dClass;
    }

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
        this.dClass=1;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getdClass() {
        return dClass;
    }

    public void setdClass(int dClass) {
        this.dClass = dClass;
    }

     static double distance(Dot a,Dot b){

        return Math.sqrt(Math.pow(a.getX()-b.getX(),2)+Math.pow(a.getY()-b.getY(),2));
     }


    @Override
    public int compareTo(Dot o) {
        return (int)(distance - o.distance);
    }

    @Override
    public String toString() {
        return "Dot{" +
                "distance=" + distance +
                '}';
    }

    public void choiceClass(){
        if(x>400&&y>400){
            dClass=9;
        }else if(x>250&&y>400){
            dClass=8;

        }else if(x>100&&y>400){
            dClass=7;

        }else if(x>400&&y>250){
            dClass=6;

        }else if(x>250&&y>250){
            dClass=5;

        }else if(x>100&&y>250){
            dClass=4;

        }else if(x>400&&y>100){
            dClass=3;

        }else if(x>250&&y>100){
            dClass=2;

        }else if(x>100&&y>100){
            dClass=1;

        }
    }
}
