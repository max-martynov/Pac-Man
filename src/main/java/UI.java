import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.*;


class Surface extends JPanel implements ActionListener {

    private final int DELAY = 160;
    private final Timer timer = new Timer(DELAY, this);
    private final Pacman pacman;
    private final Grid grid;
    private final Ghost[] ghosts;
    private boolean gameOn= true;


    public Surface(Grid grid, Pacman pacman, Ghost[] ghosts) {
        this.grid = grid;
        this.pacman = pacman;
        this.ghosts = ghosts;
        timer.start();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        System.out.println(getWidth());
        System.out.println(getHeight());

        int cellWidth = getWidth() / grid.getM();
        int cellHeight = getHeight() / grid.getN();

        g2d.setPaint(Color.black);
        g2d.fillRect(0, 0, getWidth() - getWidth() % grid.getM(), getHeight() - getHeight() % grid.getN());

        for (int i = 0; i < grid.getN(); i++) {
            for (int j = 0; j < grid.getM(); j++) {
                int x = j * cellWidth, y = i * cellHeight;
                switch (grid.getGridElement(i, j)) {
                    case POINT: {
                        g2d.setPaint(Color.yellow);
                        g2d.fillOval(x + cellWidth / 4, y + cellHeight / 4, cellWidth / 2, cellHeight / 2);
                        break;
                    }
                    case BLOCKED: {
                        g2d.setPaint(Color.blue);
                        g2d.fillRect(x, y, cellWidth, cellHeight);
                        break;
                    }
                    case PACMAN: {
                        g2d.drawImage(pacman.getIcon(), x, y, cellWidth, cellHeight, this);
                        break;
                    }
                    case GHOST1:
                    case GHOST2:
                        g2d.drawImage(ghosts[0].getIcon(), x, y, cellWidth, cellHeight, this);
                }
            }
        }
        if (!gameOn) {
            g2d.setPaint(Color.RED);
            g2d.setFont(new Font("Times New Roman", Font.BOLD, 40));
            g2d.drawString("GAME OVER :(", getWidth() / 3, getHeight() / 3);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    public void paintFailure(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOn) {
            gameOn = pacman.move(grid);
            for (Ghost ghost : ghosts) {
                gameOn &= ghost.move(grid);
            }
            repaint();
        }
    }
}


public class UI extends JFrame {

    private final static int width = 600;
    private final static int height = 700;


    public UI(Grid grid, Pacman pacman, Ghost[] ghosts) {

        add(new Surface(grid, pacman, ghosts));

        addKeyListener(new PacmanController(pacman));

        setTitle("Pac-Man Application");
        setSize(grid.getM() * (width / grid.getM()), 22 + grid.getN() * (height / grid.getN()));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
