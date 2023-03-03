import java.io.*;
import java.util.*;

public class Boj20057 {
	static int n, ans, curX, curY;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        // 모래밭 크기
        n = Integer.parseInt(br.readLine());
        // 모래밭 가운데 칸 좌표
        int k = n / 2;
        
        // 격자의 각 칸에 있는 모래 저장
        int[][] arr = new int[n][n];
        for (int i=0; i<n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j=0; j<n; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // 토네이도 위치
        curX = k;
        curY = k;
        
        int temp;
        
        // 토네이도
        for (int i=1; i<=k; i++) {
        	// (k+1-i, k+1-i) => (k+1-i, k-i)
    		curY--;
    		arr = tornado(curX, curY, arr);
        	
        	// 배열 돌리기
        	temp = curX;
        	curX = curY;
        	curY = n-1-temp;
        	arr = turnArr(arr);
        	
        	// (k-i, k-1+i) => (k-i, k-i)
        	// (k-i, k+i) => (k-i, k-i) 2번 반복
        	for (int j=0; j<3; j++) {
            	while (curY != k-i) {
            		curY--;
            		arr = tornado(curX, curY, arr);
            	}
            	
            	// 배열 돌리기
            	temp = curX;
            	curX = curY;
            	curY = n-1-temp;
            	arr = turnArr(arr);
        	}
        	
        	// (k-i, k+i) => (k-i, k-i)
        	while (curY != k-i) {
        		curY--;
        		arr = tornado(curX, curY, arr);
        	}
        }
        
        // 답 출력
        System.out.println(ans);
    }
    // 토네이도를 위한 델타배열
    static int[] dx = {-1,1,-2,2,0,-1,1,-1,1};
    static int[] dy = {1,1,0,0,-2,0,0,-1,-1};
    static int[] p = {1,1,2,2,5,7,7,10,10};
    
    // 토네이도 진행
    private static int[][] tornado (int x, int y, int[][] arr) {
    	// 전체 모래의 양
    	int sand = arr[x][y];
    	// 비율 써진 칸으로 모래 날리기
    	for (int i=0; i<9; i++) {
    		// 일단 날릴 모래 제거
    		arr[x][y] -= p[i]*sand/100;
    		// 날릴 좌표
    		int nx = x + dx[i];
    		int ny = y + dy[i];
    		// 범위 밖이면 ans에 더하고 continue
    		if (nx<0 || nx>=n || ny<0 || ny>=n) {
    			ans += p[i]*sand/100;
    			continue;
    		}
    		// 범위 안이면 arr에 더해주기
    		arr[nx][ny] += p[i]*sand/100;
    	}
    	
    	// 알파 칸으로도 모래 날리기
    	if (y > 0) {
    		arr[x][y-1] += arr[x][y];
    	}
    	else {
    		ans += arr[x][y];
    	}
    	arr[x][y] = 0;
    	return arr;
    }
    
    // 배열 돌리기
    private static int[][] turnArr (int[][] arr) {
    	int[][] newArr = new int[n][n];
    	for (int i=0; i<n; i++) {
    		for (int j=0; j<n; j++) {
    			newArr[j][n-1-i] = arr[i][j];
    		}
    	}
    	return newArr;
    }
}