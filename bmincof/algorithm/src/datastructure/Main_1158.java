package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1158 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder().append("<");

        // 1. 큐에 1 부터 N까지 enqueue
        // 2. 큐가 빌 때 까지  K번째 마다 dequeue
        // 2. K번 째가 아니면 큐에 맨 앞에 요소를 enqueue
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= n; i++) {
            q.offer(i);
        }

        // 이전 제거된 사람으로부터 몇 번 인지
        int cnt = 1;
        while(!q.isEmpty()) {
            // k번째 사람을 만났을 때
            if(cnt / k == 1) {
                sb.append(q.poll()).append(", ");
                cnt = 1;
                continue;
            }

            // 지금 맨 앞의 사람을 맨 뒤로 보낸다.
            q.offer(q.poll());
            cnt++;
        }
        sb.replace(sb.length()-2, sb.length(), ">");
        System.out.println(sb);
    }

}
