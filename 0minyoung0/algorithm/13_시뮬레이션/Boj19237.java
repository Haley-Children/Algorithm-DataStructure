import java.io.*;
import java.util.*;
public class Boj19237 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 격자 크기
		int n = Integer.parseInt(st.nextToken());
		// 상어 수
		int m = Integer.parseInt(st.nextToken());
		// 냄새는 k턴 후 사라짐
		int k = Integer.parseInt(st.nextToken());
		
		// 상어의 위치, 초기 위치에 냄새 뿌리기
		int[][] data = new int[m+1][3];
		int[][][] smell = new int[n][n][2];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp != 0) {
					data[temp][0] = i;
					data[temp][1] = j;
					smell[i][j][0] = temp;
					smell[i][j][1] = k;
				}
			}
		}
		
		// 상어의 방향
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=m; i++) {
			data[i][2] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		// 상어의 방향에 따른 우선순위
		int[][][] favor = new int[m+1][4][4];
		for (int i=1; i<=m; i++) {
			for (int j=0; j<4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int l=0; l<4; l++) {
					favor[i][j][l] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
		}
		
		// 남은 상어의 수를 세기 위한 변수
		int sharkCnt = m;
		
		// 1000초동안 시뮬레이션 진행
		for (int t=1; t<=1000; t++) {
			// 상어가 이동 할 위치를 저장할 맵 선언
			int[][] map = new int[n][n];
			
			// 1번상어부터 순차적으로 이동하며 이미 다른 상어가 도착했으면 쫓겨남
			// 쫓겨난 상어는 x좌표를 -1로 함
			for (int i=1; i<=m; i++) {
				if (data[i][0] != -1) {
					int curX = data[i][0];
					int curY = data[i][1];
					int curD = data[i][2];
					
					// 이동하면 true로 바꿈
					boolean move = false;
					
					// 냄새가 없는 칸 중 우선 순위가 높은 칸
					for (int dir=0; dir<4; dir++) {
						int nx = curX + dx[favor[i][curD][dir]];
						int ny = curY + dy[favor[i][curD][dir]];
						// out ot index
						if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
						// 냄새가 있는 칸
						if (smell[nx][ny][0] != 0) continue;
						
						// 냄새가 없는 칸 중에 우선순위가 높아 먼저 도달한 칸
						move = true;
						// 먼저온 상어가 있다면 쫓겨남
						if (map[nx][ny] != 0) {
							data[i][0] = -1;
							// 상어가 1마리 남으면 출력 후 리턴
							if (--sharkCnt == 1) {
								System.out.println(t);
								return;
							}
						}
						// 그게 아니면 맵과 데이터에 저장
						else {
							map[nx][ny] = i;
							data[i][0] = nx;
							data[i][1] = ny;
							data[i][2] = favor[i][curD][dir];
						}
						break;
					}
					
					// 위에서 이동 못했다면
					if (!move) {
						// 자기 냄새가 있는 칸 중 우선 순위가 높은 칸
						for (int dir=0; dir<4; dir++) {
							int nx = curX + dx[favor[i][curD][dir]];
							int ny = curY + dy[favor[i][curD][dir]];
							// out ot index
							if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
							// 자기 냄새가 아닌 칸
							if (smell[nx][ny][0] != i) continue;
							
							// 자기 냄새가 있는 칸 중에 우선순위가 높아 먼저 도달한 칸
							move = true;
							// 먼저온 상어가 있다면 쫓겨남
							if (map[nx][ny] != 0) {
								data[i][0] = -1;
								// 상어가 1마리 남으면 출력 후 리턴
								if (--sharkCnt == 1) {
									System.out.println(t);
									return;
								}
							}
							// 그게 아니면 맵과 데이터에 저장
							else {
								map[nx][ny] = i;
								data[i][0] = nx;
								data[i][1] = ny;
								data[i][2] = favor[i][curD][dir];
							}
							break;
						}
					}
				}
			}
			
			// 이동이 모두 끝났다면 기존 냄새들을 -1
			for (int i=0; i<n; i++) {
				for (int j=0; j<n; j++) {
					// 냄새가 없으면 스킵
					if (smell[i][j][0] == 0) continue;
					// 냄새가 0만큼 남았으면 상어 번호 제거
					if (--smell[i][j][1] == 0) {
						smell[i][j][0] = 0;
					}
				}
			}
			
			// 이후 각 상어들의 위치에 새 냄새 생성
			for (int i=0; i<n; i++) {
				for (int j=0; j<n; j++) {
					// 맵에 상어가 있으면 새 냄새 생성
					if (map[i][j] != 0) {
						smell[i][j][0] = map[i][j];
						smell[i][j][1] = k;
					}
				}
			}
		}
		
		// 1000초간의 시뮬레이션에서 리턴되지 않았다면 -1 출력
		System.out.println(-1);
	}
}
