package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class boj_2164_카드2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> queue = new ArrayDeque<>(500005);
        for (int i = 1; i <= N; i++) {
            queue.offer(i);
        }
        while (queue.size()>1) {
            queue.poll();
            queue.offer(queue.poll());
        }
        System.out.println(queue.peek());
    }
}
