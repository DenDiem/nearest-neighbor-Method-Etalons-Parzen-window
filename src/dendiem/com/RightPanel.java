package dendiem.com;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Arrays;

import static dendiem.com.FloFrame.*;

public class RightPanel extends JPanel {
    private Graphics graphics;
    private Dot copy;
    final static private int DOT_DIAMETR = 10;
    public RightPanel() {
        setBackground(Color.WHITE);


        JLabel jl = new JLabel("<--------    [OutPut]    -------->");
        add(jl);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        copy = new Dot(currentDot.getX(),currentDot.getY());
        copy.choiceClass();
        graphics = g;
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setStroke(new BasicStroke(4));
        g.drawLine(1,1,1,getSize().height);

        g.drawString("Class new object: "+currentDot.getdClass(),5,50);
        g.drawString("Real class: "+copy.getdClass(),5,70);
        g.drawString("K of elements: "+numofel,5,90);
        g.drawString("distance/Parsen window: "+radius,5,110);
        MyClass mc;
        int y;


            myClasses.sort((o1, o2) ->(int)((o2.getSumWeight()*10000)-(o1.getSumWeight()*10000)));



        for(int i=0;i<9;++i){
            y = i*70+140;
            mc = myClasses.get(i);
            addDot(mc.getColor(),15,y);
            g.drawString(mc.getdClass()+"",15,y+15);
            g.drawString("num: " + mc.getNumObj(),40,y);
            g.drawString("  weight: " + mc.getSumWeight(),80,y);
        }
        myClasses.sort((o1, o2) ->(int)(o1.getdClass()-o2.getdClass()));


    }
    private void addDot(Color dclass,int x, int y){

        graphics.setColor(dclass);
        Graphics2D g2= (Graphics2D) (graphics);

        Ellipse2D.Double circle = new Ellipse2D.Double(x-DOT_DIAMETR/2,y-DOT_DIAMETR/2,DOT_DIAMETR,DOT_DIAMETR);
        g2.fill(circle);

    }
}
