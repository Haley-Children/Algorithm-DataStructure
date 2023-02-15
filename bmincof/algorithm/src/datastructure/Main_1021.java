package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1021 {
    // 1. 목표하는 숫자가 나올 때까지 한 쪽 방향으로만 회전 시킨다. (연산 2 or 연산 3)
    // 2. 이동한 횟수와 (큐의 크기 - 이동횟수) 중 더 작은 값을 더한다.
    // 3. m번 반복
    // 최대 O(50^2 / 2)
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();
        // 1 부터 n까지 큐에 넣기 (초기화)
        for(int i = 1; i <= n; i++) {
            q.offer(i);
        }
        // 2번 연산만 하면서 해당 숫자를 만날 때까지 필요한 이동횟수를 카운트
        // 2번 3번 연산을 가장 적게 사용했을 때 횟수
        int totalOper = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            int toFind = Integer.parseInt(st.nextToken());
            // 원하는 값을 찾을 때 까지 2번 연산을 반복한다
            // 연산횟수를 세는 move 변수
            int move = 0;
            while(!q.isEmpty() && q.peek() != toFind) {
                move++;
                q.offer(q.poll());
            }
            // 2번 연산과 3번 연산 중 더 적은 횟수를 선택
            move = Math.min(move, q.size() - move);
            // 찾은 값을 빼낸다.
            q.poll();
            totalOper += move;
        }
        System.out.println(totalOper);
    }
}
