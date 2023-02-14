// 연결리스트.boj1158;

import java.io.*;
import java.util.*;

public class Boj1158 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		LinkedList<Integer> ll = new LinkedList<>();
		// linkedlist 채우기
		for (int i=1; i<=n; i++) {
			ll.add(i);
		}
		
		// listiterator 선언
		ListIterator<Integer> iter = ll.listIterator();
		
		sb.append("<");
		
		// 숫자 n-1개 뽑아내기
		for (int i=0; i<n-1; i++) {
			for (int j=0; j<k; j++) {
				if (!iter.hasNext()) {
					iter = ll.listIterator();
				}
				iter.next();
			}
			iter.previous();
			sb.append(iter.next()).append(", ");
			iter.remove();
		}
		sb.append(ll.getFirst()).append(">");
		System.out.println(sb);
	}
}
