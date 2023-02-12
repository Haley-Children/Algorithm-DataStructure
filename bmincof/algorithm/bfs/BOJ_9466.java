import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9466 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder(100);

        for (int test = 0; test < t; test++) {
            int n = Integer.parseInt(br.readLine());

            // 조를 짜고 싶은 학생 번호, 들른 곳
            int[] wish = new int[n+1];
            boolean[] vis = new boolean[n+1];

            // 조를 짜지 못한 학생의 수
            int count = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                wish[i] = Integer.parseInt(st.nextToken());
            }

            // 각 시작점에서 현재 이어진 방향으로 이동하면서 큐에 추가하고 방문을 체크
            for (int student = 1; student <= n; student++) {
                // 이미 방문한 곳은 루프 체크를 했기 때문에 자기 자신과 루프가 될 수 없다
                if(vis[student]) continue;
                // 루프 검사를 하기 위한 큐
                int cur = student;
                Queue<Integer> q = new LinkedList<>();

                // 방문한 적이 있는 곳에 도달하기 전까지
                while(!vis[cur]) {
                    vis[cur] = true;
                    q.offer(cur);
                    cur = wish[cur];
                }
                // while문을 나왔을 때 cur는 루프의 시작점이다
                // 루프의 시작점 전에 있는 학생들은 조를 짜지 못한 학생
                while(!q.isEmpty() && q.poll() != cur) count++;
            }

            sb.append(count).append('\n');
        }

        System.out.println(sb);
    }
}

