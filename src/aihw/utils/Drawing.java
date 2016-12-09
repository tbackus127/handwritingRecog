package src.aihw.utils;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Simple drawing program.
 * 
 * @author W. Patrick Hooper whooper@ccny.cuny.edu
 * @author Jarred Durant jarreddurant14@gmail.com
 */
public class Drawing extends JPanel implements MouseListener, MouseMotionListener {
    
    GeneralPath p=new GeneralPath();

    LinkedList<GeneralPath> old_paths= new LinkedList<GeneralPath>();
    public Drawing() {
        setBackground(Color.WHITE); // Make the background color white

        // Necessary for mouse interaction:
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics gfx) {
        // This stuff is standard, and should be in any paintComponent method. 
        super.paintComponent(gfx);
        Graphics2D g = (Graphics2D) gfx;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));        
        for (GeneralPath old_path : old_paths ) g.draw(old_path);
        g.setStroke(new BasicStroke(2));
        g.draw(p);        
    }

    ///// MOUSE INTERACTION
    // Implementation of MouseListener
    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
    @Override
    public void mousePressed(MouseEvent me) {
        old_paths.add(p);
        p=new GeneralPath();
        p.moveTo(me.getX(), me.getY());
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    // Implementation of MouseMotionListener
    @Override
    public void mouseDragged(MouseEvent me) {
        p.lineTo(me.getX(), me.getY());
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent me) {        
    }

    public void run() {
        // Construct a new window:
        JFrame frame = new JFrame("Draw a letter!");
        JPanel p = new JPanel(new BorderLayout());
        frame.add(p);
        // Dimensions of the window in pixels:
        frame.setSize(701, 626);
        // Quit the program when the window is closed:
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // The window will contain only our panel:
        p.add(new Drawing(), BorderLayout.CENTER);
	JButton done = new JButton("Done drawing");
	done.setSize(new Dimension(100, 701));
	p.add(done, BorderLayout.SOUTH);
        // Make the window visible:
        frame.setVisible(true);
    }
}
