// 덱.boj1021;

import java.io.*;
import java.util.*;

public class Boj1021 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		// 데이터를 저장할 큐 선언
		Queue<Integer> queue = new ArrayDeque<>();
		// 큐에 데이터 저장 (데이터는 최초의 위치와 같도록)
		for (int i=1; i<=n; i++) {
			queue.offer(i);
		}
		
		int ans = 0;
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<m; i++) {
			int target = Integer.parseInt(st.nextToken());
			int cnt = 0;
			while (target != queue.peek()) {
				queue.offer(queue.poll());
				cnt++;
			}
			cnt = Math.min(cnt, queue.size()-cnt);
			queue.poll();
			ans += cnt;
		}
		
		System.out.println(ans);
	}
}
