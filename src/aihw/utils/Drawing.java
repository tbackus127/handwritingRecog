
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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Simple drawing program.
 * 
 * @author W. Patrick Hooper whooper@ccny.cuny.edu
 * @author Tim Backus tbackus127@gmail.com
 * @author Jarred Durant jarreddurant14@gmail.com
 * @author Tyler Fiacco Tyler_Fiacco@yahoo.com
 * @author Eric Sakshaug Eric.Sakshaug11@gmail.com
 */
public class Drawing extends JPanel implements MouseListener, MouseMotionListener {

  /** The GeneralPath object. */
  private GeneralPath p = new GeneralPath();

  /** A list of old paths. */
  private LinkedList<GeneralPath> old_paths = new LinkedList<GeneralPath>();

  /** Default serial version. */
  private static final long serialVersionUID = 1L;

  /**
   * Default constructor.
   */
  public Drawing() {

    // Make the background color white
    setBackground(Color.WHITE);

    // Necessary for mouse interaction:
    addMouseListener(this);
    addMouseMotionListener(this);
  }

  /**
   * Paints the panel.
   * 
   * @param gfx the Graphics object.
   */
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

  /**
   * Mouse event - click.
   * 
   * @param me the MouseEvent generated.
   */
  @Override
  public void mouseClicked(MouseEvent me) {}

  /**
   * Mouse event - enter panel.
   * 
   * @param me the MouseEvent generated.
   */
  @Override
  public void mouseEntered(MouseEvent me) {}

  /**
   * Mouse event - exit panel.
   * 
   * @param me the MouseEvent generated.
   */
  @Override
  public void mouseExited(MouseEvent me) {}

  /**
   * Mouse event - button down
   * 
   * @param me the MouseEvent generated.
   */
  @Override
  public void mousePressed(MouseEvent me) {
    old_paths.add(p);
    p = new GeneralPath();
    p.moveTo(me.getX(), me.getY());
    repaint();
  }

  /**
   * Mouse event - mouse up.
   * 
   * @param me the MouseEvent generated.
   */
  @Override
  public void mouseReleased(MouseEvent me) {}

  // Implementation of MouseMotionListener
  /**
   * Mouse event - drag
   * 
   * @param me the MouseEvent generated.
   */
  @Override
  public void mouseDragged(MouseEvent me) {
    p.lineTo(me.getX(), me.getY());
    repaint();
  }

  /**
   * Mouse event - move
   * 
   * @param me the MouseEvent generated.
   */
  @Override
  public void mouseMoved(MouseEvent me) {}

  /**
   * Runs the drawing panel.
   */
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

  /**
   * Gets the image from this panel.Because of Polymorphism, this is already a jpanel. JPanels have the ability to be
   * converted into BufferedImage, so we can easily change it to a png.
   * 
   * TESTING REQUIRED - THIS METHOD HAS NOT BEEN TESTED
   * 
   * @return
   */
  public BufferedImage getImage() {
    int w = this.getWidth();
    int h = this.getHeight();
    BufferedImage toReturn = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = toReturn.createGraphics();
    this.printAll(g);
    g.dispose();
    return toReturn;
  }

  /**
   * Saves the image to a file. This is exactly like the getImage method, but it converts the image to a file.
   * 
   * TESTING REQUIRED - THIS METHOD HAS NOT BEEN TESTED
   * 
   * @param filepath the path to save the image to.
   * @throws IOException
   */
  public void imageToFile(String filepath) throws IOException {
    BufferedImage img = getImage();
    ImageIO.write(img, "png", new File(filepath));
  }

}
