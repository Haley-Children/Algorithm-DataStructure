package sort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main_5648 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int n = sc.nextInt();
        List<Long> arr = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            arr.add(Long.valueOf(sc.next()));
        }

        for (int i = 0; i < n; i++) {
            long tmp = arr.get(i);
            long res = 0;
            while (tmp > 0) {
                long r = tmp % 10;
                res = res * 10 + r;
                tmp = tmp / 10;
            }
            arr.set(i, res);
        }
        Collections.sort(arr);
        for (long a : arr) {
            sb.append(a).append("\n");
        }
        System.out.println(sb);

    }

}
