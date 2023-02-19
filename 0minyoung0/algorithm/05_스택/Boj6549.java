import java.io.*;
import java.util.*;

public class Boj6549 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		
		while (n != 0) {
			long ans = 0;
			
			// 데이터를 저장할 스택을 덱으로 선언
			Deque<int[]> stack = new ArrayDeque<>();
			
			label: for (int i=0; i<n; i++) {
				int cur = Integer.parseInt(st.nextToken());
				int count = 1;
				
				// 스택이 비었으면 데이터 저장
				if (stack.isEmpty()) {
					stack.offerFirst(new int[] {cur, count});
					continue label;
				}
				
				// stack.peek()[0] >= cur 인 경우
				while (stack.peek()[0] >= cur) {
					// pop하기 전에 ans 후보 체크
					if (stack.peek()[0] > cur) {
						ans = Math.max(ans, (long)stack.peek()[0] * (stack.peek()[1] + count - 1));
					}
					// pop하면서 count 전달
					count += stack.poll()[1];
					
					// 스택이 비었으면 데이터 저장
					if (stack.isEmpty()) {
						stack.offerFirst(new int[] {cur, count});
						continue label;
					}
				}
				
				// stack.peek()[0] < cur인 경우
				stack.offerFirst(new int[] {cur, count});
			}
			
			int temp = 0;
			// 스택에 남은거 정산
			while (!stack.isEmpty()) {
				// count 넘겨주기 위해서 temp에 임시로 저장
				temp += stack.peek()[1];
				ans = Math.max(ans, (long)stack.poll()[0] * temp);
			}
			
			sb.append(ans).append("\n");
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
		}
		System.out.println(sb);
	}
}
