package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_3986 {
    // 문자를 저장할 스택을 만든다
    // 스택의 top과 같은 문자를 만나면 pop
    // 모든 문자를 확인했을 때 스택이 empty이면 단어 개수 + 1
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 좋은 단어의 수
        int count = 0;
        for(int i = 0; i < n; i++) {
            Stack<Character> stack = new Stack<>();
            String line = br.readLine();

            // 각 문장의 모든 문자를 확인한다.
            for(int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                // 같은 문자를 만나면 pop 아니면 push
                if(stack.isEmpty() || stack.peek() != c) {
                    stack.push(c);
                } else if(stack.peek() == c) {
                    stack.pop();
                }
            }
            // 모든 문자 확인 후 스택이 비었으면 == 모든 문자가 선끼리 교차하지 않고 짝을 이루면
            if(stack.isEmpty()) count++;
        }

        System.out.println(count);
    }
}
