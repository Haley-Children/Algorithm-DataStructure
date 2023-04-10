import java.io.*;
import java.util.*;

public class Boj9202 {
	static int maxScore;
	static String longestWord;
	static int findWordCnt;
	static char[][] board;
	
	// 단어 길이에 따른 점수
	static int[] score = {0, 0, 0, 1, 1, 2, 3, 5, 11};
	
	// 단어사전을 트라이로 구현
	static final int ROOT = 1;
	static final int MX = 300000 * 8 + 5;
	static int unused = 2;
	static int[][] nxt = new int[MX][26];
	static boolean[] chk = new boolean[MX];
	
	static TreeSet<String> set;
	static int[] dx = {-1,-1,-1,0,0,1,1,1};
	static int[] dy = {-1,0,1,-1,1,-1,0,1};
	static boolean[][] vis = new boolean[4][4];
	static char[] findingWord = new char[8];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 단어사전에 들어있는 단어의 수
		int w = Integer.parseInt(br.readLine());
		
		for (int i=0; i<MX; i++) {
			Arrays.fill(nxt[i], -1);
		}
		
		// 트라이에 단어 저장
		while (w-- > 0) {
			char[] input = br.readLine().toCharArray();
			int cur = ROOT;
			for (char c : input) {
				if (nxt[cur][c2i(c)] == -1) {
					nxt[cur][c2i(c)] = unused++;
				}
				cur = nxt[cur][c2i(c)];
			}
			chk[cur] = true;
		}
		br.readLine();
		
		// Boggle 보드의 개수
		int b = Integer.parseInt(br.readLine());
		
		// Boggle 보드마다 최대 점수, 가장 긴 단어, 찾은 단어의 개수 출력
		while (b-- > 0) {
			
			// Boggle 보드 정보 저장
			board = new char[4][];
			for (int i=0; i<4; i++) {
				board[i] = br.readLine().toCharArray();
			}
			if (b != 0) br.readLine();
			
			// 찾은 단어를 저장할 해시셋
			set = new TreeSet<>();
			
			// 백트래킹으로 단어 찾기
			for (int i=0; i<4; i++) {
				for (int j=0; j<4; j++) {
					vis[i][j] = true;
					findingWord[0] = board[i][j];
					backTracking(i, j, 1);
					vis[i][j] = false;
				}
			}
			
			// 해시셋으로 답 찾기
			maxScore = 0;
			longestWord = "";
			findWordCnt = set.size();
			
			for (String s : set) {
				maxScore += score[s.length()];
				if (longestWord.length() < s.length()) {
					longestWord = s;
				}
			}
			
			// 답 출력
			System.out.println(maxScore + " " + longestWord + " " + findWordCnt);
		}
		
	}
	
	private static void backTracking(int x, int y, int k) {
		// 단어 찾기
		int cur = ROOT;
		boolean finding = true;
		for (int i=0; i<k; i++) {
			if (nxt[cur][c2i(findingWord[i])] == -1) {
				finding = false;
				break;
			}
			cur = nxt[cur][c2i(findingWord[i])];
		}
		// 단어를 찾은 경우
		if (finding && chk[cur]) {
			
			// 해시셋에 찾은 단어 저장
			String s = "";
			for (int i=0; i<k; i++) {
				s += findingWord[i];
			}
			set.add(s);
		}
		
		// k가 8이면 더이상 재귀호출 안하고 리턴
		if (k == 8) return;
		
		// 다음 재귀호출
		for (int dir=0; dir<8; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			// out of index
			if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;
			
			// 방문한경우
			if (vis[nx][ny]) continue;
			
			// 재귀호출
			vis[nx][ny] = true;
			findingWord[k] = board[nx][ny];
			backTracking(nx, ny, k+1);
			vis[nx][ny] = false;
		}
	}
	
	private static int c2i(char c) {
		return c - 'A';
	}
}
