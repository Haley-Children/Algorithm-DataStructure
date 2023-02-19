package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_4949 {
    // 여는 괄호를 저장할 스택을 만든다
    // 닫는 괄호를 만날 때마다 스택의 top과 같은 쌍이면 통과, 아니면 no 반환
    // 모든 문자를 확인했을 때 no반환 없이 스택이 empty이면 yes
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;

        Stack<Character> stack = new Stack<>();
        // .을 입력받은 게 아니면 계속 실행
        input :
        while(!(line = br.readLine()).equals(".")) {

            stack.clear();

            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);
                if(ch == '(' || ch == '[') {
                    stack.push(ch);
                } else if (ch == ')') {
                    if(!stack.isEmpty() && stack.peek() == '(') {
                        stack.pop();
                    } else {
                        sb.append("no\n");
                        continue input;
                    }
                } else if (ch == ']') {
                    if(!stack.isEmpty() && stack.peek() == '[') {
                        stack.pop();
                    } else {
                        sb.append("no\n");
                        continue input;
                    }
                }
            } // end of for

            // 모든 문자를 확인했을 때 괄호가 알맞게 떨어지면 yes 아니면 no
            if(stack.isEmpty()) {
                sb.append("yes\n");
            } else {
                sb.append("no\n");
            }

        }
        System.out.println(sb);
    }
}
