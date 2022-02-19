import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.Random;

public class Application {

    public static void main(String[] args) throws IOException {

        String pathToGrid = "src/main/resources/grid3.txt";
        Image pacmanIcon = Icons.PACMAN_RIGHT.icon;
        Image ghostIcon = Icons.GHOST.icon;

        Grid grid = new Grid(pathToGrid);

        List<Integer> coors = grid.getPacmanPosition();
        Pacman pacman = new Pacman(coors.get(0), coors.get(1), Direction.RIGHT, pacmanIcon);

        ArrayList<Ghost> ghosts = new ArrayList<>();

        for (List<Integer> ghostPosition : grid.getGhostsPositions()) {
            ghosts.add(new Ghost(ghostPosition.get(0), ghostPosition.get(1), getRandomDirection(), ghostIcon));
        }

        EventQueue.invokeLater(() -> {
            CurrentGame currentGame = new CurrentGame(grid, pacman, ghosts.toArray(new Ghost[0]));
            currentGame.setVisible(true);
        });

    }

    private static Direction getRandomDirection() {
        return Direction.values()[new Random().nextInt(Direction.values().length)];
    }
}
