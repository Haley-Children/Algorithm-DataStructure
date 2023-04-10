import java.io.*;
import java.util.*;

public class Boj14425 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 트라이
		final int ROOT = 1;
		int unused = 2;
		final int MX = 10000 * 500 + 5;
		boolean[] chk = new boolean[MX];
		int[][] nxt = new int[MX][26];
		
		for (int i=0; i<MX; i++) {
			Arrays.fill(nxt[i], -1);
		}
		
		// 집합 S에 있는 문자열의 개수
		int N = Integer.parseInt(st.nextToken());
		// 검사할 문자열의 개수
		int M = Integer.parseInt(st.nextToken());
		
		// N개의 문자열을 트라이에 저장
		while (N-- > 0) {
			int cur = ROOT;
			char[] input = br.readLine().toCharArray();
			for (char c : input) {
				if (nxt[cur][c2i(c)] == -1) {
					nxt[cur][c2i(c)] = unused++;
				}
				cur = nxt[cur][c2i(c)];
			}
			chk[cur] = true;
		}
		
		// 검사한 문자열 중 몇개가 집합 S에 포함되었는지 셀 변수
		int ans = 0;
		
		// 문자열 검사
		find: while (M-- > 0) {
			int cur = ROOT;
			char[] input = br.readLine().toCharArray();
			for (char c : input) {
				if (nxt[cur][c2i(c)] == -1) continue find;
				cur = nxt[cur][c2i(c)];
			}
			if (chk[cur]) ans++;
		}
		
		// 답 출력
		System.out.println(ans);
	}
	
	private static int c2i (char c) {
		return c-'a';
	}
}
