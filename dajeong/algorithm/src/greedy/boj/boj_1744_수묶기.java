package greedy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class boj_1744_수묶기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> minus = new ArrayList<>();
        ArrayList<Integer> plus = new ArrayList<>();
        boolean zero = false;
        boolean one = false;
        int oneCnt = 0; // 1이 여러번 나올 수 있음
        for (int i = 0; i < N; i++) {
            int t = Integer.parseInt(br.readLine());
            if (t < 0) {
                minus.add(t);
            } else if (t > 0) {
                if (t != 1) { // 1 초과
                    plus.add(t);
                } else { // 1
                    one = true;
                    oneCnt++;
                }
            } else { // 0
                zero = true;
            }
        }

        Collections.sort(minus);
        Collections.sort(plus, Collections.reverseOrder()); // ** 헉 주의!! 양수는 큰 것부터! 음수는 작은 것부터!

        int size = minus.size();
        long sum = 0;
        if (size % 2 == 0) {
            for (int i = 0; i < size; i += 2) {
                sum += ((long) minus.get(i) * minus.get(i + 1));
            }
        } else {
            for (int i = 0; i < size - 1; i += 2) {
                sum += ((long) minus.get(i) * minus.get(i + 1));
            }
            if (!zero) {
                sum += minus.get(size - 1);
            }
        }

        size = plus.size();
        if (size % 2 == 0) {
            for (int i = 0; i < size; i += 2) {
                sum += ((long) plus.get(i) * plus.get(i + 1));
            }
        } else {
            for (int i = 0; i < size - 1; i += 2) {
                sum += ((long) plus.get(i) * plus.get(i + 1));
            }
            sum += plus.get(size - 1);
        }

        if (one) {
            sum += oneCnt;
        }

        System.out.println(sum);

    }

}
