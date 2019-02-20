package dendiem.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import static dendiem.com.FloFrame.*;


public class DrawPanel extends JPanel {
    private int height;
    private int width;
    private Graphics graphics;
    final static private int DOT_DIAMETR = 10;
    public DrawPanel() {
        setBackground(Color.WHITE);



    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        graphics = g;
        height = getSize().height;
        width = getSize().width;

        graphics.drawLine(20,0,20,height);
        graphics.drawLine(0,20,width,20);
        graphics.drawString("0",10,10);

        graphics.drawLine(15,height-20,25,height-20);
        graphics.drawLine(width-20,15,width-20,25);
        graphics.drawString(height-20+"",1,height-25);
        graphics.drawString(width-20+"",width-40,10);
        Dot dot;
        int dx,dy;
        for (int i=0;i<dotArr.size();++i){
            dot = dotArr.get(i);
            dx=dot.getX();
            dy=dot.getY();

            if(i==dotArr.size()-1){

                dot = currentDot;
                dx=dot.getX();
                dy=dot.getY();
                addDot(myClasses.get(dot.getdClass()-1).getColor(),dx,dy);
                if(grid){
                    drawFocus(dx,dy);
                }
            }else{
                addDot(myClasses.get(dot.getdClass()-1).getColor(),dx,dy);
            }

        }
        if (FloFrame.grid){
            drawGrid();
        }
    }
    private void drawFocus(int x,int y){
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setStroke(new BasicStroke((float)1.5));
        g2.drawLine(x+DOT_DIAMETR,y+DOT_DIAMETR,x+DOT_DIAMETR*2,y+DOT_DIAMETR*2);
        g2.drawLine(x+DOT_DIAMETR,y-DOT_DIAMETR,x+DOT_DIAMETR*2,y-DOT_DIAMETR*2);
        g2.drawLine(x-DOT_DIAMETR,y+DOT_DIAMETR,x-DOT_DIAMETR*2,y+DOT_DIAMETR*2);
        g2.drawLine(x-DOT_DIAMETR,y-DOT_DIAMETR,x-DOT_DIAMETR*2,y-DOT_DIAMETR*2);
        g2.drawOval(x-radius,y-radius,radius*2,radius*2);
    }

     private void addDot(Color dclass,int x, int y){

          graphics.setColor(dclass);
          Graphics2D g2d = (Graphics2D)graphics;

          // Assume x, y, and diameter are instance variables.
          Ellipse2D.Double circle = new Ellipse2D.Double(x-DOT_DIAMETR/2,y-DOT_DIAMETR/2,DOT_DIAMETR,DOT_DIAMETR);
          g2d.fill(circle);
          //graphics.drawOval(x-DOT_DIAMETR/2,y-DOT_DIAMETR/2,DOT_DIAMETR,DOT_DIAMETR);
      }
      private void drawGrid(){
          Graphics2D g2 = (Graphics2D) graphics;
          g2.setStroke(new BasicStroke(2));
          g2.setColor(Color.BLACK);

        for(int x=100;x<=550;x+=150){
            g2.draw(new Line2D.Float(x, 100, x, 550));
        }
          for(int y=100;y<=550;y+=150){
              g2.draw(new Line2D.Float(100, y, 550, y));
          }

          int i = 1;
          g2.setFont(new Font("TimesRoman", Font.BOLD, 25));
          for(int y=100;y<550;y+=150){
              for(int x=100;x<550;x+=150){
                  g2.drawString(i+"",x+65,y+80);
                  ++i;
              }

          }


      }

}
