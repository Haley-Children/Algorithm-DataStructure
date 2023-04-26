package twopointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2230_수고르기 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 갯수
        int M = Integer.parseInt(st.nextToken()); // 최소 차이

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(list);

        int p1 = 0, p2 = 0;
        int min = Integer.MAX_VALUE;
        while(p1 < N && p2 < N) {
            if (list.get(p2) - list.get(p1) >= M) {
                if (min > list.get(p2) - list.get(p1)) min = list.get(p2) - list.get(p1);
                p1++;
            } else {
                p2++;
            }
        }
        System.out.println(min);

    }
}
