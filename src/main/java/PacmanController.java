import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PacmanController extends KeyAdapter {

    private final Pacman pacman;

    public PacmanController(Pacman pacman) {
        this.pacman = pacman;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        System.out.println(key);

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT: {
                pacman.direction = Direction.LEFT;
                pacman.setIcon(Icons.PACMAN_LEFT.icon);
                break;
            }
            case KeyEvent.VK_RIGHT: {
                pacman.direction = Direction.RIGHT;
                pacman.setIcon(Icons.PACMAN_RIGHT.icon);
                break;
            }
            case KeyEvent.VK_UP: {
                pacman.direction = Direction.UP;
                pacman.setIcon(Icons.PACMAN_UP.icon);
                break;
            }
            case KeyEvent.VK_DOWN: {
                pacman.direction = Direction.DOWN;
                pacman.setIcon(Icons.PACMAN_DOWN.icon);
                break;
            }
        }
    }
}
