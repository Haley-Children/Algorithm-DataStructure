import java.io.*;
import java.util.*;

public class Boj18869 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 우주의 개수
		int m = Integer.parseInt(st.nextToken());
		// 우주에 있는 행성의 개수
		int n = Integer.parseInt(st.nextToken());
		
		// 각 우주에 있는 행성의 크기를 배열과 리스트에 저장하고 리스트는 각 우주별로 정렬
		int[][] planet = new int[m][n];
		List<List<Integer>> list = new ArrayList<>();
		for (int i=0; i<m; i++) {
			list.add(new ArrayList<>());
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				int temp = Integer.parseInt(st.nextToken());
				planet[i][j] = temp;
				list.get(i).add(temp);
			}
			Collections.sort(list.get(i));
		}
		
		// 각 우주의 행성들이 자기보다 작은 행성의 개수를 저장할 배열
		int[][] lower = new int[m][n];
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				int target = planet[i][j];
				int s = 0;
				int e = n - 1;
				while (s < e) {
					// 리스트에서 탐색할 행성 인덱스
					int mid = (s + e) / 2;
					// 찾는 값보다 작을 때
					if (target > list.get(i).get(mid)) s = mid + 1;
					// 찾는 값보다 크거나 같을 때
					else e = mid;
				}
				lower[i][j] = s;
			}
		}
		
		// 모든 우주의 쌍에 대해서 답 개수 세기
		int ans = 0;
		for (int i=0; i<m-1; i++) {
			next: for (int j=i+1; j<m; j++) {
				for (int k=0; k<n; k++) {
					if (lower[i][k] != lower[j][k]) continue next;
				}
				ans++;
			}
		}
		
		// 답 출력
		System.out.println(ans);
	}
}
