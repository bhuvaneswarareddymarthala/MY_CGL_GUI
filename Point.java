package pl.project.ca;

import java.util.ArrayList;

public class Point {
  private ArrayList<Point> neigh;
 
  private int              NS;// next state
  private int              CS;// currentstate
  private int numS = 2;
  
  private final int min_f = 2;
  private final int max_f = 3;

  private final int LIVE = 1;
  private final int DIE  = 0;

  

  public Point() {
    CS = 0;
    NS = 0;
    neigh = new ArrayList<Point>();
  }

  public void clicked() {
    CS = (++CS) % numS;
  }

  public int getState() {
    return CS;
  }

  public void setState(int s) {
    CS = s;
  }

  public void calculateNewState() {
    //logic which updates according to CS and number of active neigh
    if (!(getState() == DIE)) {
      NS = shouldThisDie() ? DIE : LIVE;
    } else {//(getState() == DIE){
      NS = shouldThisBeBorn() ? LIVE : DIE;
    }
  }

  public void changeState() {
    CS = NS;
  }

  public void addNeighbor(Point nei) {
    neigh.add(nei);
  }

  // method counting all active neigh of THIS point
  private boolean shouldThisDie() {
    int LIVEFriends = countLIVEFriends();

    boolean shouldDie = LIVEFriends > max_f || LIVEFriends < min_f;
   
    return shouldDie;
  }

  private boolean shouldThisBeBorn() {
    int LIVEFriends = countLIVEFriends();
    boolean shouldStart = LIVEFriends == max_f;
   
    return shouldStart;
  }

  private int countLIVEFriends() {
    int LIVEFriends = 0;

    System.out.print("friends: " + neigh.size() + "; ");
    for (Point neighbor : neigh) {
      if (neighbor.getState() == 1) {
        ++LIVEFriends;
      }
    }
    return LIVEFriends;
  }
}
