// 덱.boj5430;

import java.io.*;
import java.util.*;

public class Boj5430 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		test: for (int i=0; i<T; i++) {
			String p = br.readLine();
			int n = Integer.parseInt(br.readLine());
			String arr = br.readLine();
			// 데이터 저장할 deque 선언
			Deque<Integer> dq = new ArrayDeque<>();
			int temp = 0;
			for (int j=1; j<arr.length(); j++) {
				if (arr.charAt(j) == ',' || arr.charAt(j) == ']') {
					if (temp != 0) {
						dq.offer(temp);
						temp = 0;
					}
				}
				else {
					temp *= 10;
					temp += arr.charAt(j) - '0';
				}
			}
			
			// 연산
			boolean isReversed = false;
			for (int j=0; j<p.length(); j++) {
				char oper = p.charAt(j);
				if (oper == 'R') {
					isReversed = !isReversed;
				}
				else { // 'D'
					if (dq.isEmpty()) {
						sb.append("error\n");
						continue test;
					}
					if (!isReversed) {
						dq.poll();
					}
					else { // Reversed
						dq.pollLast();
					}
				}
			}
			
			// 출력
			sb.append("[");
			while(dq.size() > 1) {
				if (!isReversed) {
					sb.append(dq.poll()).append(",");
				}
				else { // Reversed
					sb.append(dq.pollLast()).append(",");
				}
			}
			if (dq.size() == 1) {
				sb.append(dq.poll());
			}
			sb.append("]\n");
		}
		System.out.println(sb);
	}
}
