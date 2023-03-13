import java.io.*;
import java.util.*;

public class Boj13913 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 수빈이 위치 n, 동생 위치 k
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		// 시작부터 찾았다면 결과 출력 후 종료
		if (n == k) {
			System.out.println("0\n" + n);
			return;
		}
		
		// 도착한 시간 data[i][0], 이전 좌표 data[i][1]
		int[][] data = new int[100001][2];
		
		// 처음 위치에 대해 초기화
		data[n][0] = 1;
		data[n][1] = -1;
		
		// BFS(큐)와 출력(스택)을 위한 덱 선언
		Deque<Integer> dq = new ArrayDeque<>();
		dq.offer(n);
		
		// BFS 시작
		BFS: while(!dq.isEmpty()) {
			// 현재 좌표
			int curX = dq.poll();
			for (int dir=0; dir<3; dir++) {
				// 다음 좌표
				int nx = nextPoint(curX, dir);
				// outOfIndex
				if (nx < 0 || nx > 100000) continue;
				// 방문한 적 있는 좌표일 경우
				if (data[nx][0] > 0) continue;
				// 처음 방문하는 좌표일 경우
				// 만약 동생을 찾았다면
				if (nx == k) {
					sb.append(data[curX][0]).append("\n");
					data[k][1] = curX;
					break BFS;
				}
				// 동생을 못찾았다면
				// 해당 좌표 최소 도달 시간 저장
				data[nx][0] = data[curX][0] + 1;
				// 해당 좌표 이전의 좌표 저장
				data[nx][1] = curX;
				// nx를 큐에 추가
				dq.offer(nx);
			}
		}
		// BFS가 끝났고 덱을 스택으로 사용하기 위해 clear해줌
		dq.clear();
		
		// k부터 이전 좌표를 순회하며 스택에 추가
		int cur = k;
		while (cur != -1) {
			// 스택에 넣기
			dq.offerFirst(cur);
			// cur이 이전좌표를 가리키도록 하기
			cur = data[cur][1];
		}
		
		// 스택에서 꺼내며 출력
		while (!dq.isEmpty()) {
			sb.append(dq.poll()).append(" ");
		}
		
		System.out.println(sb);
	}
	
	// 현재 위치와 방향 인덱스를 주면 다음 좌표를 리턴하는 메소드
	private static int nextPoint(int curX, int dir) {
		switch(dir) {
			case 0: return curX - 1;
			case 1: return curX + 1;
			default: return curX * 2;
		}
	}
}
