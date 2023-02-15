package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main_10866 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        String[] cmd;
        Deque<String> q = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            cmd = br.readLine().split(" ");

            if (cmd[0].equals("push_back")) {
                q.offer(cmd[1]);
            } else if (cmd[0].equals("push_front")) {
                q.offerFirst(cmd[1]);
            }
            else if (cmd[0].equals("pop_front")) {
                if(q.isEmpty()) {
                    sb.append("-1\n");
                } else {
                    sb.append(q.poll()).append('\n');
                }
            } else if (cmd[0].equals("pop_back")) {
                if(q.isEmpty()) {
                    sb.append("-1\n");
                } else {
                    sb.append(q.pollLast()).append('\n');
                }
            } else if (cmd[0].equals("size")) {
                sb.append(q.size()).append('\n');
            } else if (cmd[0].equals("empty")) {
                if (q.isEmpty()) {
                    sb.append("1\n");
                } else {
                    sb.append("0\n");
                }
            } else if (cmd[0].equals("front")) {
                if (q.isEmpty()) {
                    sb.append("-1\n");
                } else {
                    sb.append(q.peek()).append('\n');
                }
            } else if (cmd[0].equals("back")) {
                if (q.isEmpty()) {
                    sb.append("-1\n");
                } else {
                    sb.append(q.getLast()).append('\n');
                }
            }
        }
        System.out.println(sb);
    }
}
