package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_2493_탑_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder ans = new StringBuilder();
        Stack<Top> stack = new Stack<>();
        for (int i = 1; i <= N; i++) {
            int t = Integer.parseInt(st.nextToken());
            while (!stack.isEmpty()) {
                if (stack.peek().h > t) {
                    ans.append(stack.peek().idx).append(" ");
                    break;
                }
                stack.pop();
            }
            if (stack.isEmpty()) {
                ans.append(0).append(" ");
            }
            stack.push(new Top(i, t));
        }
        System.out.println(ans);
    }

    private static class Top {

        int idx;
        int h;

        public Top(int idx, int h) {
            this.idx = idx;
            this.h = h;
        }
    }


}
