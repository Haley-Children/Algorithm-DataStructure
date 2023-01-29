// 시뮬레이션.boj13460;

import java.io.*;
import java.util.*;

public class Boj13460 {
	static char[][] map;
	static int n,m,ans; // 가로, 세로, 답
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int[] oPoint = new int[2];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        int[] rPoint = new int[2];
        int[] bPoint = new int[2];
        for (int i=0; i<n; i++) {
        	String s = br.readLine();
        	for (int j=0; j<m; j++) {
        		map[i][j] = s.charAt(j);
        		if (map[i][j] == 'O') {
        			oPoint[0] = i;
        			oPoint[1] = j;
        		}
        		else if (map[i][j] == 'R') {
        			rPoint[0] = i;
        			rPoint[1] = j;
        		}
        		else if (map[i][j] == 'B') {
        			bPoint[0] = i;
        			bPoint[1] = j;
        		}
        	}
        }
        ans = 11;
        func(0,-1,rPoint,bPoint);
        if (ans == 11) ans = -1;
        System.out.println(ans);
    }
    private static void func(int k, int predir, int[] rArr, int[] bArr) {
    	if (k==10) {
    		return;
    	}
    	for (int dir=0; dir<4; dir++) {
    		if (dir!=predir) {
	    		int rx = rArr[0];
	    		int ry = rArr[1];
	    		int bx = bArr[0];
	    		int by = bArr[1];
	    		boolean success = false;
	    		boolean fail = false;
	    		// 멈추거나 구멍 도착할때까지 굴리기
	    		while((!success && map[rx+dx[dir]][ry+dy[dir]] != '#' && !(rx+dx[dir] == bx && ry+dy[dir] == by))
	    			|| (!fail && map[bx+dx[dir]][by+dy[dir]] != '#' && !(bx+dx[dir] == rx && by+dy[dir] == ry))) {
	    			if(!success && map[rx+dx[dir]][ry+dy[dir]]=='O') {
	    				rx = -1;
	    				ry = -1;
	    				success = true;
	    			}
	    			if(!fail && map[bx+dx[dir]][by+dy[dir]]=='O') {
	    				bx = -1;
	    				by = -1;
	    				fail = true;
	    				break;
	    			}
	    			if (!success && map[rx+dx[dir]][ry+dy[dir]] != '#' && !(rx+dx[dir] == bx && ry+dy[dir] == by)) {
	    				rx += dx[dir];
	        			ry += dy[dir];
	    			}
	    			if (!fail && map[bx+dx[dir]][by+dy[dir]] != '#' && !(bx+dx[dir] == rx && by+dy[dir] == ry)) {
	    				bx += dx[dir];
	        			by += dy[dir];
	    			}
	    		}
	    		if (fail) continue;
	    		else if (success) {
	    			if (ans > k+1) ans = k+1;
	    			continue;
	    		}
	    		// 다음 단계 호출
	    		func(k+1, dir, new int[] {rx,ry}, new int[] {bx,by});
    		}
    	}
    }
}