package priorityqueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1781 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 숙제의 개수, 마감일
        int n = Integer.parseInt(br.readLine());

        // 데드라인 순으로 정렬
        PriorityQueue<int[]> homeworks =
                new PriorityQueue<>((e1, e2) -> e1[0] - e2[0]);

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int deadline = Integer.parseInt(st.nextToken());
            int reward = Integer.parseInt(st.nextToken());

            homeworks.add(new int[] {deadline, reward});
        }

        // day일 까지 선택한 숙제들, day개 만큼을 가지고 있을 수 있다.
        PriorityQueue<Integer> selected = new PriorityQueue<>();

        for(int day = 1; day <= n; day++) {
            if(homeworks.peek()[0] == day) {
                while(!homeworks.isEmpty() && homeworks.peek()[0] == day) {
                    selected.offer(homeworks.poll()[1]);
                }
            }

            // day일 까지 선택할 수 있는 숙제중 보상 좋은것 제외 다 버리기
            while(selected.size() > day) {
                selected.poll();
            }

            if(homeworks.isEmpty()) break;
        }

        int count = 0;
        while(!selected.isEmpty()) count += selected.poll();

        System.out.println(count);
    }
}
