package hash;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_20166 {
	static int N, M, K;
	static char[][] map;
	static Map<String, Integer> cnt;
	static StringBuilder sb = new StringBuilder();
	
	static int[] dx = {1,-1,0,0,-1,1,1,-1};
	static int[] dy = {0,0,1,-1,-1,-1,1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	
		map = new char[N][M];
		cnt = new HashMap<>();
		String[] words = new String[K];
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for(int i = 0; i < K; i++) {
			String word = br.readLine();
			cnt.put(word, 0);
			words[i] = word;
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				bt(0, r, c);
			}
		}
		
		for(int i = 0; i < K; i++) {
			sb.append(cnt.get(words[i])).append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	static void bt(int k, int r, int c) {
		if(k == 5) {
			return;
		}
		
		sb.append(map[r][c]);
		String word = sb.toString();
		
		if(cnt.containsKey(word)) {
			cnt.put(word, cnt.get(word) + 1);
		}
		
		for(int dir = 0; dir < 8; dir++) {
			int nx = (r + dx[dir] + N) % N;
			int ny = (c + dy[dir] + M) % M;
			
			bt(k+1, nx, ny);
		}
		
		sb.deleteCharAt(k);
	}
	
}

