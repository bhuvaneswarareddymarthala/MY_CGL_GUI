package pl.project.ca;

import javax.swing.*;

public class Program extends JFrame {

  private static final long serialVersionUID = 1L;

  private GUI gof;

  public Program() {
    setTitle("Game of Life");
   
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   
    setSize(500, 500);
    setVisible(true);
  }

  public static void main(String[] args) {
    new Program();
  }

}
