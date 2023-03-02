import java.io.*;
import java.util.*;

public class Boj20056 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 델타배열
        int[] dx = {-1,-1,0,1,1,1,0,-1};
        int[] dy = {0,1,1,1,0,-1,-1,-1};
        
        // 격자 크기
        int n = Integer.parseInt(st.nextToken());
        // 파이어볼 개수
        int m = Integer.parseInt(st.nextToken());
        // k턴 후 종료
        int k = Integer.parseInt(st.nextToken());
        
        // 파이어볼 다룰 큐
        Queue<int[]> q = new ArrayDeque<>();
        // 파이어볼 다룰 리스트
        List[][] list = new List[n][n];
        for (int i=0; i<n; i++) {
        	for (int j=0; j<n; j++) {
        		list[i][j] = new ArrayList<int[]>();
        	}
        }
        
        // 파이어볼 큐에 다 넣기
        for (int i=0; i<m; i++) {
        	st = new StringTokenizer(br.readLine());
        	q.add(new int[] {Integer.parseInt(st.nextToken())-1,
        					Integer.parseInt(st.nextToken())-1,
        					Integer.parseInt(st.nextToken()),
        					Integer.parseInt(st.nextToken()),
        					Integer.parseInt(st.nextToken())});
        }
        
        // k턴 진행
        for (int turn=1; turn<=k; turn++) {
        	// 큐에서 파이어볼 빼면서 이동시키고 list에 넣기
        	while(!q.isEmpty()) {
        		int[] cur = q.poll();
        		int nx = (cur[0] + cur[3] * dx[cur[4]]) % n;
        		nx = nx < 0? nx + n: nx;
        		int ny = (cur[1] + cur[3] * dy[cur[4]]) % n;
        		ny = ny < 0? ny + n: ny;
        		list[nx][ny].add(new int[] {cur[2], cur[3], cur[4]});
        	}
        	
        	// 각 좌표를 돌면서 리스트의 파이어볼을 처리하고 큐에 넣기
        	for (int i=0; i<n; i++) {
        		for (int j=0; j<n; j++) {
        			if (list[i][j].size() >= 2) {
        				int mass = 0;
        				int speed = 0;
        				int dir = 0;
        				for (int l=0; l<list[i][j].size(); l++) {
        					int[] cur = (int[]) list[i][j].get(l);
        					mass += cur[0];
        					speed += cur[1];
        					dir += cur[2] % 2;
        				}
        				if (mass >= 5) {
	        				mass /= 5;
	        				speed /= list[i][j].size();
	        				if (dir == 0 || dir == list[i][j].size()) dir = 0;
	        				else dir = 1;
	        				for (int l=0; l<4; l++) {
	        					q.add(new int[] {i, j, mass, speed, dir+2*l});
	        				}
        				}
        				list[i][j].clear();
        			}else if (list[i][j].size() == 1) {
        				int[] cur = (int[]) list[i][j].get(0);
        				q.add(new int[] {i, j, cur[0], cur[1], cur[2]});
        				list[i][j].clear();
        			}
        		}
        	}
        }
        
        // 남아있는 파이어볼 질량의 합 출력
        int ans = 0;
        while (!q.isEmpty()) {
        	ans += q.poll()[2];
        }
        System.out.println(ans);
    }
}