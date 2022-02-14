public enum Direction {
    UP, LEFT, DOWN, RIGHT;

    public int getDi() {
        switch (this) {
            case UP: return -1;
            case DOWN: return 1;
            default: return 0;
        }
    }

    public int getDj() {
        switch (this) {
            case LEFT: return -1;
            case RIGHT: return 1;
            default: return 0;
        }
    }
}


