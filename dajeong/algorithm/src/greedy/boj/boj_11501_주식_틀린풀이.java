package greedy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_11501_주식_틀린풀이 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());
             PriorityQueue<Pair> money = new PriorityQueue<>();
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                 money.add(new Pair(i, arr[i]));
            }

            // 가장 큰 값에 팔기
            // 최대값 & idx 작은 순으로 정렬
            // -> 틀린 풀이인 이유: 반례 5 4 3 2 1 2 3

            long purchasedPrice = arr[0];
            int cnt = 1; // 산 주식의 수
            long earnedPrice = 0L;
            for (int i = 1; i < arr.length; i++) {
                if (money.peek().i == i) {
                    earnedPrice += ((long) cnt * arr[i] - purchasedPrice);
                    cnt = 0;
                    purchasedPrice = 0L;
                    money.poll();
                } else {
                    purchasedPrice += arr[i];
                    cnt++;
                }
            }

            sb.append(earnedPrice).append("\n");
        }
        System.out.println(sb);
    }


    private static class Pair implements Comparable<Pair> {

        int i;
        int m;

        public Pair(int i, int m) {
            this.i = i;
            this.m = m;
        }

        @Override
        public int compareTo(Pair o) {
            if (m == o.m) {
                return i - o.i;
            } else {
                return o.m - m;
            }
        }
    }


}
