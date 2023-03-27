import java.io.*;
import java.util.*;

public class Boj1774 {
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 우주신의 수
		int n = Integer.parseInt(st.nextToken());
		// 이미 연결된 통로의 수
		int m = Integer.parseInt(st.nextToken());
		
		// 각 우주신의 좌표
		int[][] coo = new int[n+1][2];
		for (int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			coo[i][0] = Integer.parseInt(st.nextToken());
			coo[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 부모정보를 담을 배열
		parent = new int[n+1];
		for (int i=1; i<=n; i++) {
			parent[i] = i;
		}
		
		// 이미 연결된 통로
		int cnt = 0;
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			// 새로 연결될때마다 카운트 증가
			if (union(x, y) && ++cnt == n-1) break;
		}
		
		// 모두 연결되었으면 0 출력 후 리턴
		if (cnt == n-1) {
			System.out.println(0.00);
			return;
		}
		
		// 서로 연결되지 않은 거리 정보 저장
		List<long[]> dis = new ArrayList<>();
		for (int i=1; i<=n; i++) {
			for (int j=i+1; j<=n; j++) {
				if (parent[i] == parent[j]) continue;
				long d_square = ((long) (coo[i][0] - coo[j][0])) * (coo[i][0] - coo[j][0])
								+ ((long) (coo[i][1] - coo[j][1])) * (coo[i][1] - coo[j][1]);
				dis.add(new long[] {d_square, i, j});
			}
		}
		Collections.sort(dis, new Comparator<long[]>() {
			public int compare(long[] o1, long[] o2) {
				if (o1[0] < o2[0]) return -1;
				if (o1[0] > o2[0]) return 1;
				return o1[1]!=o2[1]? (int)(o1[1]-o2[1]): (int)(o1[2]-o2[2]);
			}
		});
		
		// 답을 저장할 변수
		double ans = 0;
		
		// 크루스칼 알고리즘으로 통로 연결하기
		for (long[] d : dis) {
			if (!union((int)d[1], (int)d[2])) continue;
			
			ans += Math.sqrt(d[0]);
			if (++cnt == n-1) break;
		}
		
		// 답 출력
		System.out.printf("%.2f", ans);
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