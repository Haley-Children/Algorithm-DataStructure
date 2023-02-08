package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_1026 {
    // 둘다 정렬해서 풀기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> a = new ArrayList<>(n);
        ArrayList<Integer> b = new ArrayList<>(n);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            b.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(a);
        Collections.sort(b, Comparator.reverseOrder());

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a.get(i) * b.get(i);
        }
        System.out.println(sum);
    }
}
