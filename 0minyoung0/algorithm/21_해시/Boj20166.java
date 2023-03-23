import java.io.*;
import java.util.*;

public class Boj20166 {
	static int n, m;
	static char[][] field;
	static Map<String, Integer> hm;
	static int[] dx = {-1,-1,-1,0,0,1,1,1};
	static int[] dy = {-1,0,1,-1,1,-1,0,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 격자의 크기
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		// 신이 좋아하는 문자열의 개수
		int k = Integer.parseInt(st.nextToken());
		
		// 격자의 정보
		field = new char[n][m];
		// 격자 정보 저장
		for (int i=0; i<n; i++) {
			String s = br.readLine();
			for (int j=0; j<m; j++) {
				field[i][j] = s.charAt(j);
			}
		}
		
		// 각 문자열을 만들 수 있는 경우의 수를 HashMap에 저장
		hm = new HashMap<>();
		
		// DFS로 문자열 만들기
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				dfs(i, j, 0, "");
			}
		}
		
		// 답 출력
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<k; i++) {
			String s = br.readLine();
			// 해시맵에 없는 경우
			if (!hm.containsKey(s)) {
				sb.append("0\n");
			}
			
			// 해시맵에 있는 경우
			else {
				sb.append(hm.get(s)).append("\n");
			}
		}
		System.out.println(sb);
	}
	
	private static void dfs(int curX, int curY, int depth, String s) {
		// 종료조건
		if (depth == 5) {
			return;
		}
		
		String newS = s + field[curX][curY];
		
		// 해시맵에 개수 추가하기
		if (!hm.containsKey(newS)) {
			hm.put(newS, 1);
		}else {
			hm.put(newS, hm.get(newS) + 1);
		}
		
		// 인접한 칸에서 dfs 호출
		for (int dir=0; dir<8; dir++) {
			int nx = (curX + dx[dir] + n) % n;
			int ny = (curY + dy[dir] + m) % m;
			dfs(nx, ny, depth+1, newS);
		}
	}
}
