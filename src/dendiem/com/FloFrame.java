package dendiem.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class FloFrame extends JFrame{

    static boolean dotAdd = true;
    static boolean NN = true;
    static int radius = 0;
    static int numofel = 0;
    static Dot currentDot = new Dot(0,0,1);
    static ArrayList<Dot> dotArr = new ArrayList<>();
    static ArrayList<MyClass> myClasses = new ArrayList<>();
    private JTextField eEnterX;
    private JLabel lEnterX;
    private JLabel lEnterY;
    private JTextField eEnterY;
    private JButton bGenerate;
    private JPanel fMain;
    private JCheckBox showGridCheckBox;
    private JLabel lEnteK;
    private JTextField eEnterK;
    private JButton bSwitch;
    private JLabel lProg;
    private JButton generateRandomButton;
    private JLabel lRandom;
    private JTextField eRandom;
    private JCheckBox bAdds;
    private DrawPanel drawPanel;
    private RightPanel rightPanel;
    static boolean grid = true;

    public FloFrame() {

        setSize(800,800);
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        drawPanel = new DrawPanel();
        rightPanel = new RightPanel();
        panel.add(fMain,BorderLayout.PAGE_START);
        panel.add(drawPanel,BorderLayout.CENTER);
        main.add(panel,BorderLayout.CENTER);
        main.add(rightPanel,BorderLayout.EAST);
        setContentPane(main);


        bGenerate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                Integer x = Integer.parseInt(eEnterX.getText());
                Integer y = Integer.parseInt(eEnterY.getText());
                Integer k = Integer.parseInt(eEnterK.getText());
                currentDot = new Dot(x,y);

                if(NN){
                    progNN(currentDot,k);
                }else{
                    progParsen(currentDot,k);

                }



                rightPanel.repaint();



                drawPanel.repaint();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        showGridCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(grid){
                    showGridCheckBox.setText("Show Grid");
                    grid=false;
                }else {
                    showGridCheckBox.setText("Hide Grid");
                    grid = true;
                }
                drawPanel.repaint();
            }

        });
        bSwitch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(NN){
                    NN=false;
                    bSwitch.setText("Switch to NN");
                    lEnteK.setText("Radius: ");
                    lProg.setText("Parsen Window");

                }else{
                    NN=true;
                    bSwitch.setText("Switch to Parsen");
                    lEnteK.setText("K: ");
                    lProg.setText("Nearest Neighbor");
                    
                }

            }
        });
        generateRandomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer k = Integer.parseInt(eEnterK.getText());
                Integer n = Integer.parseInt(eRandom.getText());
                Random rand = new Random();
                Dot copy;
                int x;
                int y;
                int right = 0;
                for(int i =0; i< n;++i) {

                    x = rand.nextInt(448) + 101;
                    y = rand.nextInt(448) + 101;
                    copy = new Dot(x,y);
                    copy.choiceClass();

                    currentDot = new Dot(x,y);
                    if(NN){
                        progNN(currentDot,k);
                    }else{
                        progParsen(currentDot,k);

                    }
                    if(copy.getdClass()==currentDot.getdClass())
                        ++right;
                }

                drawPanel.repaint();
                rightPanel.repaint();
                System.out.println("Right: " + right);
                infoBox("Right: " + right+"/"+n,"[OutPut]");


            }
        });
        bAdds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dotAdd=!dotAdd;
            }
        });
    }
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    private void progNN(Dot currentDot,int k){
        for (MyClass mc:myClasses
        ) {

            mc.normalize();
        }
        numofel=0;


        for (Dot dot: dotArr ) {

            dot.setDistance(Dot.distance(dot,currentDot));
        }
        dotArr.sort((o1, o2) ->(int) (o1.getDistance()-o2.getDistance()));
        double weight;
        Dot curr;
        for(int i=0;i<k&&i<dotArr.size();++i){
            numofel++;
            curr = dotArr.get(i);
            weight = ((k-i)/(k*1.0));
            myClasses.get(curr.getdClass()-1).setSumWeight(weight);
            myClasses.get(curr.getdClass()-1).setNumObj(1);
            if(i==k-1) radius = (int)curr.getDistance();

        }
        choiseClassForDot();
        if (dotAdd)
            dotArr.add(currentDot);
    }
    private void progParsen(Dot currentDot,int radius){

        for (MyClass mc:myClasses
        ) {

            mc.normalize();
        }
        numofel=0;




        for (Dot dot: dotArr ) {

            dot.setDistance(Dot.distance(dot,currentDot));
        }
        dotArr.sort((o1, o2) ->(int) (o1.getDistance()-o2.getDistance()));
        double weight;
        Dot curr;
        int i =0;
        FloFrame.radius = radius;
        do{
            numofel++;
            curr = dotArr.get(i);
            weight = ((radius-curr.getDistance())/(radius*1.0));
            myClasses.get(curr.getdClass()-1).setSumWeight(weight);
            myClasses.get(curr.getdClass()-1).setNumObj(1);

            ++i;

        }while (i<dotArr.size()&&curr.getDistance()<radius);
        choiseClassForDot();
        if (dotAdd)
            dotArr.add(currentDot);

    }
    static void choiseClassForDot(){
        myClasses.sort((o1, o2) ->(int)(o2.getSumWeight()-o1.getSumWeight()));
        currentDot.setdClass(myClasses.get(0).getdClass());
        System.out.println(Arrays.toString(myClasses.toArray()));
        myClasses.sort((o1, o2) ->(int)(o1.getdClass()-o2.getdClass()));


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
