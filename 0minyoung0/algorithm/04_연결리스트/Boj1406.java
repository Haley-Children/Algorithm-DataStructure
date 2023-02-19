// 연결리스트.boj1406;

import java.io.*;
import java.util.*;

public class Boj1406 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 문자열 받기
		String s = br.readLine();

		// 링크드리스트에 문자열 입력
		LinkedList<Character> ll = new LinkedList<>();
		for (int i=0; i<s.length(); i++) {
			ll.offer(s.charAt(i));
		}
		
		// listIterator 선언
		ListIterator<Character> iter = ll.listIterator();
		// listIterator 맨 뒤로 보내기
		while(iter.hasNext()) {
			iter.next();
		}
		
		// 명령어 개수 m 받기
		int m = Integer.parseInt(br.readLine());
		
		// 명령어 m번 처리하기
		for (int i=0; i<m; i++) {
			// stringToknizer에 명령 한줄 받기
			String oper = br.readLine();
			// 명령어 char로 뽑아오기
			char o = oper.charAt(0);
			
			if (o == 'L') {
				if (iter.hasPrevious()) {
					iter.previous();
				}
			}
			else if (o == 'D') {
				if (iter.hasNext()) {
					iter.next();
				}
			}
			else if (o == 'B') {
				if (iter.hasPrevious()) {
					iter.previous();
					// ListIterator.remove()는 next(), previous()로 반환된 가장 최근 값을 제거
					iter.remove();
				}
			}
			else { // o == 'P'
				char $ = oper.charAt(2);
				iter.add($);
			}
		}
		
		// LinkedList 출력
		for (char c : ll) {
			sb.append(c);
		}
		System.out.println(sb);
	}
}
