// 시뮬레이션.boj3190;

import java.io.*;
import java.util.*;

public class Boj3190 {
	// 링크드리스트 하나에 x,y 좌표가 들어가도록 뱀의 좌표정보를 저장
	static int[][] map;
	static int n,k,l,d;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static LinkedList<int[]> snake = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        k = Integer.parseInt(br.readLine());
        for (int i=0; i<k; i++) { // 사과는 맵에 1로 저장
        	st = new StringTokenizer(br.readLine());
        	int temp1 = Integer.parseInt(st.nextToken());
        	int temp2 = Integer.parseInt(st.nextToken());
        	map[temp1-1][temp2-1] = 1; // 첫줄이 1행, 1열인거 고려
        }
        l = Integer.parseInt(br.readLine());
        int[] temp1 = new int[l+1];
        char[] temp2 = new char[l+1];
        for (int i=0; i<l; i++) {
        	st = new StringTokenizer(br.readLine());
        	temp1[i] = Integer.parseInt(st.nextToken());
        	temp2[i] = st.nextToken().charAt(0);
        }
        
        snake.add(new int[] {0,0});
        map[0][0] = 2; // 뱀의 몸이 존재하는 칸은 2로 표시
        int time = 0;
        int lIndex = 0;
        boolean gameOver = false;
        
        while (!gameOver) {
        	time++;
        	int[] curHead = snake.peekLast();
        	int nx = curHead[0] + dx[d];
        	int ny = curHead[1] + dy[d];
        	if (nx<0||nx>=n||ny<0||ny>=n) break; // 벽에 부딪히면 종료
        	if (map[nx][ny] == 2) break; // 몸에 부딪히면 종료
        	snake.add(new int[] {nx,ny});
        	if (map[nx][ny] != 1) {
        		int[] tail = snake.poll();
        		map[tail[0]][tail[1]] = 0;
        	}
        	map[nx][ny] = 2;
        	
        	if (time == temp1[lIndex]) {
        		if (temp2[lIndex] == 'L') {
        			d = adjIdx(d-1);
        		}
        		else { // 'D'
        			d = adjIdx(d+1);
        		}
        		lIndex++;
        	}
        	
        }
        System.out.println(time);
    }
    private static int adjIdx(int n) {
    	return (n+4)%4;
    }
}