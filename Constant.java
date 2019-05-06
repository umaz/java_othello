import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Constant {
  private Constant() {}

  public static final int EMPTY = 0;
  public static final int BLACK = 1;
  public static final int WHITE = -1;
  public static final int WALL = 2;

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
}