package pl.project.ca;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;

/**
 * Board with Points that may be expanded (with automatic change of cell
 * number) with mouse event listener
 */
public class Board extends JComponent implements MouseInputListener, ComponentListener {
  private static final long serialVersionUID = 1L;
  private Point[][] points;
  private int size = 20;

  public Board(int length, int height) {
    addMouseListener(this);
    addComponentListener(this);
    addMouseMotionListener(this);
    setBackground(Color.WHITE);
    
  }

  // single iteration
  public void iteration() {
    for (Point[] point : points) {
      for (Point aPoint : point) {
        aPoint.calculateNewState();
      }
    }

    for (Point[] point : points) {
      for (Point aPoint : point) {
        aPoint.changeState();
      }
    }
    this.repaint();
  }

  // clearing board
  public void clear() {
    for (Point[] point : points) {
      for (Point aPoint : point) {
        aPoint.setState(0);
      }
    }
    this.repaint();
  }

  private void initialize(int length, int height) {
    points = new Point[length][height];

    for (int x = 0; x < points.length; ++x) {
      for (int y = 0; y < points[x].length; ++y) {
        points[x][y] = new Point();
      }
    }

    for (int x = 0; x < points.length; ++x) {
      for (int y = 0; y < points[x].length; ++y) {
        //initialize the neighborhood of points[x][y] cell
        tryToAdd(points[x][y],x-1,y+1); tryToAdd(points[x][y],x, y+1); tryToAdd(points[x][y],x+1,y+1);
        tryToAdd(points[x][y],x-1,y); /*                            */ tryToAdd(points[x][y], x+1, y);
        tryToAdd(points[x][y],x-1,y-1); tryToAdd(points[x][y],x, y-1); tryToAdd(points[x][y], x+1, y-1);
      }
    }
  }

  private void tryToAdd(Point addTo, int x, int y){
    try{
    addTo.addNeighbor(points[x][y]);
    }catch(ArrayIndexOutOfBoundsException ignore){
      //ignore
    }
  }

  
  //paint background and separators between cells
  protected void paintComponent(Graphics g) {
    if (isOpaque()) {
      g.setColor(getBackground());
      g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
    g.setColor(Color.RED);
    drawNetting(g, size);
  }

  // draws the background netting
  private void drawNetting(Graphics g, int gridSpace) {
    Insets insets = getInsets();
    int firstX = insets.left;
    int lastX = this.getWidth() - insets.right;
    int firstY = insets.top;
    int lastY = this.getHeight() - insets.bottom;

   
    for(int y = firstY;y < lastY;y += gridSpace) {
    	 g.drawLine(firstX, y, lastX, y);
    }
    
    int x,y;
  
    for ( x = 0; x < points.length; ++x) {
      for ( y = 0; y < points[x].length; ++y) {
        if (points[x][y].getState() != 0) {
      
          switch (points[x][y].getState()) {
            case 0:
              g.setColor(new Color(0x0000ff));
            case 1:
              g.setColor(new Color(0x0000));
              break;
         
          }
          g.fillRect((x * size) + 1, (y * size) + 1, (size - 1), (size - 1));
        }
      }
    }

  }

  public void mouseClicked(MouseEvent e) {
    int x = e.getX() / size;
    int y = e.getY() / size;
    if ((x < points.length) && (x > 0) && (y < points[x].length) && (y > 0)) {
      points[x][y].clicked();
      this.repaint();
    }
  }

  public void componentResized(ComponentEvent e) {
    int dlugosc = (this.getWidth() / size) + 1;
    int wysokosc = (this.getHeight() / size) + 1;
    initialize(dlugosc, wysokosc);
  }

  public void mouseDragged(MouseEvent e) {
    int x = e.getX() / size;
    int y = e.getY() / size;
    if ((x < points.length) && (x > 0) && (y < points[x].length) && (y > 0)) {
      points[x][y].setState(1);
      this.repaint();
    }
  }

  public void mouseExited(MouseEvent e) {
  }

  public void mouseEntered(MouseEvent e) {
  }

  public void componentShown(ComponentEvent e) {
  }

  public void componentMoved(ComponentEvent e) {
  }

  public void mouseReleased(MouseEvent e) {
  }

  public void mouseMoved(MouseEvent e) {
  }

  public void componentHidden(ComponentEvent e) {
  }

  public void mousePressed(MouseEvent e) {
  }

}
