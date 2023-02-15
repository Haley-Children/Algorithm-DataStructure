package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_10828 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        String[] cmd;
        Stack<String> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            cmd = br.readLine().split(" ");

            if (cmd[0].equals("push")) {
                st.push(cmd[1]);
            } else if (cmd[0].equals("pop")) {
                if(st.isEmpty()) {
                    sb.append("-1\n");
                } else {
                    sb.append(st.pop()).append('\n');
                }
            } else if (cmd[0].equals("size")) {
                sb.append(st.size()).append('\n');
            } else if (cmd[0].equals("empty")) {
                if (st.isEmpty()) {
                    sb.append("1\n");
                } else {
                    sb.append("0\n");
                }
            } else if (cmd[0].equals("top")) {
                if (st.isEmpty()) {
                    sb.append("-1\n");
                } else {
                    sb.append(st.peek()).append('\n');
                }
            }
        }
        System.out.println(sb);
    }

}
