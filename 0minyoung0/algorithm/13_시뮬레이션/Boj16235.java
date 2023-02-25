import java.io.*;
import java.util.*;

public class Boj16235 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	// 땅의 크기
    	int n = Integer.parseInt(st.nextToken());
    	// 처음에 심은 나무의 개수
    	int m = Integer.parseInt(st.nextToken());
    	// k년이 지난 후 살아있는 나무 세기
    	int k = Integer.parseInt(st.nextToken());
    	
    	// ground 배열에 매 순간의 영양분 상태를 저장 (초기상태 : 5)
    	// A 배열에 겨울에 S2D2가 추가하는 영양을 저장
    	int[][] ground = new int[n][n];
    	int[][] A = new int[n][n];
    	for (int i=0; i<n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for (int j=0; j<n; j++) {
    			ground[i][j] = 5;
    			A[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	// 각 좌표마다 나무를 나이순으로 관리하기 위해서 nxn짜리 덱 배열 선언 (0-indexed)
    	// 덱의 int[] 정보 : index0: 나무의 나이, index1: 나무가 최근 영양을 먹은 년도
    	Deque<int[]>[][] trees = new Deque[n][n];
    	for (int i=0; i<n; i++) {
    		for (int j=0; j<n; j++) {
    			trees[i][j] = new ArrayDeque<>();
    		}
    	}
    	
    	// 초기에 심은 나무의 정보에 따라 나무 심기 (덱에 추가)
    	for (int i=0; i<m; i++) {
    		st = new StringTokenizer(br.readLine());
    		// 나무의 좌표
    		int x = Integer.parseInt(st.nextToken());
    		int y = Integer.parseInt(st.nextToken());
    		// 나무의 나이
    		int z = Integer.parseInt(st.nextToken());
    		
    		// 덱에 추가
    		trees[x-1][y-1].offer(new int[] {z, 0});
    	}
    	
    	// 가을에 나무의 번식을 위한 델타 배열 선언
    	int[] dx = {-1,-1,-1,0,0,1,1,1};
    	int[] dy = {-1,0,1,-1,1,-1,0,1};
    	
    	// k년간 나무 기르기
    	for (int year=1; year<=k; year++) {
    		// 봄
    		// 각 좌표에 대해 어린 나무부터 영양을 먹이기
    		for (int i=0; i<n; i++) {
    			for (int j=0; j<n; j++) {
    				// 해당 좌표에 나무가 있다면
    				if (!trees[i][j].isEmpty()) {
    					// 맨 앞의 나무가 올해에 영양을 먹지 않았고, 영양을 줄 수 있다면
    					while (trees[i][j].peek()[1] != year && trees[i][j].peek()[0] <= ground[i][j]) {
    						// 영양을 먹여서 나이를 증가시키고 맨 뒤로 보내기
    						ground[i][j] -= trees[i][j].peek()[0];
    						trees[i][j].offer(new int[] {trees[i][j].poll()[0] + 1, year});
    					}
    				}
    			}
    		}
    		
    		// 여름
    		// 각 좌표에 대해 올해에 영양을 먹지 않은 나무의 나이/2 만큼 ground에 더해주기
    		for (int i=0; i<n; i++) {
    			for (int j=0; j<n; j++) {
    				while (!trees[i][j].isEmpty() && trees[i][j].peek()[1] != year) {
    					ground[i][j] += trees[i][j].poll()[0] / 2;
    				}
    			}
    		}
    		
    		// 가을
    		// 각 좌표에 대해 덱을 한바퀴 순회하면서 나이가 5의 배수이면 인접한 땅에 나이가 1인 나무 추가하기
    		for (int i=0; i<n; i++) {
    			for (int j=0; j<n; j++) {
    				// 덱에 저장된 나무를 모두 탐색
    				for (int l=0; l<trees[i][j].size(); l++) {
    					// 나무가 5의 배수이면
    					if (trees[i][j].peek()[0] % 5 == 0) {
    						// 주변 인접한 8칸에 대해 나무 추가하기
    						for (int dir=0; dir<8; dir++) {
    							int nx = i + dx[dir];
    							int ny = j + dy[dir];
    							// out of index
    							if (nx<0 || nx>=n || ny<0 || ny>=n) continue;
    							// 인덱스를 벗어나지 않으면 덱에 나이 1인 나무 추가
    							trees[nx][ny].offerFirst(new int[] {1, year});
    						}
    					}
    					// 다음 나무 탐색을 위해 맨 뒤로 보내기
    					trees[i][j].offer(trees[i][j].poll());
    				}
    			}
    		}
    		
    		// 겨울
    		// 각 좌표에 대해 A배열의 값만큼 영양을 추가
    		for (int i=0; i<n; i++) {
    			for (int j=0; j<n; j++) {
    				ground[i][j] += A[i][j];
    			}
    		}
    	}
    	
    	// 살아있는 나무의 수 세서 출력
    	int ans = 0;
    	for (int i=0; i<n; i++) {
    		for (int j=0; j<n; j++) {
    			ans += trees[i][j].size();
    		}
    	}
    	System.out.println(ans);
    }
}