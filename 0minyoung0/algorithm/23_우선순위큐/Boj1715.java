import java.io.*;
import java.util.*;

public class Boj1715 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 카드 묶음의 개수
		int n = Integer.parseInt(br.readLine());
		
		// 카드 묶음을 저장할 우선순위 큐
		PriorityQueue<Long> pq = new PriorityQueue<>();
		
		// 카드 묶음 저장
		while (n-- > 0) {
			pq.add(Long.parseLong(br.readLine()));
		}
		
		// 답을 저장할 변수
		long ans = 0;
		
		// 카드 합치기
		while (pq.size() > 1) {
			long temp = pq.poll() + pq.poll();
			ans += temp;
			pq.add(temp);
		}
		
		// 답 출력
		System.out.println(ans);
		
	}
}
