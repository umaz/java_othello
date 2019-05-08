class Game {
  private int turn = 0;
  private Board board = new Board();
  private Player first = new Player(Constant.BLACK);
  private Player second = new Player(Constant.WHITE);
  private int current_color = Constant.BLACK;

  Game() {
  }

  public void phase() {
    switch (status()) {
      case Constant.FINISH:
        end_game();
        break;
    
      case Constant.PASS:
        System.out.print(turn+1 + "手目\n");
        System.out.print(Constant.COLOR.get(current_color) + "の手番です\n");
        System.out.print("パスしました\n");
        board.show_board();
        phase();
        break;

      case Constant.MOVE:
        System.out.print(Constant.COLOR.get(current_color) + "の手番です\n");
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
      System.out.print("\n黒:" + black + " 対 白:" + white + " で黒の勝ち\n\n");
    } else if (white > black) {
      System.out.print("\n黒:" + black + " 対 白:" + white + " で白の勝ち\n\n");
    } else {
      System.out.print("\n黒:" + black + " 対 白:" + white + " で引き分け\n\n");
    }
  }
}