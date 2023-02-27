import java.io.*;
import java.util.*;

public class Boj11003 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 수의 개수
		int n = Integer.parseInt(st.nextToken());
		// 최솟값 탐색 길이
		int l = Integer.parseInt(st.nextToken());
		
		// 탐색에 사용할 덱 선언
		Deque<int[]> dq = new ArrayDeque<>();
		
		// 덱을 이용한 탐색
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			int[] cur = new int[] {Integer.parseInt(st.nextToken()), i};
			
			// 덱이 비어있지 않고, front의 주소가 cur의 주소 - L 이하라면 poll
			if (!dq.isEmpty() && dq.peek()[1] <= i - l) {
				dq.poll();
			}
			
			// 덱이 비어있지 않고, back의 값이 cur의 값보다 크거나 같은 경우 계속해서 pollLast
			while (!dq.isEmpty() && dq.peekLast()[0] >= cur[0]) {
				dq.pollLast();
			}
			
			// 덱이 비어있거나 back의 값이 cur의 값보다 작다면 offer
			dq.offer(cur);
			
			// front값을 스트링 빌더에 append
			sb.append(dq.peek()[0]).append(" ");
		}
		System.out.println(sb);
	}
}
