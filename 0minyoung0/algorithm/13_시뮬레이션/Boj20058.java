import java.io.*;
import java.util.*;

public class Boj20058 {
	static int N;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 격자의 크기 : 2^n = N
		int n = Integer.parseInt(st.nextToken());
		N = 1;
		for (int i=0; i<n; i++) {
			N *= 2;
		}
		// 파이어스톰 시전 횟수
		int q = Integer.parseInt(st.nextToken());
		
		// 격자의 얼음 정보
		int[][] arr = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 파이어스톰 q번 시전
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<q; i++) {
			// 파이어스톰 단계 l, 격자를 2^l = L 사이즈로 나눔
			int l = Integer.parseInt(st.nextToken());
			int L = 1;
			for (int j=0; j<l; j++) {
				L *= 2;
			}
			
			// 부분 격자 돌리기
			if (L != 1) {
				arr = turn(arr, L);
			}
			
			// 인접한 칸이 2이하인 좌표는 boolean[][]에 저장
			boolean[][] check = new boolean[N][N];
			for (int x=0; x<N; x++) {
				for (int y=0; y<N; y++) {
					// 얼음이 있는 좌표에 대해서
					if (arr[x][y] != 0) {
						int c = 0;
						for (int dir=0; dir<4; dir++) {
							int nx = x + dx[dir];
							int ny = y + dy[dir];
							// out of index
							if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
							// 얼음이 없는 경우
							if (arr[nx][ny] == 0) continue;
							// 인접한 얼음 개수 세기
							c++;
						}
						if (c <= 2) check[x][y] = true;
					}
				}
			}
			
			// 체크된 좌표는 얼음 감소
			for (int x=0; x<N; x++) {
				for (int y=0; y<N; y++) {
					if (check[x][y]) arr[x][y]--;
				}
			}
		}
		
		// 남아있는 얼음의 합 출력
		int iceSum = 0;
		for (int x=0; x<N; x++) {
			for (int y=0; y<N; y++) {
				iceSum += arr[x][y];
			}
		}
		System.out.println(iceSum);
		
		// 가장 큰 덩어리가 차지하는 칸의 개수 출력
		int ans = 0;
		boolean[][] vis = new boolean[N][N];
		Queue<int[]> queue = new ArrayDeque<>();
		for (int x=0; x<N; x++) {
			for (int y=0; y<N; y++) {
				// 방문한적 없는 얼음에 대해서 BFS
				if (arr[x][y] != 0 && !vis[x][y]) {
					queue.offer(new int[] {x,y});
					vis[x][y] = true;
					int cnt = 1;
					while (!queue.isEmpty()) {
						int[] cur = queue.poll();
						for (int dir=0; dir<4; dir++) {
							int nx = cur[0] + dx[dir];
							int ny = cur[1] + dy[dir];
							// out of index
							if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
							// 얼음이 없거나 방문한 경우
							if (arr[nx][ny] == 0 || vis[nx][ny]) continue;
							// 큐에 추가하면서 cnt 추가
							queue.offer(new int[] {nx,ny});
							vis[nx][ny] = true;
							cnt++;
						}
					}
					if (cnt > ans) ans = cnt;
				}
			}
		}
		System.out.println(ans);
	}
	private static int[][] turn(int[][] arr, int L) {
		int[][] newArr = new int[N][N];
		for (int i=0; i<N; i+=L) {
			for (int j=0; j<N; j+=L) {
				for (int x=0; x<L; x++) {
					for (int y=0; y<L; y++) {
						newArr[i+y][j+L-1-x] = arr[i+x][j+y];
					}
				}
			}
		}
		return newArr;
	}
}
