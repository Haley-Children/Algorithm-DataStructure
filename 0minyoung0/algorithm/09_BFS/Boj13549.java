// bfs.boj13549;

import java.io.*;
import java.util.*;

public class Boj13549 {
    static int n, k, ans;
    static int[] dis = new int[100001];
    static int[] dx = {-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        Queue<Integer> q = new LinkedList<>();
        
        // 큐에 넣을때 2^n을 곱한 꼴도 전부 넣음
        int temp = n;
        do {
        	q.add(temp);
        	dis[temp] = 1;
        	temp *= 2;
        } while(temp>0 && temp<=100000);
        
        label: while(!q.isEmpty() && dis[k]==0) {
        	int cur = q.poll();
        	for (int dir=0; dir<2; dir++) {
        		int nx = cur + dx[dir];
        		if (nx < 0 || nx > 100000) continue;
        		if (dis[nx] > 0) continue;
        		temp = nx;
        		
                do {
                	if (temp == k) {
                		ans = dis[cur];
                		break label;
                	}
                	if (dis[temp]==0) {
	                	q.add(temp);
	                	dis[temp] = dis[cur] + 1;
                	}
                	temp *= 2;
                } while(temp>0 && temp<=100000);
        	}
        }
        
        System.out.println(ans);
    }
}