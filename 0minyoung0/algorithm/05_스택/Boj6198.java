import java.io.*;
import java.util.*;

public class Boj6198 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 빌딩의 개수
		int n = Integer.parseInt(br.readLine());
		
		// 빌딩을 저장할 스택을 덱으로 선언
		Deque<Integer> stack = new ArrayDeque<>();
		
		// 답을 저장할 변수 선언 <- int범위 넘어감!!!
		long ans = 0;
		
		// 빌딩의 높이를 스택에 저장하면서 계산
		for (int i=0; i<n; i++) {
			// 현재 확인중인 빌딩의 높이
			int cur = Integer.parseInt(br.readLine());
			
			// 스택이 비지 않았고 스택의 top이 현재 확인중인 높이보다 작거나 같으면 pop <- 반복
			while (!stack.isEmpty() && stack.peek() <= cur) {
				stack.poll();
			}
			
			// 스택이 비었거나 스택의 top이 현재 확인중인 높이보다 커지면
			// 스택의 크기만큼 ans에 더한 후 push
			ans += stack.size();
			stack.offerFirst(cur);
		}
		
		// 정답 출력
		System.out.println(ans);
	}
}
