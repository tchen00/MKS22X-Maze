import java.util.*;
import java.io.*;
public class Maze{

    private char[][]maze;
    private boolean animate;//false by default

    /*Constructor loads a maze text file, and sets animate to false by default.

      1. The file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - Walls - locations that cannot be moved onto
      ' ' - Empty Space - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)

      'S' - the location of the start(exactly 1 per file)

      2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!


      3. When the file is not found OR the file is invalid (not exactly 1 E and 1 S) then:
         throw a FileNotFoundException or IllegalStateException

    */

    public Maze(String filename) throws FileNotFoundException{
        // default for animate is false
        animate = false;
        // reading the data (two scanners bc two loops)
        File data = new File(filename);
        Scanner scan = new Scanner(data);
        Scanner filler = new Scanner(data);
        int row = 0;
        int col = 0;
        int count = 0;
        //count number of rows and col to set up maze array
        while (scan.hasNextLine()){
          row++;
          String line = scan.nextLine();
          col = line.length();
        }
        // for debug System.out.println(col);
        maze = new char[row][col];
        // fill the array w/ correct symbols from the .dat files
        while (filler.hasNextLine()){
          String line = filler.nextLine();
          for (int i = 0; i < line.length(); i++){
            maze[count][i] = line.charAt(i);
          } count++;
        }
    }

    //code to wait from Mr. K
    private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }

    /*Return the string that represents the maze.
     It should look like the text file with some characters replaced.
    */
    public String toString(){
      //toString for the array
      String output = "";
      for (int i = 0; i < maze.length; i++){
        for (int j = 0; j < maze[i].length; j++){
          output += maze[i][j];
          if (j == maze[i].length - 1) output += "\n"; // skip line
        }
      }
      return output;
    }

    public void setAnimate(boolean b){
        animate = b;
    }


    public void clearTerminal(){
        //erase terminal, go to top left of screen.
        System.out.println("\033[2J\033[1;1H");
    }



    /*Wrapper Solve Function returns the helper function

      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.

    */
    public int solve(){
      //int[] = findStart();
      //finding the S
      int[] coor = new int[2];
      boolean found = false;
      for (int i = 0; i < maze.length && !found; i++){
        for (int j = 0; j < maze[i].length && !found; j++){
          if (maze[i][j] == 'S'){
            coor[0] = i;
            coor[1] = j;
            found = true;
          }
        }
      }
      // after found S replace it with @
      maze[coor[0]][coor[1]] = ' ';
      // recursive helper
      return solve(coor[0], coor[1]);
    }

    /*
      Recursive Solve function:

      A solved maze has a path marked with '@' from S to E.

      Returns the number of @ symbols from S to E when the maze is solved,
      Returns -1 when the maze has no solution.

      Postcondition:
        The S is replaced with '@' but the 'E' is not.
        All visited spots that were not part of the solution are changed to '.'
        All visited spots that are part of the solution are changed to '@'
    */
    private int solve(int row, int col){ //you can add more parameters since this is private
        //automatic animation! You are welcome. (haha from Mr.K)
        if(animate){
            clearTerminal();
            System.out.println(this);
            //System.out.println("the count is " + count); debug
            wait(20);
        }

        int[][] directions = {{0,1},{0,-1},{-1,0},{1,0}}; //up, down, left, right

        int temp = 0;
        if (maze[row][col] == 'E') return temp; //if exit found
        if (maze[row][col] != ' ') return -1;

        maze[row][col] = '@'; //mark this spot as traveled to
        int output = 0;
        for (int i = 0; i < directions.length; i++){
          output = solve(row + directions[i][0], col + directions[i][1]); //try move
          if (output != -1) {
            return output + 1;
          } else if (maze[row + directions[i][0]][col + directions[i][1]] == 'E') {
            return 1; // if end found
            }
        }
        maze[row][col] = '.'; //if everything fails
        return -1; //so it compiles properly
    }

    // private method that checks to see if the particular direction is empty (space)
    private boolean checkDirection(int row, int col, int verticalShift, int horizontalShift){
      try {
        if (maze[row+verticalShift][col+horizontalShift] == ' ') return true;
      }
      catch (IndexOutOfBoundsException e){
      }
      return false;

    }
}
