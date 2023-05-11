import java.io.*;
import java.util.*;

public class Boj9328 {
	static int ans;
	static char[][] map;
	static Queue<int[]> q;
	static boolean[][] vis;
	static List<int[]>[] lockedDoor;
	static boolean[] key;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 델타 배열
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		// 테스트 케이스 개수
		int TC = Integer.parseInt(br.readLine());
		
		// 테스트 케이스 별로 실행
		while (TC-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			// 지도의 높이
			int h = Integer.parseInt(st.nextToken());
			// 지도의 너비
			int w = Integer.parseInt(st.nextToken());
			
			// 지도 정보
			map = new char[h][w];
			for (int i=0; i<h; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			// BFS를 위한 큐
			q = new ArrayDeque<>();
			
			// BFS를 위한 방문 표시 배열
			vis = new boolean[h][w];
			
			// 방문하였으나 아직 열 수 없는 문을 저장할 List 배열
			lockedDoor = new List[26];
			for (int i=0; i<26; i++) {
				lockedDoor[i] = new ArrayList<>();
			}
			
			// 상근이가 가지고 있는 키
			key = new boolean[26];
			String keys = br.readLine();
			if (!keys.equals("0")) {
				for (int i=0; i<keys.length(); i++) {
					key[keys.charAt(i)-'a'] = true;
				}
			}
			
			// 상근이가 훔친 문서의 개수
			ans = 0;
			
			// 빌딩의 가장자리 방문 처리
			for (int i=0; i<w; i++) {
				if (map[0][i] != '*') check(new int[] {0, i});
				if (map[h-1][i] != '*') check(new int[] {h-1, i});
			}
			for (int i=1; i<h-1; i++) {
				if (map[i][0] != '*') check(new int[] {i, 0});
				if (map[i][w-1] != '*') check(new int[] {i, w-1});
			}
			
			// 시뮬레이션
			while (!q.isEmpty()) {
				int[] cur = q.poll();
				
				for (int dir=0; dir<4; dir++) {
					int nx = cur[0] + dx[dir];
					int ny = cur[1] + dy[dir];
					
					// out of index
					if (nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
					
					// 이미 방문한 좌표
					if (vis[nx][ny]) continue;
					
					// 벽인 경우
					if (map[nx][ny] == '*') continue;
					
					// 방문하기
					check(new int[] {nx, ny});
				}
			}
			
			// 답 출력
			System.out.println(ans);
		}
	}
	
	// 특정 좌표에 방문했을때 처리
	private static void check(int[] point) {
		vis[point[0]][point[1]] = true;
		char curPoint = map[point[0]][point[1]];
		
		// 열쇠인 경우
		if ('a' <= curPoint && curPoint <= 'z') {
			
			// 열쇠 챙기기
			key[curPoint - 'a'] = true;
			map[point[0]][point[1]] = '.';
			
			// 방문했었지만 해당 열쇠가 없어서 방문하지 못했던 문이 있으면
			for (int[] door : lockedDoor[curPoint - 'a']) {
				
				// 문 지우고 큐에 넣기
				map[door[0]][door[1]] = '.';
				q.offer(new int[] {door[0], door[1]});
			}
			lockedDoor[curPoint - 'a'].clear();
		}
		
		// 문인 경우
		else if ('A' <= curPoint && curPoint <= 'Z') {
			
			// 이미 열쇠가 있는 경우
			if (key[curPoint - 'A']) {
				
				// 문 지우기
				map[point[0]][point[1]] = '.';
			}
			
			// 열쇠가 없는 경우
			else {
				
				// lockedDoor에 저장하고 리턴
				lockedDoor[curPoint - 'A'].add(new int[] {point[0], point[1]});
				return;
			}
		}
		
		// 서류가 있는 경우
		else if (curPoint == '$') {
			
			// 서류 챙기기
			ans++;
			map[point[0]][point[1]] = '.';
		}
		
		// 다음 탐색을 위해 큐에 넣기
		q.offer(new int[] {point[0], point[1]});
	}
}
