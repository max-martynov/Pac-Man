import javax.swing.*;
import java.awt.*;

public abstract class Actor {

    protected int x, y;
    protected Direction direction;
    private Image icon;

    public Actor(int x, int y, Direction direction, Image icon) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.icon = icon;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image newIcon) { icon = newIcon; }

    public abstract boolean move(Grid grid);
}
