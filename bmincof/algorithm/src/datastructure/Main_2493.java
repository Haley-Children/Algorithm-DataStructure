package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2493 {
    // 1. 탑을 왼쪽부터 스택에 저장
    // 2. 스택에서 현재 탑의 높이보다 큰 탑이 나올때까지 pop
    // 3. 높은 탑을 발견하면 현재 탑의 레이저는 스택의 top에 저장된 탑에서 멈춤
    // 큰 탑 사이에 있는 작은 탑들은 무시할 수 있으므로 pop해도 된다
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 탑의 개수
        int n = Integer.parseInt(br.readLine());
        Stack<Tower> stack = new Stack<>();
        // 레이저가 도달한 탑의 위치
        int[] reachable = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 스택에서 현재 탑의 높이보다 큰 탑이 나올때 까지 pop하고 큰 탑을 만나면
        for(int i = 0; i < n; i++) {
            int height = Integer.parseInt(st.nextToken());

            while(!stack.isEmpty()) {
                // 현재 타워에서 출발한 레이저가 도달할 탑이 있으면
                if(stack.peek().height >= height) {
                    reachable[i] = stack.peek().order;
                    stack.push(new Tower(height,i+1));
                    break;
                    // 현재 타워에서 출발한 레이저가 이전 타워에 도달하지 않으면 pop해서 더 이전의 탑을 조사
                } else {
                    stack.pop();
                }
            }

            // 레이저가 아무타워에도 도달하지 못할 경우
            if(stack.isEmpty()) {
                stack.push(new Tower(height, i+1));
                reachable[i] = 0;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int reached : reachable) {
            sb.append(reached).append(" ");
        }
        System.out.println(sb);
    }

    static class Tower {
        int height;
        int order;

        Tower(int height, int order) {
            this.height = height;
            this.order = order;
        }
    }
}
