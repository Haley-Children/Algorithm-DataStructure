// 스택.boj2493;

import java.io.*;
import java.util.*;

public class Boj2493 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		// 탑의 높이와 인덱스를 한번에 저장할 스택 선언
		Deque<int[]> stack = new ArrayDeque<>();
		
		for (int i=1; i<=n; i++) {
			int curH = Integer.parseInt(st.nextToken());
			while (true) {
				// 수신 못하는 경우
				if (stack.isEmpty()) {
					sb.append("0 ");
					stack.offerFirst(new int[] {curH, i});
					break;
				}
				// 수신하는 경우
				else if (stack.peek()[0] > curH) {
					sb.append(stack.peek()[1]).append(" ");
					stack.offerFirst(new int[] {curH, i});
					break;
				}
				// 현재 빌딩보다 작은 빌딩이 top에 있는 경우
				else {
					stack.poll();
				}
			}
		}
		// 출력
		System.out.println(sb);
	}
}
