import java.io.*;
import java.util.*;

public class Boj1197 {
	public static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 정점의 개수
		int v = Integer.parseInt(st.nextToken());
		// 간선의 개수
		int e = Integer.parseInt(st.nextToken());
		
		// 간선 정보
		List<int[]> edge = new ArrayList<>();
		for (int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edge.add(new int[] {a, b, c});
		}
		Collections.sort(edge, new Comparator<int[]>(){
			public int compare(int[] o1, int[] o2) {
				return o1[2]!=o2[2]? o1[2]-o2[2]: o1[1]!=o2[1]? o1[1]-o2[1]: o1[0]-o2[0];
			}
		});
		
		// 부모 정보를 담을 배열
		parent = new int[v+1];
		for (int i=1; i<=v; i++) {
			parent[i] = i;
		}
		
		// 답을 저장할 변수
		long ans = 0;
		
		// 유니온 파인드로 크루스칼 알고리즘
		int find = 0;
		for (int[] cur : edge) {
			// 이미 연결된 경우
			if (!union(cur[0], cur[1])) continue;
			// 연결되지 않은 경우
			ans += cur[2];
			// 최소 신장 트리를 구성하는 간선을 다 찾았으면 끝내기
			if (++find == v - 1) break;
		}
		
		// 답 출력
		System.out.println(ans);
	}
	private static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		// 같은 그래프에 속해있는 경우
		if (x == y) return false;
		// 다른 그래프에 속해있는 경우
		parent[Math.max(x, y)] = Math.min(x, y);
		return true;
	}
	
	private static int find(int x) {
		if (parent[x] == x) return x;
		return find(parent[x]);
	}
}
