import java.io.*;
import java.util.*;

public class Boj16398 {
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		// 행성의 수
		int n = Integer.parseInt(br.readLine());
		
		// 플로우 관리 비용
		List<int[]> cost = new ArrayList<>();
		for (int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=n; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 0 || i >= j) continue;
				cost.add(new int[] {temp, i, j});
			}
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
			if (++cnt == n - 1) break;
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
		
		return parent[x]=find(parent[x]);
	}
}