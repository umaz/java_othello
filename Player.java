import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Player {
  private int color;
  private int lv;
  private Scanner sc = new Scanner(System.in);
  Player(int c, int l) {
    color = c;
    lv = l;
  }

  public int get_color() {
    return color;
  }

  public int[] put_stone(Board board, int turn) {
    int[] cell;
    if (lv == 0) {
      cell = lv0(board, turn);
    } else {
      cell = lv1(board);
      String row = Constant.ROW_VALUE.get(cell[0]);
      String col = Constant.COL_VALUE.get(cell[1]);
      System.out.print(turn + "手目: " + col + row);
    }
    return cell;
  }

  private int[] lv0(Board board, int turn) {
    ArrayList<int[]> putable_cells = get_putable_cells(board);
    for(int[] cell : putable_cells) {
      System.out.print("(" + Constant.COL_VALUE.get(cell[1]) + Constant.ROW_VALUE.get(cell[0]) + ")");
    }
    System.out.print("\n" + turn + "手目: ");
    String move = sc.next();
    String[] split = move.split("");
    int col = Constant.COL_NUM.get(split[0]);
    int row = Constant.ROW_NUM.get(split[1]);
    int[] cell = {row, col};
    return cell;
  }

  private int[] lv1(Board board) {
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