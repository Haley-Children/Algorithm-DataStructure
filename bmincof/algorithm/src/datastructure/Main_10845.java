package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main_10845 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        String[] cmd;
        Deque<String> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            cmd = br.readLine().split(" ");

            if (cmd[0].equals("push")) {
                q.offer(cmd[1]);
            } else if (cmd[0].equals("pop")) {
                if(q.isEmpty()) {
                    sb.append("-1\n");
                } else {
                    sb.append(q.poll()).append('\n');
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
