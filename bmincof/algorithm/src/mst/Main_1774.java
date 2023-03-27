package mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1774 {
	static int[] group;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] pos = new int[n+1][2];
		group = new int[n+1];
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			
			pos[i][0] = Integer.parseInt(st.nextToken());
			pos[i][1] = Integer.parseInt(st.nextToken());
			group[i] = i;
		}
		
		int selected = 0;
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			if(union(u, v)) {
				selected++;
			};
			
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		for(int i = 1; i <= n - 1; i++) {
			for(int j = i + 1; j <= n; j++) {
				// 이미 연결됐으면 스킵
				if(find(i) == find(j)) continue;
				double dx = pos[i][0] - pos[j][0];
				double dy = pos[i][1] - pos[j][1];
				
				pq.offer(new Edge(i, j, Math.sqrt(dx*dx + dy*dy)));
			}
		}
		
		double len = 0;
		while(selected < n-1) {
			Edge cur = pq.poll();
			
			// 처음 연결하는 정점이면
			if(union(cur.u, cur.v)) {
				len += cur.weight;
				selected++;
			}
		}
		
		System.out.printf("%.2f\n", len);
		
	}
	
	// 이미 합쳐진 그룹이면 false
	static boolean union(int u, int v) {
		u = find(u);
		v = find(v);
		
		if(u == v) return false;
		
		group[v] = u;
		return true;
	}
	
	static int find(int u) {
		if(group[u] == u) {
			return u;
		}
		
		return group[u] = find(group[u]);
	}
	
	static class Edge implements Comparable<Edge> {
		int u, v;
		double weight;

		public Edge(int u, int v, double weight) {
			this.u = u;
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight <= o.weight ? -1 : 1;
		}
		
	}
	
}

