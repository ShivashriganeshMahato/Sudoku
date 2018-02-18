import java.util.*;

public class Open {
    private int row;
    private int col;
    private List<Integer> possib;

    public Open(int row, int col) {
        this.row = row;
        this.col = col;
        possib = new ArrayList<>();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public List<Integer> getPossib() {
        return possib;
    }
}
