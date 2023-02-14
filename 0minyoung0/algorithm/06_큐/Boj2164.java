// 큐.boj2164;

import java.io.*;
import java.util.*;

public class Boj2164 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		// 카드를 저장할 큐
		Queue<Integer> queue = new ArrayDeque<>();
		// 카드 초기상태로 저장
		for (int i=1; i<=n; i++) {
			queue.offer(i);
		}
		
		// 손에 드는걸 이렇게 표현하고 싶었음
		int hand = 0;
		
		// 한장 남을때까지 시행
		while (queue.size() != 1) {
			queue.poll();
			hand = queue.poll();
			queue.offer(hand);
		}
		
		// 남은 한장 출력
		System.out.println(queue.peek());
	}
}
