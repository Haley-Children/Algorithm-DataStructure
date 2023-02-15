package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main_18258 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder(n);

        Deque<String> dq = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            String[] cmd = br.readLine().split(" ");

            switch(cmd[0]) {
                case "push":
                    dq.addLast(cmd[1]);
                    break;
                case "pop":
                    sb.append(dq.isEmpty() ? "-1" : dq.pollFirst()).append("\n");
                    break;
                case "size":
                    sb.append(dq.size()).append("\n");
                    break;
                case "front":
                    sb.append(dq.isEmpty() ? "-1" : dq.peekFirst()).append("\n");
                    break;
                case "back":
                    sb.append(dq.isEmpty() ? "-1" : dq.peekLast()).append("\n");
                    break;
                // == empty
                default:
                    sb.append(dq.isEmpty() ? "1" : "0").append("\n");
            }
        } // end of for

        System.out.println(sb);
    }

}
