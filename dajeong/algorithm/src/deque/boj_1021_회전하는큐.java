package deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_1021_회전하는큐 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        LinkedList<Integer> deque = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            deque.offerLast(i);
        }

        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int t = Integer.parseInt(st.nextToken());
            if (deque.indexOf(t) < (double) deque.size() / 2) {
                while (deque.getFirst() != t) {
                    deque.offerLast(deque.pollFirst());
                    sum++;
                }

            } else {
                while (deque.getFirst() != t) {
                    deque.offerFirst(deque.pollLast());
                    sum++;
                }
            }
            // 뽑아내는 연산이 항상 앞의 원소를 뽑아내는 거였다.
            deque.poll();
        }
        System.out.println(sum);
    }

}
