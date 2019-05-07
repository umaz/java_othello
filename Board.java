import java.util.ArrayList;

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
    System.out.print("\n");
    int[] stone_count = count();
    System.out.print("\n 黒:" + stone_count[0] + " 白:" + stone_count[1] + "\n\n");
  }

  public void reverse(int row, int col, int color) {
    board[row][col] = color;
    int turn_direction = turnable_direction(row, col, color);
    if ((turn_direction & Constant.UPPER_LEFT) != 0) {
      int i = 1;
      while (board[row - i][col - i] == -color) { // 相手の色が続くまで
        board[row - i][col - i] = color;
        i += 1;
      }
    }
    if ((turn_direction & Constant.UPPER) != 0) {
      int i = 1;
      while (board[row - i][col] == -color) { // 相手の色が続くまで
        board[row - i][col] = color;
        i += 1;
      }
    }
    if ((turn_direction & Constant.UPPER_RIGHT) != 0) {
      int i = 1;
      while (board[row - i][col + i] == -color) { // 相手の色が続くまで
        board[row - i][col + i] = color;
        i += 1;
      }
    }
    if ((turn_direction & Constant.RIGHT) != 0) {
      int i = 1;
      while (board[row][col + i] == -color) { // 相手の色が続くまで
        board[row][col + i] = color;
        i += 1;
      }
    }
    if ((turn_direction & Constant.LOWER_RIGHT) != 0) {
      int i = 1;
      while (board[row + i][col + i] == -color) { // 相手の色が続くまで
        board[row + i][col + i] = color;
        i += 1;
      }
    }
    if ((turn_direction & Constant.LOWER) != 0) {
      int i = 1;
      while (board[row + i][col] == -color) { // 相手の色が続くまで
        board[row + i][col] = color;
        i += 1;
      }
    }
    if ((turn_direction & Constant.LOWER_LEFT) != 0) {
      int i = 1;
      while (board[row + i][col - i] == -color) { // 相手の色が続くまで
        board[row + i][col - i] = color;
        i += 1;
      }
    }
    if ((turn_direction & Constant.LEFT) != 0) {
      int i = 1;
      while (board[row][col - i] == -color) { // 相手の色が続くまで
        board[row][col - i] = color;
        i += 1;
      }
    }
  }

  public int turnable_direction(int row, int col, int color) {
    int direction = Constant.NONE;
    if (board[row - 1][col - 1] == -color) {
      int i = 2;
      while (board[row - i][col - i] == -color) { // 相手の色が続くまで
        i += 1;
      }
      if (board[row - i][col - i] == color) { // 相手の色に続くのが自分の色の場合
        direction |= Constant.UPPER_LEFT;
      }
    }
    if (board[row - 1][col] == -color) {
      int i = 2;
      while (board[row - i][col] == -color) { // 相手の色が続くまで
        i += 1;
      }
      if (board[row - i][col] == color) { // 相手の色に続くのが自分の色の場合
        direction |= Constant.UPPER;
      }
    }
    if (board[row - 1][col + 1] == -color) {
      int i = 2;
      while (board[row - i][col + i] == -color) { // 相手の色が続くまで
        i += 1;
      }
      if (board[row - i][col + i] == color) { // 相手の色に続くのが自分の色の場合
        direction |= Constant.UPPER_RIGHT;
      }
    }
    if (board[row][col + 1] == -color) {
      int i = 2;
      while (board[row][col + i] == -color) { // 相手の色が続くまで
        i += 1;
      }
      if (board[row][col + i] == color) { // 相手の色に続くのが自分の色の場合
        direction |= Constant.RIGHT;
      }
    }
    if (board[row + 1][col + 1] == -color) {
      int i = 2;
      while (board[row + i][col + i] == -color) { // 相手の色が続くまで
        i += 1;
      }
      if (board[row + i][col + i] == color) { // 相手の色に続くのが自分の色の場合
        direction |= Constant.LOWER_RIGHT;
      }
    }
    if (board[row + 1][col] == -color) {
      int i = 2;
      while (board[row + i][col] == -color) { // 相手の色が続くまで
        i += 1;
      }
      if (board[row + i][col] == color) { // 相手の色に続くのが自分の色の場合
        direction |= Constant.LOWER;
      }
    }
    if (board[row + 1][col - 1] == -color) {
      int i = 2;
      while (board[row + i][col - i] == -color) { // 相手の色が続くまで
        i += 1;
      }
      if (board[row + i][col - i] == color) { // 相手の色に続くのが自分の色の場合
        direction |= Constant.LOWER_LEFT;
      }
    }
    if (board[row][col - 1] == -color) {
      int i = 2;
      while (board[row][col - i] == -color) { // 相手の色が続くまで
        i += 1;
      }
      if (board[row][col - i] == color) { // 相手の色に続くのが自分の色の場合
        direction |= Constant.LEFT;
      }
    }
    return direction;
  }

  public ArrayList<int[]> get_putable_cells(int color) {
    ArrayList<int[]> putable_cells = new ArrayList<int[]>();
    int i = 0;
    for (int[] row : board) {
      int j = 0;
      for (int col : row) {
        if (col == Constant.EMPTY) {
          int turnable_direction = turnable_direction(i, j, color);
          if (turnable_direction != Constant.NONE) {
            int[] cell = {i, j};
            putable_cells.add(cell);
          }
        }
        j ++;
      }
      i ++;
    }
    return putable_cells;
  }

  public void undo(int[][] board) {
    this.board = board;
  }

  public int[] count() {
    int black = 0;
    int white = 0;
    for (int[] row : board) {
      for (int col : row) {
        switch (col) {
          case Constant.BLACK:
            black += 1;
            break;
          case Constant.WHITE:
            white += 1;
            break;
          default:
            break;
        }
      }
    }
    int[] count = {black, white};
    return count;
  }
}