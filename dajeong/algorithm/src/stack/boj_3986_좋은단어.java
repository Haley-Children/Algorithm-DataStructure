package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class boj_3986_좋은단어 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayDeque<Character> stack;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            stack = new ArrayDeque<>();
            String str = br.readLine();
            for (char c : str.toCharArray()) {
                if (stack.isEmpty() || stack.peek() != c) {
                    stack.push(c);
                } else {
                    stack.pop();
                }
            }
            if (stack.size() == 0) {
                ans++;
            }
        }
        System.out.println(ans);
    }

}
