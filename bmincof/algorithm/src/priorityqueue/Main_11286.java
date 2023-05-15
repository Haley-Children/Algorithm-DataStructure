package priorityqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_11286 {

	public static void main(String args[]) throws IOException{
		// 입력 값 처리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		int N = Integer.parseInt(br.readLine());
        int[] x = new int[N];
		
		for(int i=0; i<N; i++) {
			x[i] = Integer.parseInt(br.readLine());
		}
		
        // 변수 선언
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
			public int compare(Integer x1, Integer x2) {
				if(Math.abs(x1) > Math.abs(x2)) {
					return 1;
				}
				else if(Math.abs(x1) < Math.abs(x2)) {
					return -1;
				}
				else {
					return x1 > x2 ? 1 : -1;
				}
			}
		});
		
        // 풀이
		for(int i=0; i<N; i++) {
			if(x[i] != 0) {
				pq.add(x[i]);
			}
			else {
				if(!pq.isEmpty()) {
					System.out.println(pq.poll());
				}
				else {
					System.out.println(0);
				}
			}
		} // end of for
		
	} // end of main		
}
