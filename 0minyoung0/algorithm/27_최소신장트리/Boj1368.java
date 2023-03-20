import java.io.*;
import java.util.*;

public class Boj1368 {
	public static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 논의 수
		int n = Integer.parseInt(br.readLine());
		
		// 간선 정보 (우물파는건 0번 정점에서 온거라고 생각하기!)
		List<int[]> cost = new ArrayList<>();	
		for (int i=1; i<=n; i++) {
			cost.add(new int[] {0, i, Integer.parseInt(br.readLine())});
		}
		for (int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=n; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp != 0) {
					cost.add(new int[] {i, j, temp});
				}
			}
		}
		Collections.sort(cost, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[2]!=o2[2]? o1[2]-o2[2]: o1[0]!=o2[0]? o1[0]-o2[0]: o1[1]-o2[1];
			}
		});
		
		// 답을 저장할 변수
		int ans = 0;
		
		// 크루스칼 알고리즘 (유니온 파인드 활용)
		int cnt = 0;
		parent = new int[n+1];
		for (int i=1; i<=n; i++) {
			parent[i] = i;
		}
		for (int[] cur : cost) {
			// 이미 연결된 경우
			if (!union(cur[0], cur[1])) continue;
			
			// 답에 비용 추가
			ans += cur[2];
			
			// 다 연결한 경우
			if (++cnt == n) break;
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
		parent[Math.min(x, y)] = Math.max(x, y);
		return true;
	}
	
	private static int find(int x) {
		if (parent[x] == x) return x;
		return find(parent[x]);
	}
}
