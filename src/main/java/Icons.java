import javax.swing.*;
import java.awt.*;

public enum Icons {
    PACMAN_UP("src/main/resources/up.gif"),
    PACMAN_DOWN("src/main/resources/down.gif"),
    PACMAN_RIGHT("src/main/resources/right.gif"),
    PACMAN_LEFT("src/main/resources/left.gif"),
    GHOST("src/main/resources/ghost.gif");

    public final Image icon;

    Icons(String pathToFile) {
        icon = new ImageIcon(pathToFile).getImage();
    }
}
