// dp.boj12852;

import java.io.*;
import java.util.*;

public class Boj12852 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] dis = new int[n+1];
		int[] pre = new int[n+1];
		for (int i=1; i<n; i++) {
			if (i*3 <= n && (pre[i*3] == 0 || dis[i*3] > dis[i] + 1)) {
				pre[i*3] = i;
				dis[i*3] = dis[i] + 1;
			}
			if (i*2 <= n && (pre[i*2] == 0 || dis[i*2] > dis[i] + 1)) {
				pre[i*2] = i;
				dis[i*2] = dis[i] + 1;
			}
			if (i+1 <= n && (pre[i+1] == 0 || dis[i+1] > dis[i] + 1)) {
				pre[i+1] = i;
				dis[i+1] = dis[i] + 1;
			}
		}
		System.out.println(dis[n]);
		while (n != 1) {
			System.out.print(n+" ");
			n = pre[n];
		}
		System.out.println(1);
	}
}
