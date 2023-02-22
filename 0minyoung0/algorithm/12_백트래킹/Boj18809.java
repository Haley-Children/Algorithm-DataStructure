import java.io.*;
import java.util.*;

public class Boj18809 {
	static int n,m,g,r,pCnt,ans;
	static char[][] map;
	static int[][] pArr;
	static int[] gArr;
	static int[] rArr;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 정원의 행의 개수, 열의 개수
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		// 초록색 배양액의 개수, 빨간색 배양액의 개수
		g = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		// 전체 맵 정보
		map = new char[n][m];
		// 배양액을 뿌릴 수 있는 땅
		pArr = new int[10][2];
		// pArr의 땅을 사용했는지 여부를 남기기 위한 boolean 배열
		boolean[] vis = new boolean[10];
		// 초록색 배양액을 뿌린 땅의 pArr 인덱스
		gArr = new int[g];
		// 빨간색 배양액을 뿌린 땅의 pArr 인덱스
		rArr = new int[r];
		
		// 맵과 배양액을 뿌릴 수 있는 땅 정보 저장
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<m; j++) {
				// 맵 정보 저장
				map[i][j] = st.nextToken().charAt(0);
				// 배양액을 뿌릴 수 있는 땅이면 pArr에 좌표를 따로 저장
				if (map[i][j] == '2') {
					pArr[pCnt++] = new int[] {i, j};
				}
			}
		}
		
		// 배양액 뿌리고 꽃의 개수를 계산하는 백트래킹 함수
		func(0, -1, vis);
		
		System.out.println(ans);
	}
	
	private static void func(int k, int pre, boolean[] vis) {
		// 배양액을 다 뿌렸다면 BFS로 꽃의 개수 계산 후 리턴
		if (k == g + r) {
			// BFS를 위한 Queue 선언
			// Queue에 들어가는 노드는 (x좌표, y좌표)
			Queue<int[]> q = new ArrayDeque<>();
			
			// BFS를 위한 map 복사
			char[][] newMap = new char[n][m];
			for (int i=0; i<n; i++) {
				for (int j=0; j<m; j++) {
					newMap[i][j] = map[i][j];
				}
			}
			
			// BFS을 위한 time 배열
			int[][] time = new int[n][m];
			
			// gArr와 rArr에 남겨진 좌표들을 큐에 넣기
			for (int i=0; i<gArr.length; i++) {
				q.add(new int[] {pArr[gArr[i]][0], pArr[gArr[i]][1]});
				// 초록색 배양액은 맵에 3으로 표시
				newMap[pArr[gArr[i]][0]][pArr[gArr[i]][1]] = '3';
				time[pArr[gArr[i]][0]][pArr[gArr[i]][1]] = 1;
			}
			for (int i=0; i<rArr.length; i++) {
				q.add(new int[] {pArr[rArr[i]][0], pArr[rArr[i]][1]});
				// 빨간색 배양액은 맵에 4로 표시
				newMap[pArr[rArr[i]][0]][pArr[rArr[i]][1]] = '4';
				time[pArr[rArr[i]][0]][pArr[rArr[i]][1]] = 1;
			}
			
			// 꽃 개수를 저장하기 위한 int 변수 선언
			int flowers = 0;
			
			// BFS 시작
			while (!q.isEmpty()) {
				int[] cur = q.poll();
				// cur가 이미 꽃이 피어난 좌표라면 continue
				if (newMap[cur[0]][cur[1]] == '5') continue;
				
				for (int dir=0; dir<4; dir++) {
					int nx = cur[0] + dx[dir];
					int ny = cur[1] + dy[dir];
					
					// out of index -> continue;
					if (nx<0 || nx>=n || ny<0 || ny>=m) continue;
					
					// 땅이 아닌 경우, 꽃이 이미 핀 경우 -> continue;
					if (newMap[nx][ny] == '0' || newMap[nx][ny] == '5') continue;
					
					// 배양액이 이미 뿌려진 경우
					if (newMap[nx][ny] == '3' || newMap[nx][ny] == '4') {
						// 지금 뿌리는 배양액과 기존에 뿌려진 배양액이 다르며
						// 시간이 같으면 꽃 만들기 -> flower개수 +1, map에 '5'로 표시
						if (newMap[cur[0]][cur[1]] != newMap[nx][ny]
							&& time[cur[0]][cur[1]] + 1 == time[nx][ny]) {
							flowers++;
							newMap[nx][ny] = '5';
						}
						continue;
					}
					
					// 배양액 뿌리기
					q.add(new int[] {nx, ny});
					newMap[nx][ny] = newMap[cur[0]][cur[1]];
					time[nx][ny] = time[cur[0]][cur[1]] + 1;
				}
			}
			
			ans = Math.max(ans, flowers);
			return;
		}
		
		// 배양액을 덜 뿌렸다면
		// 초록색을 덜 뿌렸으면 초록색 뿌리기
		if (k < g) {
			for (int i=pre+1; i<=pCnt-g+k; i++) {
				// vis배열을 인자로 넘겨주기 위해서 복사
				boolean[] newVis = new boolean[pCnt];
				for (int j=0; j<pCnt; j++) {
					newVis[j] = vis[j];
				}
				
				// gArr에 초록색 배양액을 뿌린 땅의 pArr 인덱스를 저장
				gArr[k] = i;
				// i번 영역을 사용했다고 표시하기
				newVis[i] = true;
				
				// 다음 재귀 호출
				// 초록색을 다 뿌렸으면 pre 초기화
				if (k + 1 == g) {
					func(k+1, -1, newVis);
				}
				// 초록색 다 뿌린게 아니면 pre를 i로 설정
				else {
					func(k+1, i, newVis);
				}
			}
		}
		// 초록색을 다 뿌렸으면 빨간색 뿌리기
		else { // k >= g
			for (int i=pre+1; i<=pCnt-g-r+k; i++) {
				// 이미 배양액을 뿌린적 있는 땅이라면
				if (vis[i] == true) continue;
				
				// vis배열을 인자로 넘겨주기 위해서 복사
				boolean[] newVis = new boolean[pCnt];
				for (int j=0; j<pCnt; j++) {
					newVis[j] = vis[j];
				}
				
				// rArr에 빨간색 배양액을 뿌린 땅의 pArr 인덱스를 저장
				rArr[k-g] = i;
				// i번 영역을 사용했다고 표시하기
				newVis[i] = true;
				
				// 다음 재귀 호출
				func(k+1, i, newVis);
			}
		}
	}
}
