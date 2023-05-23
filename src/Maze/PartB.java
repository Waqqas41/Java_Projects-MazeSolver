package Maze;

import BasicIO.*;

/** This class recursively solves a maze
 */
public class PartB {

    char[][] maze; //maze array
    int x; //rand coords for S
    int y;
    int a; // rand coords for E
    int b;
    int r;
    int c;

    /** This constructor builds a maze from a .txt file, recursively solves it,
     * then outputs it to an ASCIIdisplayer and ASCIIOutputFile
     */
    public PartB() {
        buildMaze();
        ASCIIOutputFile output = new ASCIIOutputFile("out.txt");
        ASCIIDisplayer display = new ASCIIDisplayer(50, 100);
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                output.writeC(maze[i][j]);
                display.writeC(maze[i][j]);
            }
            output.newLine();
            display.newLine();
        }
        output.close();


        System.out.println("Starting location is: " + x + "," + y);
        System.out.println("Crush is at: " + a + "," + b);

    }//constructor

    /** this method builds a maze into a 2d array using a .txt file
     */
    private void buildMaze() { // this method builds a maze using a .txt file
        ASCIIDataFile data = new ASCIIDataFile("mz2.txt"); //maze .txt file
        int row, col;
        row = data.readInt(); //reads data from .txt file
        col = data.readInt();
        maze = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                maze[i][j] = data.readC();
            }
            data.nextLine();
        }


        //random E
        do {
            a = (int) (Math.random() * row);
             b = (int) (Math.random() * col);
        } while (maze[a][b] != ' ');
        maze[a][b] = 'E';

        //random S
        do {
            x = (int) (Math.random() * row);
            y = (int) (Math.random() * col);
        } while (maze[x][y] != ' ');
        findPath(x, y);
        maze[x][y] = 'S';



    }//buildMaze

    /**
     * this method solves the maze recursively by looking for 'E' from 'S', both defined by random x,y coordinates
     * in the maze
     * @param r
     * @param c
     * @return
     */
    private boolean findPath(int r, int c) {
        if (maze[r][c] == '#' || maze[r][c] == '.') { // letter runs into a wall
            return false;
        } else if (maze[r][c] == 'E') { // you find E
            return true;
        }
            maze[r][c] = '.'; // student hands the letter back to the student who handed them it
        if (findPath(r, c + 1)) {
            maze[r][c] =
                    '>';
            return true;
        }
        if (findPath(r, c - 1)) {
            maze[r][c] =
                    '<';
            return true;
        }
        if (findPath(r + 1, c)) {
            maze[r][c] =
                    'v';
            return true;
        }
        if (findPath(r - 1, c)) {
            maze[r][c] =
                    '^';
            return true;
        }
        return false;
    }//findPath

    public static void main(String[] args) {PartB c = new PartB();}


} //PartB