package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class boj_2504_괄호의값 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayDeque<Character> stack = new ArrayDeque<>();
        boolean flag = true; // 바로 전에 열림이면 true 반대면 false
        int ans = 0;
        int tmp = 0;
        int size = 0;
        for (char c : br.readLine().toCharArray()) {
            if (c == '(' || c == '[') {
                stack.push(c);
                if (!flag) {
                    ans += tmp;
                    tmp = 0;
                    size = stack.size();
                }
                flag = true;
            } else {
                int var = 3;
                if (c == ')') {
                    var = 2;
                }
                if (flag) {
                    tmp += var;
                } else {
                    tmp *= var;
                }
                if (size == stack.size()) {
                    ans += tmp;
                    tmp = 0;

                }
            }
        }
    }
}
