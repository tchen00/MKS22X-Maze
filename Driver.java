import java.util.*;
import java.io.*;

public class Driver{
    public static void main(String[]args){
      String filename = "data3.dat";
      try{
        Maze f;
        f = new Maze(filename);//true animates the maze.
        //System.out.println(f.toString());
        f.setAnimate(true);
        //f.solve();
        System.out.print(f.solve());
        //System.out.println(f);
        //System.out.println(f.findStart()[1]);
      }catch(FileNotFoundException e){
        System.out.println("Invalid filename: "+filename);
      }
    }
}
