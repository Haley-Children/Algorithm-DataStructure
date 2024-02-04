package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class boj_4949_균형잡힌세상 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        ArrayDeque<Character> stack;
        loop:
        while (true) {
            stack = new ArrayDeque<>();
            String str = br.readLine();
            if (str.isEmpty() || str.charAt(0) == '.') {
                break;
            }
            for (char c : str.toCharArray()) {
                if (c == '[' || c == '(') {
                    stack.push(c);
                }

                // Map에다가 괄호쌍을 넣어두면 로직 하나로 퉁칠 수 있지만 귀찮아서 안함
                if (c == ']') {
                    if (stack.isEmpty() || stack.peek() != '[') {
                        ans.append("no\n");
                        continue loop;
                    } else if (stack.peek() == '[') {
                        stack.pop();
                    }

                } else if (c == ')') {
                    if (stack.isEmpty() || stack.peek() != '(') {
                        ans.append("no\n");
                        continue loop;
                    } else if (stack.peek() == '(') {
                        stack.pop();
                    }

                }
            }
            if (stack.size() > 0) {
                ans.append("no").append("\n");
            } else {
                ans.append("yes").append("\n");

            }
        }
        System.out.println(ans);
    }

}
