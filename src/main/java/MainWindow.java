import javax.swing.*;
import java.awt.*;


class GridView extends JPanel {

    private final Grid grid;
    private final Pacman pacman;
    private final Image ghostIcon = Icons.GHOST.icon;

    public GridView(Grid grid, Pacman pacman) {
        this.grid = grid;
        this.pacman = pacman;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

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
                        g2d.drawImage(ghostIcon, x, y, cellWidth, cellHeight, this);
                }
            }
        }
    }
}


public class MainWindow extends JFrame {

    private final static int width = 400;
    private final static int height = 500;
    private GridView gridView;

    public MainWindow(Grid grid, Pacman pacman) {

        gridView = new GridView(grid, pacman);
        add(gridView);

        setTitle("Pac-Man Application");
        setSize(grid.getM() * (width / grid.getM()), 22 + grid.getN() * (height / grid.getN()));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void repaint() {
        gridView.repaint();
    }

}
