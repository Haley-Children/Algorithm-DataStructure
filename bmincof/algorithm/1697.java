import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		if(n==k) {
			System.out.println(0);
			return;
		}
		
		int[] move = new int[] {1,-1,2};
		
		int[] pos = new int[200010];
		pos[k] = 1;
		
		int[] time = new int[200010];
		Queue<Integer> q = new LinkedList<>();
		
		time[n] = 1;
		q.add(n);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int i = 0; i < 3; i++) {
				int nx = cur; 
				
				if(i==2) nx *= move[i];
				else nx += move[i];

				if(nx < 0 || nx > 100000) continue;
				if(pos[nx] == 1) {
					System.out.println(time[cur]);
					return;
				}
				if(time[nx] > 0) continue;
				
				
				time[nx] = time[cur] + 1;
				q.add(nx);
			}
		}
		
	}
	
}

