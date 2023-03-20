package mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1368 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 논의 개수
		int n = Integer.parseInt(br.readLine());
		
		// 0~n-1번 끼리 물을 끌어올 때 비용
		// [i][n] == [n][i] == i번에 우물을 파는 비용
		int[][] costs = new int[n+1][n+1];
		
		// 우물을 파는 비용을 저장
		for(int i = 0; i < n; i++) {
			costs[n][i] = costs[i][n] = Integer.parseInt(br.readLine());
		}
		
		// 간선 정보를 저장
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				costs[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 간선의 개수가 많으므로 프림 알고리즘 이용
		// 트리에 포함되었는지
		boolean[] isTree = new boolean[n+1];
		
		// 간선의 가중치가 적은게 앞에 오도록
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[0] - e2[0]);
		
		// 최소 하나의 간선은 우물과 연결되어야 하므로 우물에서 시작
		isTree[n] = true;
		
		for(int i = 0; i < n; i++) {
			pq.offer(new int[] {costs[n][i], i});
		}
	
		// mst 만들기
		int selected = 0;
		int totalCost = 0;
		
		while(selected < n) {
			int[] cur = pq.poll();

			// 목적지가 이미 트리에 포함되었으면 생략
			if(isTree[cur[1]]) continue;
			
			int cost = cur[0];
			int to = cur[1];
			
			isTree[to] = true;
			totalCost += cost;
			selected++;
			
			// 목적지에 연결된 간선들 모두 저장
			for(int i = 0; i <= n; i++) {
				// 이미 트리에 포함이 된 곳과 연결되는 간선이면 생략
				if(isTree[i]) continue;
				pq.offer(new int[] {costs[to][i], i});
			}
		}
		
		System.out.println(totalCost);
	}
}

