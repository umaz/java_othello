import java.util.Arrays;

class Board {
  private int[][] board = {
    { Constant.WALL, Constant.WALL, Constant.WALL, Constant.WALL, Constant.WALL, Constant.WALL, Constant.WALL,
        Constant.WALL, Constant.WALL, Constant.WALL },
    { Constant.WALL, Constant.EMPTY, Constant.EMPTY, Constant.EMPTY, Constant.EMPTY, Constant.EMPTY, Constant.EMPTY,
        Constant.EMPTY, Constant.EMPTY, Constant.WALL },
    { Constant.WALL, Constant.EMPTY, Constant.EMPTY, Constant.EMPTY, Constant.EMPTY, Constant.EMPTY, Constant.EMPTY,
        Constant.EMPTY, Constant.EMPTY, Constant.WALL },
    { Constant.WALL, Constant.EMPTY, Constant.EMPTY, Constant.EMPTY, Constant.EMPTY, Constant.EMPTY, Constant.EMPTY,
        Constant.EMPTY, Constant.EMPTY, Constant.WALL },
    { Constant.WALL, Constant.EMPTY, Constant.EMPTY, Constant.EMPTY, Constant.WHITE, Constant.BLACK, Constant.EMPTY,
        Constant.EMPTY, Constant.EMPTY, Constant.WALL },
    { Constant.WALL, Constant.EMPTY, Constant.EMPTY, Constant.EMPTY, Constant.BLACK, Constant.WHITE, Constant.EMPTY,
        Constant.EMPTY, Constant.EMPTY, Constant.WALL },
    { Constant.WALL, Constant.EMPTY, Constant.EMPTY, Constant.EMPTY, Constant.EMPTY, Constant.EMPTY, Constant.EMPTY,
        Constant.EMPTY, Constant.EMPTY, Constant.WALL },
    { Constant.WALL, Constant.EMPTY, Constant.EMPTY, Constant.EMPTY, Constant.EMPTY, Constant.EMPTY, Constant.EMPTY,
        Constant.EMPTY, Constant.EMPTY, Constant.WALL },
    { Constant.WALL, Constant.EMPTY, Constant.EMPTY, Constant.EMPTY, Constant.EMPTY, Constant.EMPTY, Constant.EMPTY,
        Constant.EMPTY, Constant.EMPTY, Constant.WALL },
    { Constant.WALL, Constant.WALL, Constant.WALL, Constant.WALL, Constant.WALL, Constant.WALL, Constant.WALL,
        Constant.WALL, Constant.WALL, Constant.WALL }
  };
  public int[][] get_board() {
    return board;
  }
  public void show_board() {
    System.out.print("\n\n  " + String.join(" ", Constant.COL_NUM.keySet()));
    int i = 0;
    for (int[] row : board) {
      if (Constant.ROW_NUM.get(String.valueOf(i)) != null) {        
        System.out.print(Constant.ROW_NUM.get(String.valueOf(i)));
      }
      for (int col : row) {
        switch (col) {
          case Constant.EMPTY:
            System.out.print("\u001b[32m");
            System.out.print(" □");
            System.out.print("\u001b[0m");
            break;        
          case Constant.BLACK:
            System.out.print(" ○");
            break;
          case Constant.WHITE:
            System.out.print(" ●");
            break;
          default:
            break;
        }
      }
      System.out.print("\n");
      i++;
    }
  }
}

class Main {
  public static void main(String[] argd) {
    Board board = new Board();
    board.show_board();
    board.get_board();
  }
}