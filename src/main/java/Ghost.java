import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Ghost extends Actor {

    private final List<GridElement> goodElements = Arrays.asList(GridElement.FREE, GridElement.POINT, GridElement.PACMAN);

    public Ghost(int x, int y, Direction direction, Image icon) {
        super(x, y, direction, icon);
    }

    @Override
    public boolean move(Grid grid) {
        int newX = x + direction.getDi();
        int newY = y + direction.getDj();
        if (!goodElements.contains(grid.getGridElement(newX, newY))) {
            List<Direction> possibleDirections = getPossibleDirections(grid);
            if (possibleDirections.isEmpty())
                return true;
            Random rand = new Random();
            direction = possibleDirections.get(rand.nextInt(possibleDirections.size()));
        }
        if (grid.getGridElement(x, y) == GridElement.GHOST1)
            grid.setGridElement(x, y, GridElement.FREE);
        else
            grid.setGridElement(x, y, GridElement.POINT);
        x += direction.getDi();
        y += direction.getDj();
        switch (grid.getGridElement(x, y)) {
            case PACMAN: {
                grid.setGridElement(x, y, GridElement.GHOST1);
                return false;
            }
            case FREE: {
                grid.setGridElement(x, y, GridElement.GHOST1);
                break;
            }
            case POINT: {
                grid.setGridElement(x, y, GridElement.GHOST2);
            }
        }
        return true;
    }

    private List<Direction> getPossibleDirections(Grid grid) {
        ArrayList<Direction> possibleDirections = new ArrayList<>();

        for (Direction dir : Direction.values()) {
            int newX = x + dir.getDi();
            int newY = y + dir.getDj();
            if (goodElements.contains(grid.getGridElement(newX, newY))) {
                possibleDirections.add(dir);
            }
        }
        return possibleDirections;
    }
}
