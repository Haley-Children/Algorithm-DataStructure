import java.io.*;
import java.util.*;

public class Boj13418 {
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 건물의 개수
		int n = Integer.parseInt(st.nextToken());
		// 도로의 개수
		int m = Integer.parseInt(st.nextToken());
		
		// 각 도로의 피로도
		List<int[]> cost = new ArrayList<>();
		for (int i=0; i<=m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = 1 - Integer.parseInt(st.nextToken());
			cost.add(new int[] {c, a, b});
		}
		Collections.sort(cost, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0]!=o2[0]? o1[0]-o2[0]: o1[1]!=o2[1]? o1[1]-o2[1]: o2[2]-o2[2];
			}
		});
		
		// 부모정보를 담을 배열
		parent = new int[n+1];
		for (int i=0; i<=n; i++) {
			parent[i] = i;
		}
		
		// 크루스칼 알고리즘으로 최악의 피로도 계산
		int ans1 = 0;
		int cnt = 0;
		for (int i=m; i>=0; i--) {
			int[] c = cost.get(i);
			if (!union(c[1], c[2])) continue;
			ans1 += c[0];
			if (++cnt == n) break;
		}
		ans1 = ans1 * ans1;
		
		// 부모 배열 초기화
		for (int i=0; i<=n; i++) {
			parent[i] = i;
		}
		
		// 크루스칼 알고리즘으로 최적의 피로도 계산
		int ans2 = 0;
		cnt = 0;
		for (int[] c: cost) {
			if (!union(c[1], c[2])) continue;
			ans2 += c[0];
			if (++cnt == n) break;
		}
		ans2 = ans2 * ans2;
		
		// 답 출력
		System.out.println(ans1 - ans2);
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