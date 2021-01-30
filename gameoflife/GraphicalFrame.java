package gameoflife;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GraphicalFrame extends JFrame {
    int currentsize;
    String currentname;
    GameOfLife gl = new GameOfLife();
    Colony cle;
    Graphics gre;
    public GraphicalFrame(Colony cl) {
        super();        
        addWindowListener(new WindowAdapter() {           
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
            }
        });
        currentsize = cl.currentcolonysize;
        currentname = cl.currentcolonyname;
        cle = cl;
        setTitle(currentname);
        setSize(500, 500);
        setVisible(true);
            }
    public void paint(Graphics gr) {
        
        int width = (int)this.getContentPane().getSize().getWidth()-230;
        int height = (int)this.getContentPane().getSize().getHeight()-200;
        int cellwidth = (int)width/currentsize;
        int cellheight = (int)height/currentsize;
        Graphics2D g = (Graphics2D)gr;
        Font oldFont = getFont();
        Font newFont = new Font(oldFont.getName(), oldFont.getStyle(), 80);
        g.setFont(newFont);
         g.setStroke(new BasicStroke(3));
        for(int r= 0;r< currentsize;r++) {
        for(int c = 0;c<currentsize;c++) {
                if(cle.isCellAlive(r, c)) {
                  g.setColor(Color.BLACK);
                   g.fillRect(50 + (cellheight * (c + 1)), 50 + 30 + (cellwidth * (r + 1)), cellheight, cellwidth);
                } else {
                    g.setColor(Color.WHITE);
                    g.fillRect(50 + (cellheight * (c + 1)), 50 + 30 + (cellwidth * (r + 1)), cellheight, cellwidth);
                }
            }
        }
       repaint();
    }
}

