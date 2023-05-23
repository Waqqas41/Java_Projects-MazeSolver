package Maze;

import BasicIO.*;                // for IO classes


/**

 @author Waqqas41
 @version 1.0 (Nov 2022)
 */

public class PartA {

    public static String textFile = "hallway.txt";
    ASCIIDataFile data = new ASCIIDataFile(textFile);
    public char[][] mazearray;
    //rows and cols of maze array
    int row=1;
    int col=1;

// instance variables

    /**
     * This constructor ...
     */
    public PartA() {

        int x = data.readInt();
        int y = data.readInt();
        mazearray = new char[x][y];


        convertASCIItoArray();
        findPath();
        printArray();
        // local variables

        // statements

    }

    // constructor


// methods

    public void convertASCIItoArray() {

        for (int i = 0; i < mazearray.length; i++) {                //converts ASCII datafile to array
            for (int j = 0; j < mazearray[0].length; j++) {
                char x = data.readC();
                while (x < 32) {
                    x = data.readC();
                }
                mazearray[i][j] = x;

            }
        }

        //hardcode S and E into array/hallway

        if (mazearray[row][col] != 'S') {
            mazearray[row][col] = 'S';
        }


        /**comment this out to show backtracking when no E is found
         */
        if (mazearray[row][col + 8] != 'E') {
            mazearray[row][col + 8] = 'E';
        }

        //hardcode S and E

        if (mazearray[row][col + 8] == ' ') {
            System.out.println("no crush found in hallway.");
        }
    }


    public ASCIIOutputFile printArray() {
        ASCIIDisplayer dis = new ASCIIDisplayer(50, 75);
        ASCIIOutputFile out = new ASCIIOutputFile();


        for (int i = 0; i < mazearray.length; i++) {
            for (int j = 0; j < mazearray[0].length; j++) {
                char x = mazearray[i][j];
                dis.writeC(x);
                out.writeC(x);
            }
            dis.newLine();
            out.newLine();
        }

        return out;
    }


    /**
     * If you are the crush, then keep the letter.
     * If you are at a dead end, then return the letter to whoever gave it to you.
     * If you are not the crush, then pass the letter to the next person in the hallway and tell
     * them to read these instructions.
     * If the letter has been returned to you after you have already performed the last step, then
     * return the letter to whoever gave it to you.
     *
     * @param
     * @param
     */

    public int findPath() {

        if (mazearray[row][col+1] == 'E') {                 // Base case : E receives letter from S
            System.out.println("Found crush in hallway!");
            return 1;
        }   else {  //should prevent stackoverflow
            if (mazearray[row][col+1] != '#') {
                col++; //increment
                mazearray[row][col] = '>'; //marks student that's been handed a letter
                findPath(); //recurse
            }

            if (mazearray[row][col+1] == '#') {         // Base case : you ran into a wall '#'
                for (int i = 0; i < mazearray.length; i++) {
                    for (int j = 0; j < mazearray[0].length; j++) {
                        while (mazearray[row][col] == '>') {
                            mazearray[row][col] = '.'; //marks student that the letter has been handed back to
                            col--;
                        }
                    }
                }
            }
        }
        return -1;
    }

//    public static void main ( String[] args ) { PartA c = new PartA(); }
}

