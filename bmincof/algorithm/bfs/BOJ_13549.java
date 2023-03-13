import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13549 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new ArrayDeque<>(100010);
        int[] time = new int[100010];
        int[] walk = {1,-1};

        // 시작점을 큐에 넣기
        time[start] = 1;
        q.offer(start);

        // 목표에 도착할 때 까지
        while(!q.isEmpty() && start != end) {
            start = q.poll();
            int tmp = start * 2;

            // 순간이동
            while (tmp <= 100000 && tmp > 0) {
                if(time[tmp] == 0) {
                    time[tmp] = time[start];
                    q.offer(tmp);
                }
                tmp *= 2;
            }

            // 걸어서 이동
            for (int dir = 0; dir < 2; dir++) {
                tmp = start + walk[dir];
                // 이동해서 0 ~ 100000 사이의 위치일 때만
                if(0<= tmp && tmp <= 100000 && time[tmp] == 0) {
                    time[tmp] = time[start] + 1;
                    q.offer(tmp);
                }
            }
        }

        // 시작을 1초로 생각했으므로
        System.out.println(time[end] - 1);
    }
}

