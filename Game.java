class Game {
  private int turn = 0;
  private Board board = new Board();
  private Player first;
  private Player second;
  private int current_color = Constant.BLACK;

  Game(int lv, int order) {
    if (order == 1) {
      first = new Player(Constant.BLACK, 0);
      second = new Player(Constant.WHITE, lv);
    } else {
      first = new Player(Constant.BLACK, lv);
      second = new Player(Constant.WHITE, 0);
    }
  }

  Game() {
    first = new Player(Constant.BLACK, 0);
    second = new Player(Constant.WHITE, 0);
  }

  Game(int[] lv) {
    first = new Player(Constant.BLACK, lv[0]);
    second = new Player(Constant.WHITE, lv[1]);
  }

  public void phase() {
    switch (status()) {
      case Constant.FINISH:
        end_game();
        break;
    
      case Constant.PASS:
        System.out.println(turn+1 + "手目");
        System.out.println(Constant.COLOR.get(current_color) + "の手番です");
        System.out.println("パスしました");
        board.show_board();
        phase();
        break;

      case Constant.MOVE:
        System.out.println(Constant.COLOR.get(current_color) + "の手番です");
        int[] move;
        if (current_color == Constant.BLACK) {
          move = first.put_stone(board);          
        } else {
          move = second.put_stone(board);
        }
        reverse(move);
        board.show_board();
        phase();
        break;

      default:
        break;
    }
  }
 
  private int status() {
    if (board.get_putable_cells(current_color).size() == 0) {
      change_phase();
      if (board.get_putable_cells(current_color).size() == 0) {
        return Constant.FINISH;
      } else {
        return Constant.PASS;
      }
    } else {
      return Constant.MOVE;
    }
  }

  private void reverse(int[] move) {
    int row = move[0];
    int col = move[1];
    board.reverse(row, col, current_color);
    change_phase();
  }

  private void change_phase() {
    current_color *= -1;
  }

  private void end_game() {
    int[] count = board.count();
    int black = count[0];
    int white = count[1];
    if (black > white) {
      System.out.println("\n黒:" + black + " 対 白:" + white + " で黒の勝ち\n");
    } else if (white > black) {
      System.out.println("\n黒:" + black + " 対 白:" + white + " で白の勝ち\n");
    } else {
      System.out.println("\n黒:" + black + " 対 白:" + white + " で引き分け\n");
    }
  }
}