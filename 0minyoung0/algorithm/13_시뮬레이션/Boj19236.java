import java.io.*;
import java.util.*;

public class Boj19236 {
	static int ans, temp;
	static int[] dx = {-1,-1,0,1,1,1,0,-1};
	static int[] dy = {0,-1,-1,-1,0,1,1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        // 공간에 있는 물고기(1~16) or 상어의 번호(17)
        int[][] map = new int[4][4];
        
        // 물고기(1~16)와 상어(17)의 위치, 방향 정보
        // 위방향이 0, 시계반대방향으로 돌면서 1씩 증가
        int[][] data = new int[18][3];
        
        // 물고기 정보 입력
        for (int i=0; i<4; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j=0; j<4; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        		data[map[i][j]][0] = i;
        		data[map[i][j]][1] = j;
        		data[map[i][j]][2] = Integer.parseInt(st.nextToken()) - 1;
        	}
        }
        
        // 시뮬레이션 실시
        simulation(0,0,0,map,data);
        
        // 답 출력
        System.out.println(ans);
    }
    
    private static void simulation(int eat, int curX, int curY, int[][] preMap, int[][] preData) {
    	// 이번 분기에서 먹을 물고기 번호
    	int target = preMap[curX][curY];
    	
    	// 새로운 맵 만들기
    	int[][] map = new int[4][4];
    	for (int i=0; i<4; i++) {
    		for (int j=0; j<4; j++) {
    			map[i][j] = preMap[i][j];
    		}
    	}
    	
    	// 새로운 데이터 배열 만들기
    	int[][] data = new int[18][3];
    	for (int i=0; i<18; i++) {
    		for (int j=0; j<3; j++) {
    			data[i][j] = preData[i][j];
    		}
    	}
    	
    	// curX, curY 위치의 물고기 먹기
    	temp += target;
    	
    	// 다먹었으면 계산 후 바로 리턴
    	if (eat == 15) {
    		ans = ans > temp? ans : temp;
    		// 물고기 뱉어내고 리턴
    		temp -= target;
    		return;
    	}
    	
    	// curX, curY 위치의 물고기 제거 (x좌표를 -1로 처리)
    	data[map[curX][curY]][0] = -1;
    	
    	// 방향 계승받고 상어를 cur 위치로 옮기기
    	data[17][2] = data[map[curX][curY]][2];
    	map[data[17][0]][data[17][1]] = 0;
    	data[17][0] = curX;
    	data[17][1] = curY;
    	map[curX][curY] = 17;
    	
    	// 물고기 이동시키기
    	for (int fish=1; fish<=16; fish++) {
    		// 물고기 안죽었으면 이동시키기
    		if (data[fish][0] != -1) {
    			int cx = data[fish][0];
    			int cy = data[fish][1];
    			for (int dir=data[fish][2]; dir<data[fish][2]+8; dir++) {
    				int nx = cx + dx[dir%8];
    				int ny = cy + dy[dir%8];
    				// 인덱스를 넘어가거나 상어가 있다면 continue
    				if (nx < 0 || nx >=4 || ny < 0 || ny >= 4) continue;
    				if (map[nx][ny] == 17) continue;
    				// 빈칸이거나 다른 물고기가 있다면
    				// cur 좌표에 대입하고 방향을 dir로 변경 후 break
    				cx = nx;
    				cy = ny;
    				data[fish][2] = dir;
    				break;
    			}
    			// cx, cy중 하나라도 바뀌었으면 이동 가능, 이동시키기
    			if (cx != data[fish][0] || cy != data[fish][1]) {
    				// cx, cy 위치의 물고기를 지금 물고기 위치로 옮기기
    				// (빈칸이어도 그냥 실행시킴)
    				data[map[cx][cy]][0] = data[fish][0];
    				data[map[cx][cy]][1] = data[fish][1];
    				map[data[fish][0]][data[fish][1]] = map[cx][cy];
    				// 지금 물고기를 cx, cy로 옮기기
    				data[fish][0] = cx;
    				data[fish][1] = cy;
    				map[cx][cy] = fish;
    			}
    		}
    	}
    	
    	// 상어가 먹을 수 있는 물고기를 판단해서 분기 나누기
    	boolean edible = false;
    	
    	// 상어의 위치와 방향
    	int sX = data[17][0];
    	int sY = data[17][1];
    	int sD = data[17][2];
    	
    	for (int dis=1; dis<=3; dis++) {
    		int nx = sX + dis * dx[sD%8];
    		int ny = sY + dis * dy[sD%8];
    		// 범위를 벗어나거나 물고기가 없으면 못먹음
    		if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;
    		if (map[nx][ny] == 0) continue;
    		// 먹을 수 있으면 재귀호출 후 edible을 true로 변경
    		simulation(eat+1, nx, ny, map, data);
    		edible = true;
    	}
    	
    	// 위의 모든 경우에서 먹을 수 없을 때
    	if (!edible) {
    		// ans 계산
    		ans = ans > temp? ans : temp;
    	}    	
    	
    	// 재귀 탈출 전에 temp를 감소시키기
    	temp -= target;
    }
}