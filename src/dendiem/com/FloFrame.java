package dendiem.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class FloFrame extends JFrame{
    static ArrayList<Dot> etalonArr = new ArrayList<>();
    static boolean dotAdd = true;

    static int mode = 0;
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
    private JButton TESTButton;
    private DrawPanel drawPanel;
    private RightPanel rightPanel;
    static boolean grid = true;

    public FloFrame() {

        setSize(900,800);
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

                if(mode == 0){
                    progNN(currentDot,k);
                }else if (mode == 1){
                    progParsen(currentDot,k);

                }else{
                    progEtalon(currentDot);
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
                if(mode == 0){
                    mode++;
                    bSwitch.setText("Switch to Etalons");
                    lEnteK.setText("R: ");
                    lProg.setText("Parsen Window");
                    eEnterK.enable();

                }else if (mode == 1){
                    mode++;
                    bSwitch.setText("Switch to NN");
                    lEnteK.setText("none: ");
                    eEnterK.disable();
                    lProg.setText("Etalons Method");
                    
                }else{
                    mode = 0;
                    eEnterK.enable();
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
                    if(mode == 0){
                        progNN(currentDot,k);
                    }else if (mode == 1){
                        progParsen(currentDot,k);

                    }else{
                        progEtalon(currentDot);
                    }
                    if(copy.getdClass()==currentDot.getdClass())
                        ++right;
                }


               /* boolean remember = dotAdd;
                dotAdd=false;
                int right2 = 0;
                for (in) {
                    x=myDot.getX();
                    y=myDot.getY();
                    copy = new Dot(x,y);
                    copy.choiceClass();

                    currentDot = new Dot(x,y);
                    if(NN){
                        progNN(currentDot,k);
                    }else{
                        progParsen(currentDot,k);

                    }
                    if(copy.getdClass()==currentDot.getdClass())
                        ++right2;
                }
                dotAdd=remember;*/
                drawPanel.repaint();
                rightPanel.repaint();
                System.out.println("Right: " + right);
                infoBox("Right: " + right+"/"+n/*+"\n Test on own, Right: " + right2+"/"+dotArr.size()*/,"[OutPut]");


            }
        });
        bAdds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dotAdd=!dotAdd;
            }
        });
        TESTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean rem = dotAdd;
                ArrayList<Dot> copydotArr = new ArrayList<>(dotArr);
                ArrayList<Dot> workarr = new ArrayList<>();
                dotAdd = false;
                int right = 0;
                int best = 1;
                Dot copy;
                int bestright = 0;
                int res = 0;
                Dot curr,wcurr;
                if(mode == 0){
                   for(int i = 1; i < dotArr.size();i++){

                       for (int j = 0; j < dotArr. size();++j  )
                       {

                           curr = dotArr.get(j);
                           dotArr.remove(j);
                           currentDot = new Dot(curr.getX(),curr.getY());
                           copy =  new Dot(curr.getX(),curr.getY());
                           copy.choiceClass();
                           progNN(currentDot,i);
                           if(copy.getdClass()==currentDot.getdClass())
                               ++right;


                           dotArr = new ArrayList<>(copydotArr);
                       }
                       if(right>bestright) {
                           best = i;
                           bestright = right;
                       }
                       right=0;
                   }
                    infoBox("TEST ON OWN, Right: " + bestright+"/"+dotArr.size()+"\n Best k: "+best,"[OutPut]");
                }else if (mode == 1){
                    for(int i = 1; i < 250;i+=1){
                        for (int j = 0; j < dotArr. size();++j  )
                        {
                            curr = dotArr.get(j);
                            dotArr.remove(j);
                            currentDot = new Dot(curr.getX(),curr.getY());
                            copy =  new Dot(curr.getX(),curr.getY());
                            copy.choiceClass();
                            progParsen(currentDot,i);
                            if(copy.getdClass()==currentDot.getdClass())
                                ++right;

                        }
                        dotArr = new ArrayList<>(copydotArr);
                        if(right>bestright) {
                            best = i;
                            bestright = right;
                        }
                        right=0;
                    }
                    infoBox("TEST ON OWN, Right: " + bestright+"/"+dotArr.size()+"\n Best radius: " +best ,"[OutPut]");
                }else{
                    for (Dot dot:dotArr
                    ) {
                        currentDot = new Dot(dot.getX(),dot.getY());
                        copy =  new Dot(dot.getX(),dot.getY());
                        copy.choiceClass();
                        progEtalon(currentDot);
                        if(copy.getdClass()==currentDot.getdClass())
                            ++right;

                    }
                   bestright = right;

                infoBox("TEST ON OWN, Right: " + bestright+"/"+dotArr.size() ,"[OutPut]");
                }

                dotAdd = rem;
            }
        });
    }
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    private void progEtalon(Dot currentDot){
        for (MyClass mc:myClasses
        ) {

            mc.normalize();
        }
        numofel=0;
        int j = 0;
        for (Dot curr:etalonArr
             ) {
            curr.setDistance(Dot.distance(curr,currentDot));
            myClasses.get(j).setJustWeight(1/curr.getDistance());
            ++j;
        }
        etalonArr.sort((o1, o2) ->(int) (o1.getDistance()-o2.getDistance()));

        currentDot.setdClass(etalonArr.get(0).getdClass());
        if (dotAdd)
            dotArr.add(currentDot);
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
        currentDot.setdClass(FloFrame.currentDot.getdClass());
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
        currentDot.setdClass(FloFrame.currentDot.getdClass());
        if (dotAdd)
            dotArr.add(currentDot);

    }
    static void choiseClassForDot(){
        myClasses.sort((o1, o2) ->(int)(o2.getSumWeight()*1000-o1.getSumWeight()*1000));
        currentDot.setdClass(myClasses.get(0).getdClass());
        System.out.println(Arrays.toString(myClasses.toArray()));
        myClasses.sort((o1, o2) ->(int)(o1.getdClass()-o2.getdClass()));


    }

}
