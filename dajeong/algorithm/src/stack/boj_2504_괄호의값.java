package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class boj_2504_괄호의값 {

    /* 자료 참고 : 분배법칙 이용

    1. ‘(’이면 tmp변수에 2를 곱해준다.

    2. ‘[’이면 tmp변수에 3을 곱해준다.

    3. ‘)’일 때

        a. 만약 stack이 비어있거나, ‘(’가 아니면 잘못된 입력이므로 종료한다.

        b. 이전 문자가 ‘(’ 이면 answer에 tmp를 더 해준다.

    4. ‘]’일 때

        a. 만약 stack이 비어있거나, ‘[’가 아니면 잘못된 입력이므로 종료한다.

        b. 이전 문자가 ‘[’ 이면 answer에 tmp를 더 해준다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayDeque<Character> stack = new ArrayDeque<>();
        int ans = 0;
        int tmp = 1;
        boolean flag = true;
        String str = br.readLine();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') {
                stack.push(c);
                tmp *= 2;
            } else if (c == '[') {
                stack.push(c);
                tmp *= 3;
            } else if (c == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    flag = false;
                    break;
                }

                if (str.charAt(i - 1) == '(') {
                    ans += tmp;
                }
                stack.pop();
                tmp /= 2;
            } else {
                if (stack.isEmpty() || stack.peek() != '[') {
                    flag = false;
                    break;
                }
                if (str.charAt(i - 1) == '[') {
                    ans += tmp;
                }
                stack.pop();
                tmp /= 3;
            }
        }

        if (!flag) {
            System.out.println(0);
        } else {
            if (!stack.isEmpty()) {
                System.out.println(0);
            } else {
                System.out.println(ans);
            }
        }
    }
}
