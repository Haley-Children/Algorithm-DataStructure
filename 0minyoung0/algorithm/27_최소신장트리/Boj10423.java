import java.io.*;
import java.util.*;

public class Boj10423 {
	// 발전소가 설치된 도시를 0번 노드와 연결된 도시라고 생각해서 MST 구하기 (그래프 모델링!)
	
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 도시의 개수
		int n = Integer.parseInt(st.nextToken());
		// 설치 가능한 케이블의 개수
		int m = Integer.parseInt(st.nextToken());
		// 발전소의 개수
		int k = Integer.parseInt(st.nextToken());
		
		// 부모 배열
		parent = new int[n+1];
		for (int i=0; i<=n; i++) {
			parent[i] = i;
		}
		
		// 발전소가 설치된 도시의 번호
		int cnt = 0;
		st = new StringTokenizer(br.readLine());
		while (k-- != 0) {
			int temp = Integer.parseInt(st.nextToken());
			if (!union(0, temp)) continue;
			cnt++;
		}
		
		// 발전소가 이미 다 설치 된 경우에는 0 출력 후 리턴
		if (cnt == n) {
			System.out.println(0);
			return;
		}
		
		// 설치 가능한 케이블의 정보
		List<int[]> cable = new ArrayList<>();
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			cable.add(new int[] {w, u, v});
		}
		
		// 케이블 정보 정렬
		Collections.sort(cable, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0]!=o2[0]? o1[0]-o2[0]: o1[1]!=o2[1]? o1[1]-o2[1]: o2[1]-o2[2];
			}
		});
		
		// 크루스칼 알고리즘
		int ans = 0;
		for (int[] c : cable) {
			if (!union(c[1], c[2])) continue;
			ans += c[0];
			if (++cnt == n) break;
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