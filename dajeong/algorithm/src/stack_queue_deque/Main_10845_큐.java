package stack_queue_deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_10845_큐 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        Deque<Integer> queue = new ArrayDeque<>();
        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "push":
                    queue.offer(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    int res = !queue.isEmpty() ? queue.poll() : -1;
                    sb.append(res).append("\n");
                    break;
                case "size":
                    sb.append(queue.size()).append("\n");
                    break;
                case "empty":
                    res = queue.isEmpty() ? 1 : 0;
                    sb.append(res).append("\n");
                    break;
                case "front":
                    int first = queue.isEmpty() ? -1 : queue.getFirst();
                    sb.append(first).append("\n");
                    break;
                case "back":
                    int last = queue.isEmpty() ? -1 : queue.getLast();
                    sb.append(last).append("\n");
            }
        }
        System.out.println(sb);
    }
}
