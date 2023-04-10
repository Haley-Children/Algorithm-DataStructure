package priorityqueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_1927 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			int cmd = Integer.parseInt(br.readLine());
			
			if(cmd == 0) {
				if(pq.isEmpty()) {
					sb.append(0);
				} else {
					sb.append(pq.poll());
				}
				
				sb.append("\n");
			} else {
				pq.offer(cmd);
			}
		}
		
		System.out.println(sb);
		
	}
}

