import java.io.*;
import java.util.*;

public class Boj17071 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 수빈이가 있는 위치
		int N = Integer.parseInt(st.nextToken());
		// 동생이 있는 위치
		int K = Integer.parseInt(st.nextToken());
		
		// 같은 위치에 있으면 조기 종료
		if (N == K) {
			System.out.println(0);
			return;
		}
		
		// 현재 시각을 나타낼 변수
		int time = 0;
		
		// BFS를 위한 큐
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(N);
		
		// 방문 체크를 위한 boolean 배열 [좌표][짝/홀]
		boolean[][] vis = new boolean[500001][2];
		vis[N][0] = true;
		
		// BFS 탐색
		while (!q.isEmpty()) {
			
			// 현재 시간에서 만났는지 확인
			if (vis[K][time%2]) {
				System.out.println(time);
				return;
			}
			
			// 시간 증가 시키기
			time++;
			
			// 동생 이동 시키기
			K += time;
			if (K > 500000) break;
			
			// 현재 큐의 크기만큼만 BFS 돌리기
			int size = q.size();
			while (size-- > 0) {
				int cur = q.poll();
				
				// -1, +1, *2 만큼 진행하기
				for (int dir=0; dir<3; dir++) {
					int nx = next(cur, dir);
					
					// 범위 밖인 경우
					if (nx < 0 || nx > 500000) continue;
					
					// 이미 방문한 경우
					if (vis[nx][time%2]) continue;
					
					// 처음 방문한 경우
					q.offer(nx);
					vis[nx][time%2] = true;
				}
			}
		}
		
		// 못만났으면 -1 출력
		System.out.println(-1);
	}
	
	// cur 위치에서 -1, +1, *2 만큼 진행 시킨 좌표 계산하는 메서드
	private static int next(int cur, int dir) {
		if (dir == 0) return cur - 1;
		else if (dir == 1) return cur + 1;
		else return cur * 2;
	}
}
