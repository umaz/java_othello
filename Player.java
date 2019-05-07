import java.util.ArrayList;
import java.util.Random;

class Player {
  private int color;
  Player(int c) {
    color = c;
  }

  public int get_color() {
    return color;
  }

  public int[] put_stone(Board board) {
    ArrayList<int[]> putable_cells = get_putable_cells(board);
    Random rnd = new Random();
    int r = rnd.nextInt(putable_cells.size());
    int[] put_cells = putable_cells.get(r);
    return put_cells;
  }

  private ArrayList<int[]> get_putable_cells(Board board) {
    ArrayList<int[]> putable_cells = board.get_putable_cells(color);
    return putable_cells;
  }
}