package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

// 내가 설계한 로직을 코드로 옮기는 과정에서 빼먹는 부분이 없는지 확인하기!
// i, j 제대로 확인
public class Main_3986_좋은단어 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int res = 0;
        for (int i = 0; i < n; i++) {
            ArrayDeque<Character> stack = new ArrayDeque<>();
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                char c = str.charAt(j); // i, j 제대로 보자!!!!!
                if (!stack.isEmpty()) {
                    if (stack.peek() == c) {
                        stack.pop();
                    } else {
                        stack.push(c);
                    }
                } else {
                    stack.push(c);
                }
            }
            if (stack.isEmpty()) {
                res++;
            }
        }
        System.out.println(res);
    }
}
