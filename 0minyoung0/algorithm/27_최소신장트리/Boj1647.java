import java.io.*;
import java.util.*;

public class Boj1647 {
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 집의 개수
		int n = Integer.parseInt(st.nextToken());
		// 길의 개수
		int m = Integer.parseInt(st.nextToken());
		
		// 길 유지비
		List<int[]> cost = new ArrayList<>();
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			cost.add(new int[] {c, a, b});
		}
		Collections.sort(cost, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0]!=o2[0]? o1[0]-o2[0]: o1[1]!=o2[1]? o1[1]-o2[1]: o2[2]-o2[2];
			}
		});
		
		// 부모정보를 담을 배열
		parent = new int[n+1];
		for (int i=1; i<=n; i++) {
			parent[i] = i;
		}

		// 답을 저장할 변수
		long ans = 0;
		
		// 크루스칼 (유니온 파인드 활용)
		int cnt = 0;
		for (int[] c : cost) {
			if (!union(c[1], c[2])) continue;
			ans += c[0];
			// 최소신장트리에서 마지막 하나의 간선을 연결하지 않음
			if (++cnt == n - 2) break;
		}
		
		// 답 출력
		System.out.println(ans);
	}
	
	private static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if (x == y) return false;
		
		parent[Math.max(x, y)] = Math.min(x, y);
		return true;
	}
	
	private static int find(int x) {
		if (x == parent[x]) return x;
		
		return parent[x] = find(parent[x]);
	}
}