package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Stack;

public class boj_1874_스택수열 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int idx = 0;
        for (int i = 1; i <= n; i++) {
            stack.push(i);
            sb.append("+").append("\n");
            while ((!stack.isEmpty()) && (idx < n) && (stack.peek() == arr[idx])) {
                stack.pop();
                sb.append("-").append("\n");
                idx++;
            }
        }

        if (!stack.isEmpty() || idx != n) {
            System.out.println("NO");
        } else {
            System.out.println(sb);
        }

    }

}
