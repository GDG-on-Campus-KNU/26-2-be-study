import java.util.*;

public class Main {
    record Pair(int value, String word) {}   // value: 1=존재, 0=삭제됨

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Pair> list = new ArrayList<>();

        while (true) {
            System.out.println("| 0.종료 | 1.생성 Create | 2.읽기 Read | 3.수정 Update | 4.삭제 Delete");
            int mode = sc.nextInt();
            sc.nextLine();                       // 남은 줄바꿈 비우기

            if (mode == 0) break;

            switch (mode) {
                case 1: {
                    String word = sc.nextLine();
                    list.add(new Pair(1, word));
                    System.out.println("생성됨 id = " + (list.size() - 1));
                    break;
                }
                case 2: {
                    int id = sc.nextInt();
                    sc.nextLine();
                    if (id < 0 || id >= list.size() || list.get(id).value() == 0) {
                        System.out.println("없는 항목입니다.");
                        break;
                    }
                    System.out.println(list.get(id).word());
                    break;
                }
                case 3: {
                    int id = sc.nextInt();
                    sc.nextLine();
                    String word = sc.nextLine();
                    if (id < 0 || id >= list.size() || list.get(id).value() == 0) {
                        System.out.println("없는 항목입니다.");
                        break;
                    }
                    list.set(id, new Pair(1, word));
                    break;
                }
                case 4: {
                    int id = sc.nextInt();
                    sc.nextLine();
                    if (id < 0 || id >= list.size()) {
                        System.out.println("없는 항목입니다.");
                        break;
                    }
                    list.set(id, new Pair(0, list.get(id).word()));
                    break;
                }
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }

        sc.close();
    }
}