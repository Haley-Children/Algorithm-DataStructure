import java.io.*;
import java.util.*;

public class Boj16934 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 트라이
		final int ROOT = 1;
		final int MX = 100000 * 10 + 5;
		int unused = 2;
		int[][] nxt = new int[MX][26];
		for (int i=0; i<MX; i++) {
			Arrays.fill(nxt[i], -1);
		}
		int[] chk = new int[MX];
		
		// 가입한 유저의 수
		int N = Integer.parseInt(br.readLine());
		
		// 유저 가입 시키며 별칭을 스트링 빌더에 저장
		while (N-- > 0) {
			char[] nickname = br.readLine().toCharArray();
			boolean find = false;
			int cur = ROOT;
			for (char c : nickname) {
				if (!find) {
					sb.append(c);
				}
				if (nxt[cur][c2i(c)] == -1) {
					find = true;
					nxt[cur][c2i(c)] = unused++;
				}
				cur = nxt[cur][c2i(c)];
			}
			chk[cur]++;
			if (!find) {
				if (chk[cur] != 1) sb.append(chk[cur]);
			}
			sb.append("\n");
		}
		
		// 답 출력
		System.out.println(sb);
	}
	
	private static int c2i(char c) {
		return c - 'a';
	}
}
