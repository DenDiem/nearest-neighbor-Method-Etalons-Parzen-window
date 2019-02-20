package dendiem.com;

import java.awt.*;
import java.util.Random;

import static dendiem.com.FloFrame.dotArr;
import static dendiem.com.FloFrame.myClasses;

public class Main {

    public static void main(String[] args) {
        Random rand = new Random();

        Dot dot;

        for(int i =0; i< 100;++i) {
            int x = rand.nextInt(448) + 101;
            int y = rand.nextInt(448) + 101;
            dot =  new Dot(x,y);
            dot.choiceClass();
            dotArr.add(dot);
        }


        myClasses.add(new MyClass(1,new Color(255, 0, 0)));
        myClasses.add(new MyClass(2,new Color(0, 255, 0)));
        myClasses.add(new MyClass(3,new Color(0,0,255)));
        myClasses.add(new MyClass(4,new Color(185, 34, 144)));
        myClasses.add(new MyClass(5,new Color(170, 163, 44)));
        myClasses.add(new MyClass(6,new Color(35, 147, 141)));
        myClasses.add(new MyClass(7,new Color(30, 60, 30)));
        myClasses.add(new MyClass(8,new Color(171, 96, 109)));
        myClasses.add(new MyClass(9,new Color(73, 12, 91)));

        FloFrame frame = new FloFrame();



    }
}
