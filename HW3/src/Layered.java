//Citation: https://docs.oracle.com/javase/tutorial/uiswing/components/layeredpane.html

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Layered implements MouseMotionListener {
    JFrame frame;
    JLayeredPane lpane;
    JLabel[] labels = {new JLabel(), new JLabel(), new JLabel()};
    Point diffDrag;
    
    public Layered() {
        initialize();
        frame.setVisible(true);
    }
    
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 650, 331);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lpane = new JLayeredPane();
        lpane.setPreferredSize(new Dimension(250,200));
        lpane.addMouseMotionListener(this);

        Color[] colors = {Color.gray, Color.red, Color.black};
        for (int i=0; i<colors.length; i++) {
            JLabel l = labels[i];
            l.setBounds(100+i*10, 50+i*5, 50, 20);
            l.setOpaque(true);
            l.setBackground(colors[i]);
            l.setForeground(Color.black);
            l.setBorder(BorderFactory.createLineBorder(Color.black));
            l.setText(String.format("label: %d", i));
            lpane.add(l, new Integer(i));
        }

        frame.add(lpane);
    }

    public void mouseDragged(MouseEvent e) {
        System.out.println("dragging");
        JLabel label = null;
        for (int i=0; i<labels.length; i++) {
            if (labels[i].getBounds().contains(e.getPoint())) {
                label = labels[i];
            }
        }
        if (label != null) {
            if (diffDrag == null)
                diffDrag = new Point(e.getX() - label.getBounds().x, e.getY() - label.getBounds().y);
            label.setBounds(e.getX() - diffDrag.x, e.getY()-diffDrag.y, label.getBounds().width, label.getBounds().height);
            System.out.printf("moved label to <%d, %d>", e.getX() - diffDrag.x, e.getY()-diffDrag.y);
        }
    }

    public void mouseMoved(MouseEvent e) {
        diffDrag = null;
    }
    
    public static void main(String[] args) {
        Layered m = new Layered();
    }
}