package aihw.utils;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Simple drawing program.
 * 
 * @author W. Patrick Hooper whooper@ccny.cuny.edu
 * @author Jarred Durant jarreddurant14@gmail.com
 */
public class Drawing extends JPanel implements MouseListener, MouseMotionListener {

  GeneralPath p = new GeneralPath();

  LinkedList<GeneralPath> old_paths = new LinkedList<GeneralPath>();

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
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g.setColor(Color.BLACK);
    g.setStroke(new BasicStroke(2));
    for (GeneralPath old_path : old_paths)
      g.draw(old_path);
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
    p = new GeneralPath();
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

    done.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
      }
    });
  }

  /*
   * Because of Polymorphism, this is already a jpanel. 
   * JPanels have the ability to be converted into 
   * BufferedImage, so we can easily change it to a jpg.
   *
   * TESTING REQUIRED - THIS METHOD HAS NOT BEEN TESTED
   */
  public BufferedImage getImage() {
    int w = this.getWidth();
    int h = this.getHeight();
    BufferedImage toReturn = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = toReturn.createGraphics();
    printAll(g);
    g.dispose();
    return toReturn;
  }
    
  /*
   * This is exactly like the getImage method, but it
   * converts the image to a file.
   *
   * TESTING REQUIRED - THIS METHOD HAS NOT BEEN TESTED
   */
  public void imageToFile(String filepath) throws IOException{
    BufferedImage img = getImage();
    ImageIO.write(image, "png", new File(filepath));
  }

}
