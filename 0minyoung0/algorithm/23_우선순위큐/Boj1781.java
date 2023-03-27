import java.io.*;
import java.util.*;

public class Boj1781 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 문제의 개수
		int n = Integer.parseInt(br.readLine());
		
		// 날짜를 저장할 트리셋
		TreeSet<Integer> set = new TreeSet<>();
		for (int i=1; i<=n; i++) {
			set.add(i);
		}
		
		// 리스트에 문제를 {데드라인, 컵라면 수}를 하나의 노드로 해서 저장
		List<int[]> list = new ArrayList<>();
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list.add(new int[] {d, c});
		}
		
		// 리스트의 문제를 컵라면 수를 기준으로 정렬
		Collections.sort(list, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				if (o1[1] != o2[1]) return o2[1] - o1[1];
				return o1[0] - o2[0];
			}
		});
		
		// 받은 컵라면을 저장할 변수
		int ans = 0;
		
		// 문제를 컵라면 많이 주는거부터 그리디하게 풀 날짜 찾기
		for (int i=0; i<n; i++) {
			int[] problem = list.get(i);
			
			// 풀 수 있는 날짜가 없으면 continue
			if (set.floor(problem[0]) == null) continue;
			
			// 풀 수 있으면 푼 날짜 제거하고 컵라면 받기
			set.remove(set.floor(problem[0]));
			ans += problem[1];
		}
		
		// 답 출력
		System.out.println(ans);
		
	}
}
