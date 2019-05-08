import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Constant {
  private Constant() {}

  public static final int EMPTY = 0;
  public static final int BLACK = 1;
  public static final int WHITE = -1;
  public static final int WALL = 2;

  public static final Map<Integer, String> COLOR;
  static {
    Map<Integer, String> color = new HashMap<>();
    color.put(BLACK, "黒");
    color.put(WHITE, "白");
    COLOR = Collections.unmodifiableMap(color);
  }

  public static final Map<String, Integer> ROW_NUM;
  static {
    Map<String, Integer> row_num = new HashMap<>();
    row_num.put("1", 1);
    row_num.put("2", 2);
    row_num.put("3", 3);
    row_num.put("4", 4);
    row_num.put("5", 5);
    row_num.put("6", 6);
    row_num.put("7", 7);
    row_num.put("8", 8);
    ROW_NUM = Collections.unmodifiableMap(row_num);
  }
  public static final Map<String, Integer> COL_NUM;
  static {
    Map<String, Integer> col_num = new HashMap<>();
    col_num.put("a", 1);
    col_num.put("b", 2);
    col_num.put("c", 3);
    col_num.put("d", 4);
    col_num.put("e", 5);
    col_num.put("f", 6);
    col_num.put("g", 7);
    col_num.put("h", 8);
    COL_NUM = Collections.unmodifiableMap(col_num);
  }

  //ひっくり返す方向
  public static final int NONE = 0; //= 0000 0000
  public static final int UPPER_LEFT = 1; //= 0000 0001
  public static final int UPPER = 2; //= 0000 0010
  public static final int UPPER_RIGHT = 4; //= 0000 0100
  public static final int RIGHT = 8; //= 0000 1000
  public static final int LOWER_RIGHT = 16; //= 0001 0000
  public static final int LOWER = 32; //= 0010 0000
  public static final int LOWER_LEFT = 64; //= 0100 0000
  public static final int LEFT = 128; //= 1000 0000

  //結果
  public static final int MOVE = 1;
  public static final int PASS = 2;
  public static final int FINISH = 3;

  //モード番号
  public static final int COM = 1;
  public static final int HUMAN = 2;
  public static final int WATCH = 3;
  public static final int EXIT = 4;
}