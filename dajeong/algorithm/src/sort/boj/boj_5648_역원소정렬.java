package sort.boj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class boj_5648_역원소정렬 {

    // 입력 방식 이상해서 scanner로 구현
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            long t = Long.parseLong(sc.next());
            long tmp = 0L;
            while (t > 0) {
                long r = t % 10;
                tmp = tmp * 10 + r;
                t /= 10;
            }
            list.add(tmp);
        }

        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(list.get(i)).append("\n");
        }
        System.out.println(sb);
    }

}
