import java.io.*;
import java.util.*;

public class Boj1043 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 사람의 수
        int n = Integer.parseInt(st.nextToken());
        // 파티의 수
        int m = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        // 진실을 아는 사람 수
        int k = Integer.parseInt(st.nextToken());
        // 진실을 아는 사람 정보
        int[] knowTruth = new int[k];
        for (int i=0; i<k; i++) {
        	knowTruth[i] = Integer.parseInt(st.nextToken());
        }
        
        // 그래프를 인접배열로 구현
        int[][] graph = new int[n+1][n+1];
        
        // 같은 파티에 오는 사람끼리 인접 정보 부여
        // 파티에 오는 사람 정보
        int[] p = new int[m];
        int[][] people = new int[m][50];
        for (int i=0; i<m; i++) {
        	st = new StringTokenizer(br.readLine());
        	// 파티에 오는 사람의 수
        	p[i] = Integer.parseInt(st.nextToken());
        	// 파티에 오는 사람 정보
        	for (int j=0; j<p[i]; j++) {
        		people[i][j] = Integer.parseInt(st.nextToken());
        	}
        	// 같은 파티에 온 사람끼리 인접 정보 부여
        	for (int j1=0; j1<p[i]-1; j1++) {
        		for (int j2=j1+1; j2<p[i]; j2++) {
        			graph[people[i][j1]][people[i][j2]] = 1;
        			graph[people[i][j2]][people[i][j1]] = 1;
        		}
        	}
        }
        
        // 진실을 아는 사람 or 진실을 아는 사람과 연결된 사람을 표시할 boolean 배열
        boolean[] check = new boolean[n+1];
        Queue<Integer> q = new ArrayDeque<>();
        for (int i=0; i<k; i++) {
        	if (check[knowTruth[i]]) continue;
        	check[knowTruth[i]] = true;
        	q.offer(knowTruth[i]);
        	while(!q.isEmpty()) {
        		int cur = q.poll();
        		for (int j=1; j<=n; j++) {
        			if (graph[cur][j] != 1) continue;
        			if (check[j]) continue;
        			check[j] = true;
        			q.offer(j);
        		}
        	}
        }
        
        // 각 파티에 체크된 사람이 있으면 거짓말 못함
        int ans = m;
        party: for (int i=0; i<m; i++) {
        	for (int j=0; j<p[i]; j++) {
        		if (check[people[i][j]]) {
        			ans--;
        			continue party;
        		}
        	}
        }
        
        // 답 출력
        System.out.println(ans);
    }
}