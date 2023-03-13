import java.io.*;
import java.util.*;

public class Boj17142 {
	static int n, m, ans;
	static int[][] map;
	static List<int[]> list;
	static List<Integer> vIdx = new ArrayList<>();
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	// 연구소의 크기
    	n = Integer.parseInt(st.nextToken());
    	// 놓을 수 있는 바이러스의 개수
    	m = Integer.parseInt(st.nextToken());
    	
    	// 최소값을 찾아야 하므로 Integer의 최대값으로 초기화
    	ans = Integer.MAX_VALUE;
    	
    	// 배열에 연구소 상태 정보 저장
    	map = new int[n][n];
    	list = new ArrayList<>();
    	for (int i=0; i<n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for (int j=0; j<n; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    			// 리스트에 바이러스를 놓을 수 있는 좌표 저장
    			if (map[i][j] == 2) {
    				list.add(new int[] {i,j});
    			}
    		}
    	}
    	
    	// 백트래킹으로 바이러스 놓을 곳 골라서 BFS로 바이러스 퍼트리기
    	func(0, -1);
    	
    	if (ans == Integer.MAX_VALUE) {
    		ans = -1;
    	}
    	
    	// 답 출력
    	System.out.println(ans);
    }
    
    private static void func(int k, int pre) {
    	// 바이러스를 놓을 곳을 모두 골랐으면
    	if (k == m) {
    		// 맵 복사
    		int[][] newMap = new int[n][n];
    		for (int i=0; i<n; i++) {
    			for (int j=0; j<n; j++) {
    				newMap[i][j] = map[i][j];
    			}
    		}
    		
    		// BFS로 바이러스 퍼트리기
    		Queue<int[]> q = new ArrayDeque<>();
    		for (int i=0; i<vIdx.size(); i++) {
    			q.add(list.get(vIdx.get(i)));
    			// 바이러스를 10으로 저장함
    			newMap[list.get(vIdx.get(i))[0]][list.get(vIdx.get(i))[1]] = 10;
    		}
    		
    		while (!q.isEmpty()) {
    			int[] cur = q.poll();
    			for (int dir=0; dir<4; dir++) {
    				int nx = cur[0] + dx[dir];
    				int ny = cur[1] + dy[dir];
    				// out of index
    				if (nx<0 || nx>=n || ny<0 || ny>=n) continue;
    				// 벽이거나 방문한적 있는 경우
    				if (newMap[nx][ny] == 1 || newMap[nx][ny] > 9) continue;
    				// 바이러스 전염시키기
    				q.add(new int[] {nx,ny});
    				newMap[nx][ny] = newMap[cur[0]][cur[1]] + 1;
    			}
    		}
    		// newMap에서의 최대값 - 10이 마지막 전염까지 걸린 시간
    		int temp = 10;
    		for (int i=0; i<n; i++) {
    			for (int j=0; j<n; j++) {
    				// 전염이 안된 칸이 있다면 리턴
    				if (newMap[i][j] == 0 || newMap[i][j] == 2) return;
    				// 마지막 전염이 비활성 바이러스인 경우를 제외하고 카운팅
    				if (map[i][j] != 2) {
    					temp = newMap[i][j] > temp? newMap[i][j] : temp;
    				}
    			}
    		}
    		
    		// ans 갱신
    		ans = ans < temp-10? ans : temp-10;
    		
    		return;
    	}
    	
    	// 바이러스 놓을 곳 고르기
    	for (int i=pre+1; i<list.size(); i++) {
    		vIdx.add(i);
    		func(k+1, i);
    		vIdx.remove(vIdx.size()-1);
    	}
    }
}