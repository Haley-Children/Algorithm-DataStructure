package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_10828_스택 {

    static int[] stack;
    static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        stack = new int[n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "push":
                    push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    sb.append(pop()).append("\n");
                    break;
                case "size":
                    sb.append(size()).append("\n");
                    break;
                case "empty":
                    sb.append(empty()).append("\n");
                    break;
                case "top":
                    sb.append(top()).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }

    private static int top() {
        if (size == 0) {
            return -1;
        }
        return stack[size - 1];
    }

    private static int empty() {
        if (size == 0) {
            return 1;
        }
        return 0;
    }

    private static int size() {
        return size;
    }

    // 코드 개선
    private static int pop() {
        int ans = -1;
        if (size != 0) {
            ans = stack[--size];
        }
        return ans;
    }

    private static void push(int parseInt) {
        stack[size++] = parseInt;
    }

}
