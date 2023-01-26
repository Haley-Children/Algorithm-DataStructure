// 시뮬레이션.boj14891;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14891 {
	static boolean[][] isS = new boolean[4][8];
	static int[] index = new int[4];
	static boolean[] vis = new boolean[4];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String s; 
		for (int i=0; i<4; i++) {
			s = br.readLine();
			for (int j=0; j<8; j++) {
				if(s.charAt(j) == '1') isS[i][j] = true;
			}
		}
		int k = Integer.parseInt(br.readLine());
		for (int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			turnGear(Integer.parseInt(st.nextToken()) - 1, -Integer.parseInt(st.nextToken()));
			for (int j=0; j<4; j++) vis[j] = false;
		}
		int ans = 0;
		int temp = 1;
		for(int i=0; i<4; i++) {
			if (isS[i][index[i]]) ans += temp;
			temp *= 2;
		}
		System.out.println(ans);
	}
	private static void turnGear(int gearNum, int dir) {
		vis[gearNum] = true;
		if (gearNum != 0 && !vis[gearNum-1] && isS[gearNum-1][adjustNum(index[gearNum-1]+2)] ^ isS[gearNum][adjustNum(index[gearNum]-2)]) {
			turnGear(gearNum-1, -dir);
		}
		if (gearNum != 3 && !vis[gearNum+1] && isS[gearNum][adjustNum(index[gearNum]+2)] ^ isS[gearNum+1][adjustNum(index[gearNum+1]-2)]) {
			turnGear(gearNum+1, -dir);
		}
		index[gearNum] = adjustNum(index[gearNum]+dir);
	}
	private static int adjustNum(int n) {
		return (n%8+8)%8;
	}
}