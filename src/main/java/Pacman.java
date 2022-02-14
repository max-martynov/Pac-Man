import java.awt.*;

public class Pacman extends Actor {

    private int score;

    public Pacman(int x, int y, Direction direction, Image icon) {
        super(x, y, direction, icon);
    }

    public void changeDirection(Direction newDirection) {
        direction = newDirection;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean move(Grid grid) {
        int newX = x + direction.getDi();
        int newY = y + direction.getDj();

        switch (grid.getGridElement(newX, newY)) {
            case GHOST1:
            case GHOST2: {
                grid.setGridElement(x, y, GridElement.FREE);
                return false;
            }
            case POINT:
                score++;
            case FREE: {
                grid.setGridElement(x, y, GridElement.FREE);
                x = newX;
                y = newY;
                grid.setGridElement(newX, newY, GridElement.PACMAN);
            }
        }
        return true;
    }
}
