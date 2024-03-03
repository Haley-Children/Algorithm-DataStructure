package greedy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2170_선긋기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Pair[] pairs = new Pair[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if (s > e) {
                int tmp = s;
                s = e;
                e = tmp;
            }
            pairs[i] = new Pair(s, e);
        }
        Arrays.parallelSort(pairs);

        //  처음에 틀린 이유: 최대 right number와의 비교가 아닌 바로 이전의 right number와 비교를 하면 틀리게 된다. 따라서 curS, curE 갱신 시점을 주의해야한다.
        // 지금까지 그리고 있는 선의 시작과 끝
        int curS = pairs[0].s; // 현재 시점까지 고른 선분 중 "최대" s
        int curE = pairs[0].e; // 현재 시점까지 고른 선분 중 "최대" e
        long sum = curE - curS;
        for (int i = 1; i < N; i++) {
            int nxtS = pairs[i].s;
            int nxtE = pairs[i].e;
            if (curE >= nxtS && curE < nxtE) { // 선분이 이어질 때
                sum += (nxtE - curE);
                if (curE < nxtE) {
                    curE = nxtE;
                }
            } else if (curE < nxtS) { // 새 선분이 이어지지 않고 끊어져 있을 때
                sum += (nxtE - nxtS);
                curS = nxtS;
                curE = nxtE;
            }

        }
        System.out.println(sum);
    }


    private static class Pair implements Comparable<Pair> {

        int s;
        int e;

        public Pair(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Pair o) {
            if (s == o.s) {
                return o.e - e;
            } else {
                return s - o.s;
            }
        }
    }
}
