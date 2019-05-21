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
      switch (lv) {
        case 1:
          cell = lv1(board);        
          break;
      
        case 2:
          cell = lv2(board);
          break;
        
        case 3:
          cell = lv3(board);
          break;

        case 4:
          cell = lv4(board);

        default:
          cell = lv1(board);
          break;
      }
      String row = Constant.ROW_VALUE.get(cell[0]);
      String col = Constant.COL_VALUE.get(cell[1]);
      System.out.print(turn + "手目: " + col + row);
    }
    return cell;
  }

  private int[] lv0(Board board, int turn) {
    ArrayList<int[]> putable_cells = get_putable_cells(board);
    String cell_list = "";
    for(int[] cell : putable_cells) {
      cell_list += "(" + Constant.COL_VALUE.get(cell[1]) + Constant.ROW_VALUE.get(cell[0]) + ")";
    }
    System.out.println(cell_list);
    System.out.print("\n" + turn + "手目: ");
    String move = sc.next();
    int[] cell = new int[2];
    if(move.matches("[a-h][1-8]")) {
      String[] split = move.split("");
      int col = Constant.COL_NUM.get(split[0]);
      int row = Constant.ROW_NUM.get(split[1]);
      cell[0] = row;
      cell[1] = col;
      if(!cell_list.contains(move)) {
        System.out.println("そのマスには打つことはできません");
        System.out.println("打てるマスは" + cell_list + "です");
        cell = put_stone(board, turn);
      }
    }else{
      System.out.println("正しいマスを選択してください");
      cell = put_stone(board, turn);
    }
    return cell;
  }

  private int[] lv1(Board board) {
    ArrayList<int[]> putable_cells = get_putable_cells(board);
    Random rnd = new Random();
    int r = rnd.nextInt(putable_cells.size());
    int[] put_cell = putable_cells.get(r);
    return put_cell;
  }

  private int[] lv2(Board board) {
    ArrayList<int[]> putable_cells = get_putable_cells(board);
    Default d = new Default();
    for(int[] cell : putable_cells) {
      int score = Constant.BOARD_SCORE[cell[0]][cell[1]];
      evaluate(cell, score, d);
    }
    int[] put_cell = select_com_move(d.candicate_cells);
    return put_cell;
  }

  private int[] lv3(Board board) {
    ArrayList<int[]> putable_cells = get_putable_cells(board);
    Default d = new Default();
    for (int[] cell : putable_cells) {
      int[][] undo = new int[10][];
      for(int i = 0; i < 10; i++) {
        undo[i] = board.get_board()[i].clone();
      }
      board.reverse(cell[0], cell[1], color);
      int score = board_score(board);
      board.undo(undo);
      evaluate(cell, score, d);
    }
    int[] put_cell = select_com_move(d.candicate_cells);
    return put_cell;
  }

  private int[] lv4(Board board) {
    ArrayList<int[]> putable_cells = get_putable_cells(board);
    Default d = new Default();
    int depth = 5;
    for (int[] cell : putable_cells) {
      int[][] undo = new int[10][];
      for (int i = 0; i < 10; i++) {
        undo[i] = board.get_board()[i].clone();
      }
      board.reverse(cell[0], cell[1], color);
      int score = 0;
      switch (status(board, -color, depth)) {
        case Constant.FINISH:
          score =  board_score(board);         
          break;
      
        case Constant.PASS:
          score = minmax(board, depth-1, color);
          break;

        case Constant.MOVE:
          score = minmax(board, depth-1, -color);
          break;

        default:
          score = 0;
          break;
      }
      board.undo(undo);
      evaluate(cell, score, d);
    }
    int[] put_cell = select_com_move(d.candicate_cells);
    return put_cell;
  }

  private int minmax(Board board, int depth, int color) {
    int best_score = 999999999;
    ArrayList<int[]> putable_cells = board.get_putable_cells(color);
    for (int[] cell : putable_cells) {
      int[][] undo = new int[10][];
      for (int i = 0; i < 10; i++) {
        undo[i] = board.get_board()[i].clone();
      }
      board.reverse(cell[0], cell[1], color);
      int score = 0;
      switch(status(board, -color, depth)) {
        case Constant.FINISH:
          score =  board_score(board);         
          break;
      
        case Constant.PASS:
          score = minmax(board, depth-1, color);
          break;

        case Constant.MOVE:
          score = minmax(board, depth-1, -color);
          break;

        default:
          score = 0;
          break;
      }
      board.undo(undo);
      //スコアの選択(αβ法)
      if (best_score == 999999999) {
        best_score = score;
      }
      if (color == this.color && score > best_score) {
        best_score = score;
      }
      if (color == -this.color && score < best_score) {
        best_score = score;
      }
    }
    return best_score;
  }

  private ArrayList<int[]> get_putable_cells(Board board) {
    ArrayList<int[]> putable_cells = board.get_putable_cells(color);
    return putable_cells;
  }

  private void evaluate(int[] cell, int score, Default d) {
    if(score > d.best_score) {
      d.candicate_cells = new ArrayList<int[]>();
      d.candicate_cells.add(cell);
      d.best_score = score;
    }else if(score == d.best_score) {
      d.candicate_cells.add(cell);
    }
  }

  private int[] select_com_move(ArrayList<int[]> candicate_cells) {
    Random rnd = new Random();
    int r = rnd.nextInt(candicate_cells.size());
    int[] cell = candicate_cells.get(r);
    return cell;
  }

  private int board_score(Board board) {
    int score = 0;
    int i = 0;
    for (int[] row : board.get_board()) {
      int j = 0;
      for (int col : row) {
        if (col == color) {
          score += Constant.BOARD_SCORE[i][j];
        }
        j++;
      }
      i++;
    }
    return score;
  }

  private int status(Board board, int color, int depth) {
    if (depth == 0) {
      return Constant.FINISH;
    } else {
      if (board.get_putable_cells(color).size() == 0) {
        if (board.get_putable_cells(-color).size() == 0) {
          return Constant.FINISH;
        } else {
          return Constant.PASS;
        }
      } else {
        return Constant.MOVE;
      }
    }
  }
}

class Default {
  int best_score = -999999999;
  ArrayList<int[]> candicate_cells = new ArrayList<int[]>();
  int alpha = -999999999;
  int beta = 999999999;
}