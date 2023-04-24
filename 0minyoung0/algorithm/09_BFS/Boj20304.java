import java.io.*;
import java.util.*;

public class Boj20304 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 관리자 계정 비밀번호의 최댓값
		int N = Integer.parseInt(br.readLine());
		
		// 로그인 시도에 사용된 비밀번호의 개수
		int M = Integer.parseInt(br.readLine());
		
		// BFS를 위한 안전거리 배열과 큐
		int[] safeDis = new int[N+1];
		Arrays.fill(safeDis, -1);
		Queue<Integer> q = new ArrayDeque<>();
		
		// 로그인 시도에 사용된 비밀번호 값
		st = new StringTokenizer(br.readLine());
		int[] p = new int[M+1];
		for (int i=1; i<=M; i++) {
			p[i] = Integer.parseInt(st.nextToken());
			safeDis[p[i]] = 0;
			q.offer(p[i]);
		}
		
		// 최대 안전도
		int ans = 0;
		
		// 안전도가 0인 (로그인 시도에 사용된 비밀번호) 값부터 BFS해서 계정 비밀번호 구하기
		while (!q.isEmpty()) {
			
			// 큐에서 하나 꺼내서 최대 안전도보다 안전도가 높으면 갱신
			int cur = q.poll();
			if (ans < safeDis[cur]) ans = safeDis[cur];
			
			// cur에서 비트 하나 바꿔서 안전도 1 올릴 수 있는 후보 찾기
			for (int i=1; i<=N; i<<=1) {
				int nx = cur ^ i;
				
				// out of index
				if (nx > N) continue;
				
				// 안전 거리를 이미 계산한 값인 경우
				if (safeDis[nx] >= 0) continue;
				
				// 안전 거리 계산하고 큐에 넣기
				safeDis[nx] = safeDis[cur] + 1;
				q.offer(nx);
			}
		}
		
		// 큐가 비었으면 최대 안전도 출력
		System.out.println(ans);
	}
}
