package pl.project.ca;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class containing GUI: board + buttons
 */
public class GUI extends JPanel implements ActionListener, ChangeListener {
  private static final long serialVersionUID = 1L;
  private Timer     count;
  private Board     board;
  private JButton   start;
  private JButton   clear;
  
  private JFrame    frame;
 
  private final int     initDelay = 100;
  private       boolean running   = false;

  public GUI(JFrame jf) {
    frame = jf;
    count = new Timer(initDelay, this);
    count.stop();
  }

  /**
   * @param container to which GUI and board is added
   */
  public void initialize(Container container) {
    container.setLayout(new BorderLayout());
    container.setSize(new Dimension(1024, 768));

    JPanel buttonPanel = new JPanel();

    start = new JButton("Start");
    start.setActionCommand("Start");
    start.addActionListener(this);

    clear = new JButton("Clear");
    clear.setActionCommand("clear");
  
    clear.addActionListener(this);

    buttonPanel.add(start);
    buttonPanel.add(clear);
  
    board = new Board(1024, 768 - buttonPanel.getHeight());
    container.add(board, BorderLayout.CENTER);
    container.add(buttonPanel, BorderLayout.SOUTH);
  }

  /**
   * handles clicking on each button
   *
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  public void actionPerformed(ActionEvent e) {
   if (e.getSource().equals(count)) {
     
      frame.setTitle("Game of Life");
     board.iteration();
   } else {
      String command = e.getActionCommand();
      if (command.equals("Start")) {
        if (!running) {
          count.start();
          start.setText("Pause");
        } else {
          count.stop();
          start.setText("Start");
        }
        running = !running;
        clear.setEnabled(true);

      } else if (command.equals("clear")) {
        
        count.stop();
        start.setEnabled(true);
        board.clear();
        frame.setTitle("Cellular Automata Toolbox");
      } 

    }
  }

@Override
public void stateChanged(ChangeEvent e) {
	// TODO Auto-generated method stub
	
}
}



