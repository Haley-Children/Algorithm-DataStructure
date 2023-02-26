import java.io.*;
import java.util.*;

public class Boj17143 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	// 격자판의 크기
    	int r = Integer.parseInt(st.nextToken());
    	int c = Integer.parseInt(st.nextToken());
    	// 상어의 수
    	int m = Integer.parseInt(st.nextToken());
    	
    	// 상어 데이터 (속력, 이동방향, 크기)
    	int[][] data = new int[m+1][5];
    	
    	// 죽은 상어 표시
    	boolean[] dead = new boolean[m+1];
    	
    	// 맵
    	int[][] map = new int[r][c];
    	
    	for (int i=1; i<=m; i++) {
    		st = new StringTokenizer(br.readLine());
    		// 맵에 상어 인덱스 저장
    		int row = Integer.parseInt(st.nextToken()) - 1;
    		int col = Integer.parseInt(st.nextToken()) - 1;
    		map[row][col] = i;
    		// x좌표, y좌표, 속력, 이동방향, 크기를 data에 저장
    		data[i][0] = row;
    		data[i][1] = col;
    		int s = Integer.parseInt(st.nextToken());
    		int d = Integer.parseInt(st.nextToken());
    		if (d == 1 || d == 2) {
    			s %= (2*r-2);
    		}else { // d == 3 or 4
    			s %= (2*c-2);
    		}
    		data[i][2] = s;
    		data[i][3] = d;
    		data[i][4] = Integer.parseInt(st.nextToken());
    	}
    	
    	// 잡은 상어 크기의 합
    	int ans = 0;
    	
    	// 시뮬레이션
    	for (int cur=0; cur<c; cur++) {
    		// 상어 잡기
    		// 땅에서 가까운 곳부터 탐색
    		for (int d=0; d<r; d++) {
    			// 상어가 있으면
    			if (map[d][cur] != 0) {
    				// 크기를 ans에 더해주고
    				ans += data[map[d][cur]][4];
    				// 상어를 죽임
    				dead[map[d][cur]] = true;
    				map[d][cur] = 0;
    				// 하나 잡았으면 for문 탈출
    				break;
    			}
    		}
    		
    		// 새 맵 만들기
    		int[][] newMap = new int[r][c];
    		
    		// 각 상어에 대해서 시뮬레이션
    		for (int i=1; i<=m; i++) {
    			// 상어가 안죽었으면
    			if (!dead[i]) {
    				// 상어가 이동할 새 인덱스
    				int nx = data[i][0];
    				int ny = data[i][1];
    				// 상어의 이동 방향
    				switch (data[i][3]) {
    					// 위
    					case 1:
    						nx -= data[i][2];
    						if (nx <= -r+1) {
    							nx += 2*r-2;
    						}else if (nx <= 0) {
    							nx = -nx;
    							data[i][3] = 2;
    						}
    						break;
    					// 아래
    					case 2:
    						nx += data[i][2];
    						if (nx >= 2*r-2) {
    							nx -= 2*r-2;
    						}else if (nx >= r-1) {
    							nx = 2*r-2 - nx;
    							data[i][3] = 1;
    						}
    						break;
						// 오른쪽
    					case 3:
    						ny += data[i][2];
    						if (ny >= 2*c-2) {
    							ny -= 2*c-2;
    						}else if (ny >= c-1) {
    							ny = 2*c-2 - ny;
    							data[i][3] = 4;
    						}
    						break;
						// 왼쪽
    					case 4:
    						ny -= data[i][2];
    						if (ny <= -c+1) {
    							ny += 2*c-2;
    						}else if (ny <= 0) {
    							ny = -ny;
    							data[i][3] = 3;
    						}
    						break;
						default:
							break;
    				}
    				// 상어의 새 위치 저장
    				data[i][0] = nx;
    				data[i][1] = ny;
    				
    				// 배열에 상어가 없으면 저장
    				if (newMap[nx][ny] == 0) {
    					newMap[nx][ny] = i;
    				}
    				// 배열에 상어가 있으면 작은 상어 죽이고 큰 상어 남기기
    				else {
    					dead[data[newMap[nx][ny]][4]<data[i][4]? newMap[nx][ny] : i] = true;
    					newMap[nx][ny] = data[newMap[nx][ny]][4]>data[i][4]? newMap[nx][ny] : i;
    				}
    			}
    		}
    		
    		// 다음 루프를 위해 newMap을 map에 할당
    		map = newMap;
    	}
    	
    	// 잡은 상어 크기의 합 출력 
    	System.out.println(ans);
    }
}