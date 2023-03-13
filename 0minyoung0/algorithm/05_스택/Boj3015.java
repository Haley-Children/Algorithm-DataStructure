import java.io.*;
import java.util.*;

public class Boj3015 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		// 사람들의 키(index0)와 같은 수가 연달아 몇번 나왔는지(index1) 저장할 스택을 덱으로 선언
		Deque<int[]> stack = new ArrayDeque<>();
		
		// int로 선언한거 때문에 5번은 더 틀림... 제발 범위 확인하자...
		long ans = 0;
		
		// 한 사람씩 순회하면서 처리
		height: for (int i=0; i<n; i++) {
			// 현재 사람의 키
			int cur = Integer.parseInt(br.readLine());
			// 현재 사람과 이전에 이어서 같은 키를 가진 사람 수
			int count = 1;
			
			// 스택이 비었으면 바로 push
			if (stack.isEmpty()) {
				stack.offerFirst(new int[] {cur, count});
				continue;
			}
			
			// 스택의 top이 cur보다 작으면 계속해서 pop
			while (stack.peek()[0] < cur) {
				ans += stack.poll()[1];
				// pop 할때마다 비었는지 체크
				if (stack.isEmpty()) {
					stack.offerFirst(new int[] {cur, count});
					continue height;
				}
			}
			
			// 스택의 top과 cur이 같다면 중복된 횟수와 함께 push
			while (stack.peek()[0] == cur) {
				count = stack.poll()[1] + 1;
				ans += count - 1;
				// pop 할때마다 비었는지 체크
				if (stack.isEmpty()) {
					stack.offerFirst(new int[] {cur, count});
					continue height;
				}
			}

			// 여기까지 왔으면 stack.peek() > cur인 경우
			stack.offerFirst(new int[] {cur, count});
			ans++;
		}
		
		System.out.println(ans);
	}
}
