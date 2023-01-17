// boj 1697
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Boj1697 {
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		Queue<Integer> Q = new LinkedList<>();
		int[] time = new int[100001];
		Q.add(n);
		time[n] = 1;
		while(!Q.isEmpty()) {
			int cur = Q.poll();
			for (int dir = 0; dir < 3; dir++) {
				int nx;
				if (dir == 0) nx = cur - 1;
				else if (dir == 1) nx = cur + 1;
				else nx = cur * 2;
				if (nx < 0 || nx > 100000) continue;
				if (time[nx] > 0) continue;
				Q.add(nx);
				time[nx] = time[cur] + 1;
			}
		}
		System.out.println(time[k] - 1);
	}
}