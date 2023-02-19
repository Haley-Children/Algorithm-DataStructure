// 연결리스트.boj5397;

import java.io.*;
import java.util.*;

public class Boj5397 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 테스트 케이스 개수 t
		int t = Integer.parseInt(br.readLine());
		
		for (int i=0; i<t; i++) {
			// 문자열 받기
			String s = br.readLine();
			
			// LinkedList와 ListIterator 선언
			LinkedList<Character> ll = new LinkedList<>();
			ListIterator<Character> iter = ll.listIterator();
			
			// 문자열에서 하나씩 접근하면서 수행하기
			for (int j=0; j<s.length(); j++) {
				char input = s.charAt(j);
				// <인 경우
				if (input == '<') {
					if (iter.hasPrevious()) {
						iter.previous();
					}
				}
				// >인 경우
				else if (input == '>') {
					if (iter.hasNext()) {
						iter.next();
					}
				}
				// -인 경우
				else if (input == '-') {
					if (iter.hasPrevious()) {
						iter.previous();
						iter.remove();
					}
				}
				// 그외에 입력할 값인 경우
				else {
					iter.add(input);
				}
			}
			
			// linkedlist 순회하면서 sb에 append
			for (char c : ll) {
				sb.append(c);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
