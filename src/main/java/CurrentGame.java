import javax.swing.*;
import java.awt.event.*;


public class CurrentGame extends JFrame {

    private final Pacman pacman;
    private final Ghost[] ghosts;
    private final Grid initialGrid;
    private Grid currentGrid;
    //private int level = 0;
    private int initialSpeed = 160;
    //private int speedDelta = 10;
    private MainWindow mainWindow;
    private Timer timer;
    private int gameStatus = 0;

    public CurrentGame(Grid initialGrid, Pacman pacman, Ghost[] ghosts) {
        this.pacman = pacman;
        this.initialGrid = initialGrid;
        this.currentGrid = initialGrid;
        this.ghosts = ghosts;
        initGame();
    }

    public void initGame() {
        mainWindow = new MainWindow(initialGrid, pacman);
        mainWindow.setVisible(true);
        timer = new Timer(initialSpeed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("aaa");
                boolean gameOn = pacman.move(currentGrid);
                for (Ghost ghost : ghosts) {
                    gameOn &= ghost.move(currentGrid);
                }
                if (!gameOn) {
                    gameStatus = 2;
                    timer.stop();
                }
                mainWindow.repaint();
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
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
                    case KeyEvent.VK_ENTER: {
                        switch (gameStatus) {
                            case 0: {
                                gameStatus = 1;
                                timer.start();
                                break;
                            }
                            case 1: {
                                gameStatus = 0;
                                timer.stop();
                            }
                        }
                    }
                }
            }
        });
    }
}
