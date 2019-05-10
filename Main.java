import java.util.Scanner;

class Main {
  static Scanner scanner = new Scanner(System.in);
  public static void menu() {
    while (true) {
      System.out.println("COMと対戦: 1");
      System.out.println("二人で対戦: 2");
      System.out.println("観戦: 3");
      System.out.println("終了: 4");
      int mode = scanner.nextInt();
      if (mode == Constant.EXIT) {
        break;
      } else if (mode == Constant.COM) {
        System.out.println("COMのレベルを選択してください");
        int lv = scanner.nextInt();
        System.out.println("手番を選択してください");
        System.out.println("1: 先手(黒), 2: 後手(白)");
        int order = scanner.nextInt();
        switch (order) {
        case 1:
          System.out.println("あなたの先手で始めます");
          break;
        case 2:
          System.out.println("COMの先手で始めます");
        default:
          break;
        }
        Game game = new Game(lv, order);
        game.phase();
      } else if (mode == Constant.HUMAN) {
        Game game = new Game();
        game.phase();
      } else if (mode == Constant.WATCH) {
        System.out.println("先手のレベルを選択してください");
        int first = scanner.nextInt();
        System.out.println("後手のレベルを選択してください");
        int second = scanner.nextInt();
        int[] lv = {first, second};
        Game game = new Game(lv);
        game.phase();
      } else {
        System.out.println("1~4で選択したください\n");
      }
    }
  }
  
  public static void main(String[] argd) {
    menu();
  }
}