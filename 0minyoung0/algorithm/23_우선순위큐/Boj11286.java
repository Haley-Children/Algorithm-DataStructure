import java.io.*;
import java.util.*;

public class Boj11286 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 연산의 개수
		int n = Integer.parseInt(br.readLine());
		
		// 절대값 우선순위 큐
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){
			public int compare(Integer o1, Integer o2) {
				if (Math.abs(o1)!=Math.abs(o2)) return Math.abs(o1) - Math.abs(o2);
				return o1 - o2;
			}
		});
		
		// 연산 처리하기
		StringBuilder sb = new StringBuilder();
		while (n-- > 0) {
			
			// 처리 할 연산
			int x = Integer.parseInt(br.readLine());
			
			// 값 추가
			if (x != 0) {
				pq.add(x);
			}
			
			// 값 출력
			else {
				if (pq.isEmpty()) {
					sb.append("0\n");
				}else {
					sb.append(pq.poll()).append("\n");
				}
			}
		}
		System.out.println(sb);
	}
}
