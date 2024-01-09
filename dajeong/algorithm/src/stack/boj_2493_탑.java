package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;


// 첫 풀이) 스택 2개 이용.
// 1. 먼저 입력값 탑 idx, 높이를 객체로 하여 Stack에 저장
// 2. stack에서 값을 하나씩 꺼내서 다른 스택 (remain)에 저장하고, 두 peek을 비교한다.
// 2-1. stack.peek.h > remain.peek.h 일 경우, remain의 idx 배열에 stack size 저장하고 remain 지우기 반복
// 2-2. else, stack의 값을 remain에 넣기
public class boj_2493_탑 {

    private static class Top {

        int idx;
        int h;

        public Top(int idx, int h) {
            this.idx = idx;
            this.h = h;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] ans = new int[N];
        Stack<Top> stack = new Stack<>();
        Stack<Top> remain = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            stack.push(new Top(i, Integer.parseInt(st.nextToken())));
        }

        while (stack.size() > 0) {
            remain.push(stack.pop());
            if (remain.size() > 0 && stack.size() > 0 && remain.peek().h >= stack.peek().h) {
                continue;
            }
            while (remain.size() > 0 && stack.size() > 0 && remain.peek().h < stack.peek().h) {
                ans[remain.peek().idx] = stack.size();
                remain.pop();
            }

        }

        for (int i = 0; i < N; i++) {
            System.out.print(ans[i] + " ");
        }

    }


}
