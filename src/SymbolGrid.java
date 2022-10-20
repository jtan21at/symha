import java.util.ArrayList;
import java.util.List;

public class SymbolGrid {
    private static char[] SYMBOLS = { '~', '!', '@', '#', '$', '^', '&', '*' };

    // NOTE:
    // Do not change this method signature.
    // This is method calls another recursive method, but it should
    // not call itself.
    public static void findAllPaths(char[][] grid, char[] sequence) {
        // TO DO:
        // Add code to traverse the grid and search for paths
        // starting at each cell using the findPathsAt() method.

        List<Cell> path = new ArrayList<Cell>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                findPathsAt(grid, new Cell(i, j), path, sequence, 0);
            }
        }

        // print the path
        for (Cell c : path) { // loop through the path
            System.out.println(c); // print the path
        }

        System.out.println("\n--- finished searching"); // print the finished searching
    }

    // TO DO:
    // Implement recursive method with backtracking
    //
    // NOTE: You may change the list of parameters here
    private static void findPathsAt(char[][] grid, Cell cell, List<Cell> path,
                                    char[] sequence, int index) {




        if (cell.r < 0 || cell.r >= grid.length || cell.c < 0
                || cell.c >= grid[0].length) {
            return;
        }

        if (grid[cell.r][cell.c] != sequence[index]) {
            return;
        }

        if (path.contains(cell)) {
            return;
        }

        path.add(cell);
        // Call findPathsAt()
         if (path.size() >= sequence.length) {
             for (Cell p_e:path
                  ) {System.out.print(sequence[path.indexOf(p_e)]+p_e.toString()+' ');

             } System.out.println();
//             System.out.println(path);
                     path.remove(path.size()-1);
                    return;
                }
        findPathsAt(grid, new Cell(cell.r - 1, cell.c), path, sequence, index + 1);
        findPathsAt(grid, new Cell(cell.r + 1, cell.c), path, sequence, index + 1);
        findPathsAt(grid, new Cell(cell.r, cell.c - 1), path, sequence, index + 1);
        findPathsAt(grid, new Cell(cell.r, cell.c + 1), path, sequence, index + 1);

        findPathsAt(grid, new Cell(cell.r - 1, cell.c-1), path, sequence, index + 1);
        findPathsAt(grid, new Cell(cell.r + 1, cell.c+1), path, sequence, index + 1);
        findPathsAt(grid, new Cell(cell.r+1, cell.c - 1), path, sequence, index + 1);
        findPathsAt(grid, new Cell(cell.r-1, cell.c + 1), path, sequence, index + 1);
        path.remove(path.size()-1); // backtrack

    }

    public static void main(String[] args) {
        char[][] grid = randomSymbolGrid(7);
        displaySymbolGrid(grid);
        System.out.println();
        char[] seq = randomSymbolSequence(4);
        System.out.print("sequence: ");
        System.out.println(seq);
        System.out.println("\npaths:");
        findAllPaths(grid, seq);
    }

    private static char[] randomSymbolSequence(int length) {
        char[] sequence = new char[length];
        for (int i = 0; i < length; i++) {
            sequence[i] = SYMBOLS[(int) (Math.random() * SYMBOLS.length)];
        }
        return sequence;
    }

    private static char[][] randomSymbolGrid(int size) {
        char[][] grid = new char[size][size];
        for (int r = 0; r < size; r++) {
            grid[r] = randomSymbolSequence(size);
        }
        return grid;
    }

    private static void displaySymbolGrid(char[][] grid) {
        // Display
        System.out.print("\n   ");
        for (int i = 0; i < grid.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int r = 0; r < grid.length; r++) {
            // Display row index
            System.out.print(" " + r + " ");
            for (int c = 0; c < grid[r].length; c++) {
                System.out.print(grid[r][c] + " ");
            }
            System.out.println();
        }
    }
}


class Cell {
    int r, c;

    Cell(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public boolean equals(Object o) {
        Cell cell = (Cell) o;
        return this.r == cell.r && this.c == cell.c;
    }

    @Override
    public String toString() {
        return "(" + r + ", " + c + ")";
    }
}
