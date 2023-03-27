import java.io.*;
import java.util.*;

public class Boj2075 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 표의 크기
		int n = Integer.parseInt(br.readLine());
		
		// 우선순위 큐
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		// 혹시 되는지 궁금하니까 n*n 한번에 우선순위큐에 때려넣기
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				pq.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		// n번째 큰 숫자 찾기
		for (int i=0; i<n-1; i++) {
			pq.poll();
		}
		System.out.println(pq.poll());
	}
}
