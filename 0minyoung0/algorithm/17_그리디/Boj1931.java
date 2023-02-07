// 그리디.boj1931;

import java.io.*;
import java.util.*;

public class Boj1931 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		List<int[]> meetings = new ArrayList<>();
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int temp1 = Integer.parseInt(st.nextToken());
			int temp2 = Integer.parseInt(st.nextToken());
			meetings.add(new int[] {temp1, temp2});
		}
		Collections.sort(meetings, new Comparator<int[]>(){
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] != o2[1] ? o1[1] - o2[1] : o1[0] - o2[0];
			}
		});
		int ans = 0;
		int timer = 0;
		for (int i=0; i<n; i++) {
			if (meetings.get(i)[0] >= timer) {
				timer = meetings.get(i)[1];
				ans++;
			}
		}
		System.out.println(ans);
	}
}
