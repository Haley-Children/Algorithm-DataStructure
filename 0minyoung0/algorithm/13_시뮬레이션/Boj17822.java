import java.io.*;
import java.util.*;

public class Boj17822 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 원판의 개수
        int n = Integer.parseInt(st.nextToken());
        // 원판에 적힌 숫자의 개수
        int m = Integer.parseInt(st.nextToken());
        // 원판을 회전시킬 횟수
        int t = Integer.parseInt(st.nextToken());
        
        // 원판 정보
        int[][] arr = new int[n+1][m+1];
        for (int i=1; i<=n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j=1; j<=m; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // t번 원판 회전시키고 인접한 수 제거
        for (int i=0; i<t; i++) {
        	st = new StringTokenizer(br.readLine());
        	// x의 배수인 원판을 d방향으로 k칸 회전
        	int x = Integer.parseInt(st.nextToken());
        	int d = Integer.parseInt(st.nextToken());
        	int k = Integer.parseInt(st.nextToken());
        	if (d == 1) {
        		k = m - k;
        	}
        	for (int j=x; j<=n; j+=x) {
    			for (int l=0; l<k; l++) {
    				arr[j][0] = arr[j][m];
    				for (int o=m; o>0; o--) {
    					arr[j][o] = arr[j][o-1];
    				}
    			}
        	}
        	
        	// 인접한 수가 같으면 제거하기 위해 boolean으로 check 남겨서 제거
        	boolean[][] check = new boolean[n+1][m+1];
        	boolean c = false;
        	// 한 원판안에서 인접한 수
        	for (int j=1; j<=n; j++) {
        		for (int l=1; l<m; l++) {
        			// 숫자가 제거되어있으면 continue
        			if (arr[j][l] == 0) continue;
        			// 인접한 같은 숫자 있는지 확인
        			if (arr[j][l] == arr[j][l+1]) {
        				check[j][l] = true;
        				check[j][l+1] = true;
        				c = true;
        			}
        		}
        		if (arr[j][m] == 0) continue;
        		if (arr[j][m] == arr[j][1]) {
        			check[j][m] = true;
        			check[j][1] = true;
        			c = true;
        		}
        	}
        	// 같은 라인에서 인접한 수
        	for (int j=1; j<=m; j++) {
        		for (int l=1; l<n; l++) {
        			// 숫자가 제거되어있으면 continue
        			if (arr[l][j] == 0) continue;
        			// 인접한 같은 숫자 있는지 확인
        			if (arr[l][j] == arr[l+1][j]) {
        				check[l][j] = true;
        				check[l+1][j] = true;
        				c = true;
        			}
        		}
        	}
        	
        	// 체크한적이 있다면
        	if (c) {
	        	// check된 값들 제거
	        	for (int j=1; j<=n; j++) {
	        		for (int l=1; l<=m; l++) {
	        			if (check[j][l]) arr[j][l] = 0;
	        		}
	        	}
        	}
        	// 체크한적이 없다면
        	else {
        		// 원판에 적힌 수의 합과 개수 구하기
        		int numSum = 0;
        		int numCnt = 0;
        		for (int j=1; j<=n; j++) {
	        		for (int l=1; l<=m; l++) {
	        			if (arr[j][l] != 0) {
	        				numSum += arr[j][l];
	        				numCnt++;
	        			}
	        		}
	        	}
        		double numAvg = (double) numSum / numCnt;
        		// 평균보다 큰 수 1빼고 작은 수는 1더하기
        		for (int j=1; j<=n; j++) {
	        		for (int l=1; l<=m; l++) {
	        			if (arr[j][l] != 0) {
	        				if (arr[j][l] > numAvg) {
	        					arr[j][l]--;
	        				}else if (arr[j][l] < numAvg) {
	        					arr[j][l]++;
	        				}
	        			}
	        		}
	        	}
        	}
        }
        
        // 원판에 적힌 수의 합 계산
        int ans = 0;
        for (int i=1; i<=n; i++) {
        	for (int j=1; j<=m; j++) {
        		ans += arr[i][j];
        	}
        }
        
        // 답 출력
        System.out.println(ans);
    }
}