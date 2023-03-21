package mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1647 {
	static int[] group;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 집의 개수
		int n = Integer.parseInt(st.nextToken());
		// 길의 개수
		int m = Integer.parseInt(st.nextToken());
		
		List<Edge> edges = new ArrayList<>();
		// 간선 입력받기
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			edges.add(new Edge(u, v, w));
		}
		
		// 간선 정렬
		Collections.sort(edges);
		
		// mst 만들기(크루스칼)
		// union 초기화
		group = new int[n+1];
		for(int i = 1; i <= n; i++) {
			group[i] = i;
		}
		
		// 트리 만들기
		// 총 비용
		int cost = 0;
		// 선택한 길의 개수
		int selected = 0;
		
		for(int i = 0; i < edges.size(); i++) {
			Edge edge = edges.get(i);
			
			// 이미 from과 to가 같은 그룹이면 스킵
			if(find(edge.to) == find(edge.from)) continue;
			
			// from과 to를 연결
			union(edge.from, edge.to);
			cost += edge.weight;
			selected++;
			
			// 집을 모두 연결한 후 가장 비용이 큰 길을 잘라서 마을을 분리
			if(selected == n - 2) break;
		}
		
		System.out.println(cost);
	}
	
	static void union(int u, int v) {
		u = find(u);
		v = find(v);
		
		if(u == v) return;
		
		group[v] = u;
	}
	
	static int find(int v) {
		if(group[v] == v) {
			return v;
		}
		
		return group[v] = find(group[v]);
	}
	
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;
		
		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		// 가중치 오름차순으로 정렬
		@Override
		public int compareTo(Edge o) {
			if(this.weight == o.weight) {
				return this.from - o.from;
			} 
			return this.weight - o.weight;
		}
	}
	
}

