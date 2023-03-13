// 스택.boj10773;

import java.io.*;
import java.util.*;

public class Boj10773 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int k = Integer.parseInt(br.readLine());
		Deque<Integer> stack = new ArrayDeque<>();
		
		for (int i=0; i<k; i++) {
			int input = Integer.parseInt(br.readLine());
			if (input == 0) {
				stack.poll();
			}
			else {
				stack.offerFirst(input);
			}
		}
		
		int sum = 0;
		for (int num : stack) {
			sum += num;
		}
		System.out.println(sum);
	}
}
