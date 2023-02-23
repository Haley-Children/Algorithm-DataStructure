import java.io.*;
import java.util.*;

public class Boj15685 {
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,-1,0,1};
	static boolean[][] map = new boolean[101][101];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        
        for (int i=0; i<n; i++) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	int d = Integer.parseInt(st.nextToken());
        	int g = Integer.parseInt(st.nextToken());
        	makeDragonCurve(x,y,d,g);
        }
        
        int ans = 0;
        for (int i=0; i<100; i++) {
        	for (int j=0; j<100; j++) {
        		if (map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]) ans++;
        	}
        }
        System.out.println(ans);
    }
    // 드래곤 커브를 만들고 커브에 포함된 점들을 map boolean배열에 입력하는 함수
    private static void makeDragonCurve(int x, int y, int d, int g) {
    	// g 세대의 드래곤 커브가 가지는 점의 개수 = 2^g+1
    	int numPoint = 1;
    	for (int i=0; i<g; i++) {
    		numPoint *= 2;
    	}
    	numPoint += 1;
    	// 드래곤 커브의 점 좌표를 저장할 배열
    	int[][] c = new int[numPoint][2];
    	// 0세대는 직접 입력
    	c[0] = new int[] {x,y};
    	c[1] = new int[] {x+dx[d], y+dy[d]};
    	// 1세대 이상일 경우 추가 탐색
    	for (int i=0; i<g; i++) {
    		// i+1번째 세대를 만들기위한 pivot 결정
    		// i가 0일때 1, 1일때 2, 2일때 4,...
    		int pivotNum = 1;
    		for (int j=0; j<i; j++) {
    			pivotNum *= 2;
    		}
    		int[] pivot = c[pivotNum];
    		for (int j=1; j<=pivotNum; j++) {
    			// new x - pivot x = -(y - pivot y)
    			// new y - pivot y = x - pivot x (90도 회전)
    			c[pivotNum+j][0] = -c[pivotNum-j][1] + pivot[0] + pivot[1];
    			c[pivotNum+j][1] = c[pivotNum-j][0] - pivot[0] + pivot[1];
    		}
    	}
    	// 드래곤 커브의 모든 점을 map에 입력
    	for (int i=0; i<numPoint; i++) {
    		map[c[i][0]][c[i][1]] = true;
    	}
    }
}