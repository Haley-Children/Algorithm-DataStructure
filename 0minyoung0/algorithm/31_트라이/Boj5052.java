import java.io.*;
import java.util.*;

public class Boj5052 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		final int ROOT = 1;
		final int MX = 10000 * 10 + 5;
		
		// 테스트 케이스 개수
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			
			// 트라이
			int unused = 2;
			boolean[] chk = new boolean[MX];
			int[][] nxt = new int[MX][26];
			
			for (int i=0; i<MX; i++) {
				Arrays.fill(nxt[i], -1);
			}
			
			// 전화번호의 수
			int n = Integer.parseInt(br.readLine());
			
			// 전화번호 인풋 받기
			String[] input = new String[n];
			for (int i=0; i<n; i++) {
				input[i] = br.readLine();
			}
			
			// 전화번호 사전순으로 정렬
			Arrays.parallelSort(input);
			
			// 일관성 없으면 체크할 boolean 변수
			boolean flag = false;
			
			find: for (String s : input) {
				// 이미 일관성 없으면 탐색 종료
				if (flag) break;
				
				int cur = ROOT;
				for (char c : s.toCharArray()) {
					if (nxt[cur][c2i(c)] == -1) {
						nxt[cur][c2i(c)] = unused++;
					}
					cur = nxt[cur][c2i(c)];
					if (chk[cur]) {
						flag = true;
						break find;
					}
				}
				chk[cur] = true;
			}
			
			// 답 출력
			if (flag) System.out.println("NO");
			else System.out.println("YES");
		}
		
	}
	private static int c2i (char c) {
		return c - '0';
	}
}
