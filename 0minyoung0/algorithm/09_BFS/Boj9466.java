// bfs.boj9466;

import java.io.*;
import java.util.*;

public class Boj9466 {
    public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	StringBuilder sb = new StringBuilder();
    	int t = Integer.parseInt(br.readLine());
    	for (int i=0; i<t; i++) {
    		int n = Integer.parseInt(br.readLine());
    		int[] selectNum = new int[n+1];
    		st = new StringTokenizer(br.readLine());
    		for (int j=1; j<=n; j++) {
    			selectNum[j] = Integer.parseInt(st.nextToken());
    		}
    		
    		// 방문여부를 저장할 boolean 배열
    		boolean[] vis = new boolean[n+1];
    		// 루프에 해당하는지 저장할 int 배열. 루프에 해당하면 1, 아니면 -1, 아직 모르면 0
    		int[] isLooped = new int[n+1];
    		
    		// 루프에 해당하지 않는 점을 돌면서 방문한 적 있는 점에 도달하면 거기가 루프의 시작점일 수 있음
    		// 루프의 시작점이 isLooped에 없다면 거기부터 다시 돌면서 isLooped boolean 배열에 저장 
    		for (int j=1; j<=n; j++) {
    			if (!vis[j]) {
    				int cur = j;
    				// 방문한 적이 있는 점까지 돌리기
    				while(!vis[cur]) {
    					vis[cur] = true;
    					// 다음 사람 지목
    					cur = selectNum[cur];
    				}
    				// cur가 루프에 포함되지 않는다는 표시가 없다면 cur는 루프의 시작점
    				// cur가 루프에 포함되지 않는다픈 표시가 있다면 cur는 그냥 루프 이전의 한 노드
    				while(isLooped[cur] == 0) {
    					vis[cur] = true;
    					isLooped[cur] = 1;
    					cur = selectNum[cur];
    				}
    				// j부터 cur 이전까지의 isLooped를 -1로 변경
    				int cur2 = j;
    				while(cur2 != cur) {
    					isLooped[cur2] = -1;
    					cur2 = selectNum[cur2];
    				}
    			}
    		}
    		
    		// isLooped 배열에서 false인 개수를 세서 ans에 저장
    		int ans = 0;
    		for (int j=1; j<=n; j++) {
    			if (isLooped[j] == -1) ans++;
    		}
    		// 스트링 빌더에 답 저장
    		sb.append(ans).append("\n");
    	}
    	System.out.println(sb);
    }
}