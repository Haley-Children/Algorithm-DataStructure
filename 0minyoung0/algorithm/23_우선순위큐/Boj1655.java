import java.io.*;
import java.util.*;

public class Boj1655 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 백준이가 외치는 정수의 개수
		int n = Integer.parseInt(br.readLine());
		
		// 정수를 저장할 우선순위 큐
		PriorityQueue<Integer> pq1 = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> pq2 = new PriorityQueue<>();
		
		// 외치는 숫자 저장하기
		int temp = Integer.parseInt(br.readLine());
		pq1.add(temp);
		sb.append(pq1.peek()).append("\n");
		
		while (--n > 0) {
			temp = Integer.parseInt(br.readLine());
			
			// pq1의 peek보다 작으면 pq1에, 크면 pq2에 저장
			if (temp < pq1.peek()) {
				pq1.add(temp);
			}else {
				pq2.add(temp);
			}
			
			// pq1의 사이즈가 pq2의 사이즈보다 2이상 크면 옮기기
			if (pq1.size() - pq2.size() >= 2) {
				pq2.add(pq1.poll());
			}
			
			// pq2가 pq1보다 크면 옮기기
			else if (pq1.size() - pq2.size() < 0) {
				pq1.add(pq2.poll());
			}
			
			// 중간값 스트링 빌더에 저장
			sb.append(pq1.peek()).append("\n");
		}
		
		// 답 출력
		System.out.println(sb);
		
	}
}
