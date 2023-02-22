package 스택의활용.boj3986;

import java.io.*;
import java.util.*;

public class Boj3986 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int ans = 0;
		for (int i=0; i<n; i++) {
			Deque<Character> dq = new ArrayDeque<>();
			String word = br.readLine();
			for (int j=0; j<word.length(); j++) {
				if (word.charAt(j) == 'A') {
					if (dq.isEmpty() || dq.peek() != 'A') {
						dq.offerFirst('A');
					}
					else {
						dq.poll();
					}
				}
				else { // 'B'
					if (dq.isEmpty() || dq.peek() != 'B') {
						dq.offerFirst('B');
					}
					else {
						dq.poll();
					}
				}
			}
			if (dq.size() == 0) {
				ans++;
			}
		}
		System.out.println(ans);
	}
}
