import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grid {

    private final int n, m;                       //    |
    private final GridElement[][] grid;           //  n |
                                                  //    |__________
    public Grid(GridElement[][] grid) {           //         m
        this.grid = grid;
        n = grid.length;
        m = grid[0].length;
    }

    public Grid(String pathToFile) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(pathToFile));
        n = lines.size();
        m = lines.get(0).length();
        grid = new GridElement[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //System.out.println(lines.get(i).charAt(j));
                switch (lines.get(i).charAt(j)) {
                    case '.': {
                        grid[i][j] = GridElement.POINT;
                        break;
                    }
                    case '#': {
                        grid[i][j] = GridElement.BLOCKED;
                        break;
                    }
                    case 'P': {
                        grid[i][j] = GridElement.PACMAN;
                        break;
                    }
                    case 'G': {
                        grid[i][j] = GridElement.GHOST2;
                    }
                }
            }
        }
    }

    public List<Integer> getPacmanPosition() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == GridElement.PACMAN)
                    return Arrays.asList(i, j);
            }
        }
        return null;
    }

    public List<List<Integer>> getGhostsPositions() {
        List<List<Integer>> ghostsPositions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == GridElement.GHOST1 || grid[i][j] == GridElement.GHOST2)
                    ghostsPositions.add(Arrays.asList(i, j));
            }
        }
        return ghostsPositions;
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public GridElement getGridElement(int x, int y) {
        return grid[x][y];
    }

    public void setGridElement(int x, int y, GridElement ge) {
        grid[x][y] = ge;
    }
}
