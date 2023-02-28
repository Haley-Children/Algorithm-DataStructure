import java.io.*;
import java.util.*;

public class Boj17837 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 체스판의 크기
        int n = Integer.parseInt(st.nextToken());
        // 말의 개수
        int k = Integer.parseInt(st.nextToken());
        
        // 체스판의 정보
        boolean[][] red = new boolean[n+1][n+1];
        boolean[][] blue = new boolean[n+1][n+1];
        List<Integer>[][] map = new ArrayList[n+1][n+1];
        for (int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=n; j++) {
                int m = Integer.parseInt(st.nextToken());
                if (m == 1) {
                    red[i][j] = true;
                }else if (m == 2) {
                    blue[i][j] = true;
                }
                map[i][j] = new ArrayList<>();
            }
        }
        
        // 말의 정보
        // 0 - 행번호, 1 - 열번호, 2 - 이동방향, 3 - 높이정보
        int[][] data = new int[k+1][4];
        for (int i=1; i<=k; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<3; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
            map[data[i][0]][data[i][1]].add(i);
        }
        
        // 1000턴이 지나는 동안 한번도 말이 4개 이상 쌓이지 않으면 -1 출력
        int ans = -1;
        
        // 델타배열
        int[] dx = {0,0,0,-1,1};
        int[] dy = {0,1,-1,0,0};
        
        // 시뮬레이션
        boolean terminated = false;
        for (int t=1; t<=1000; t++) {
            for (int i=1; i<=k; i++) {
            	// 말이 원래 있던 좌표
            	int x = data[i][0];
            	int y = data[i][1];
            	
            	// 이동할 좌표 계산
            	int nx = x + dx[data[i][2]];
            	int ny = y + dy[data[i][2]];
            	// out of index거나 blue
            	if (nx<=0 || nx>n || ny<=0 || ny>n || blue[nx][ny]) {
            		if (data[i][2] <= 2) {
            			data[i][2] = 3 - data[i][2];
            		}
            		else {
            			data[i][2] = 7 - data[i][2];
            		}
            		// 방향을 바꾸고 이동할 좌표 다시 계산
            		nx = x + dx[data[i][2]];
            		ny = y + dy[data[i][2]];
            		// 또 out of index거나 blue
            		if (nx<=0 || nx>n || ny<=0 || ny>n || blue[nx][ny]) {
            			// 이동 안함
            			continue;
            		}
            	}
            	
            	// 옮길 말의 높이
            	int h = data[i][3];
            	// 옮길 말이 있는 곳의 최대 높이
            	int H = map[x][y].size() - 1;
            	
            	// red로 이동하는 경우
            	if (red[nx][ny]) {
            		for (int j=H; j>=h; j--) {
            			// 말을 기존 위치에서 제거
            			int num = map[x][y].remove(j);
            			// 말을 새 위치에 추가
            			map[nx][ny].add(num);
            			// 말 정보 바꾸기
            			data[num][0] = nx;
            			data[num][1] = ny;
            			data[num][3] = map[nx][ny].size() - 1;
            		}
            	}
            	// 흰칸으로 이동하는 경우
            	else {
            		for (int j=h; j<=H; j++) {
            			// 말을 기존 위치에서 제거
            			int num = map[x][y].remove(h);
            			// 말을 새 위치에 추가
            			map[nx][ny].add(num);
            			// 말 정보 바꾸기
            			data[num][0] = nx;
            			data[num][1] = ny;
            			data[num][3] = map[nx][ny].size() - 1;
            		}
            	}
            	
            	// 말을 옮긴 좌표에 말이 4개 이상인지 확인
            	if (map[nx][ny].size() >= 4) {
            		terminated = true;
            		ans = t;
            		break;
            	}
            }
            if (terminated) break;
        }
        
        // 답 출력
        System.out.println(ans);
    }
}