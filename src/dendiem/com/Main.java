package dendiem.com;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static dendiem.com.FloFrame.*;

public class Main {
    public static void initEtalon(){
        ArrayList<Dot> worklist = new ArrayList<>();
        int mindistance = Integer.MAX_VALUE;
        int[] arr = new int[3];
        int sumdistanse = 0;
        for(int i = 0; i<9; ++i) {
            for (Dot curr : dotArr
            ) {
                if (curr.getdClass() == i + 1) {
                    worklist.add(curr);
                }

            }
            if (!worklist.isEmpty()) {
                for (Dot curr : worklist
                ) {
                    for (Dot dot : worklist) {

                        sumdistanse += Dot.distance(dot, curr);
                    }
                    if (sumdistanse < mindistance) {
                        mindistance = sumdistanse;
                        arr[0] = curr.getX();
                        arr[1] = curr.getY();
                        arr[2] = curr.getdClass();
                    }
                    sumdistanse = 0;
                }
                worklist.clear();
                etalonArr.add(new Dot(arr[0], arr[1], arr[2]));
                mindistance = Integer.MAX_VALUE;
                sumdistanse = 0;
            }
            worklist.clear();
            mindistance = Integer.MAX_VALUE;
            sumdistanse = 0;
        }

    }
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

        initEtalon();

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

        System.out.println(Arrays.toString(etalonArr.toArray()));


        ArrayList<Integer> al = new ArrayList<Integer>();
        al.add(1);
        al.add(2);
        al.add(3);
        ArrayList<Integer> al2 = new ArrayList<Integer>(al);
        al.remove(0);
        al = new ArrayList<>(al2);
        al.remove(0);
        al = new ArrayList<>(al2);
        System.out.println(Arrays.toString(al.toArray()));
        System.out.println(Arrays.toString(al2.toArray()));
    }
}
