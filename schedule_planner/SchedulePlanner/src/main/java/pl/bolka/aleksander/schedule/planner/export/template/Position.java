package pl.bolka.aleksander.schedule.planner.export.template;

public class Position {

    private int gridX;

    private int gridY;

    public Position() {
    }

    public Position(int gridX, int gridY) {
        this.gridX = gridX;
        this.gridY = gridY;
    }


    public int getGridX() {
        return gridX;
    }

    public void setGridX(int gridX) {
        this.gridX = gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public void setGridY(int gridY) {
        this.gridY = gridY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (gridX != position.gridX) return false;
        return gridY == position.gridY;
    }

    @Override
    public int hashCode() {
        int result = gridX;
        result = 31 * result + gridY;
        return result;
    }
}