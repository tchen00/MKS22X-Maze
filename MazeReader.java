import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class MazeReader{
  public static void main(String args[]){
    try{
      File text = new File("Maze1.txt");
      Scanner info = new Scanner(text);
      while(info.hasNextLine()){
        String line = info.nextLine();
        System.out.println(line);
      }
    }
    catch(FileNotFoundException e){
      System.out.println("");
    }
  }
}
