import java.io.*;
import java.util.*;

public class Boj19238 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 델타배열
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        
        // 지도 크기
        int n = Integer.parseInt(st.nextToken());
        // 승객 수
        int m = Integer.parseInt(st.nextToken());
        // 연료
        int fuel = Integer.parseInt(st.nextToken());
        
        // 벽 위치 (1-indexed)
        int[][] map = new int[n+1][n+1];
    	for (int i=1; i<=n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for (int j=1; j<=n; j++) {
    			if (st.nextToken().equals("1")) {
    				map[i][j] = -1;
    			}
    		}
    	}
        
    	// 택시의 현재 위치
    	st = new StringTokenizer(br.readLine());
    	int cx = Integer.parseInt(st.nextToken());
    	int cy = Integer.parseInt(st.nextToken());
    	
    	// 승객의 승하차 위치 정보
    	int[][] data = new int[m+1][4];
    	for (int i=1; i<=m; i++) {
    		st = new StringTokenizer(br.readLine());
    		for (int j=0; j<4; j++) {
    			data[i][j] = Integer.parseInt(st.nextToken());
    		}
    		map[data[i][0]][data[i][1]] = i;
    	}
    	
    	// BFS에 사용할 큐
    	Queue<int[]> q = new ArrayDeque<>();
    	// 최단 거리의 승객이 여러명이면 넣어서 정렬할 리스트
    	List<int[]> list = new ArrayList<>();
    	// BFS에 사용할 거리 배열
    	int[][] dis;
    	int d;
    	int passenger;
    	
    	// 승객 m명 태우기
    	for (int turn=0; turn<m; turn++) {
    		// 지금 위치에 손님이 있는지 확인
    		if (map[cx][cy] > 0) {
	    		// 손님 번호
	    		passenger = map[cx][cy];
	    		// 손님을 맵에서 지우기
	    		map[cx][cy] = 0;
    		}
    		else {
	    		// 현재 위치에서 BFS해서 가까운 승객 찾기
	    		dis = new int[n+1][n+1];
	    		q.add(new int[] {cx, cy});
	    		dis[cx][cy] = 1;
	    		boolean find = false;
	    		d = 0;
	    		while (!q.isEmpty()) {
	    			int[] cur = q.poll();
	    			for (int dir=0; dir<4; dir++) {
	    				int nx = cur[0] + dx[dir];
	    				int ny = cur[1] + dy[dir];
	    				// out of index
	    				if (nx<=0||nx>n||ny<=0||ny>n) continue;
	    				// 벽이거나 방문한 경우
	    				if (map[nx][ny] == -1 || dis[nx][ny] > 0) continue;
	    				// 승객이 있는 칸이고
	    				if (map[nx][ny] > 0) {
	    					// 처음 찾았거나 처음 찾은 좌표와 거리가 같다면
	    					if (!find || (find && d == dis[cur[0]][cur[1]])) {
	    						// find를 true로 바꾸고 d를 갱신
	    						find = true;
	    						d = dis[cur[0]][cur[1]];
	    						// 리스트에 추가
	    						list.add(new int[] {nx,ny});
	    					}
	    				}
	    				// 승객이 없는 칸이면
	    				else {
	    					// 아직 못찾은 경우에만 BFS 유지
	    					if (!find) {
	    						q.add(new int[] {nx,ny});
	    						dis[nx][ny] = dis[cur[0]][cur[1]] + 1;
	    					}
	    				}
	    			}
	    		}
	    		if (d == 0) {
	    			System.out.println(-1);
	    			return;
	    		}
	    		// 큐 클리어
	    		q.clear();
	    		
	    		// 다음 좌표까지 연료 소모
	    		fuel -= d;
	    		if (fuel < 0) {
	    			System.out.println(-1);
	    			return;
	    		}
	    		
	    		// 최적의 손님 찾기
	    		Collections.sort(list, new Comparator<int[]>() {
	    			public int compare(int[] o1, int[] o2) {
	    				return o1[0]!=o2[0]? o1[0]-o2[0] : o1[1]-o2[1];
	    			}
	    		});
	    		// 다음 좌표로 이동
	    		cx = list.get(0)[0];
	    		cy = list.get(0)[1];
	    		
	    		// 리스트 클리어
	    		list.clear();
	    		
	    		// 손님 번호
	    		passenger = map[cx][cy];
	    		
	    		// 손님을 맵에서 지우기
	    		map[cx][cy] = 0;
    		}
    		
    		// 현재 위치에서부터 손님의 도착지점까지 BFS해서 거리 측정
    		dis = new int[n+1][n+1];
    		q.add(new int[] {cx, cy});
    		dis[cx][cy] = 1;
    		d = 0;
    		BFS: while (!q.isEmpty()) {
    			int[] cur = q.poll();
    			for (int dir=0; dir<4; dir++) {
    				int nx = cur[0] + dx[dir];
    				int ny = cur[1] + dy[dir];
    				// out of index
    				if (nx<=0||nx>n||ny<=0||ny>n) continue;
    				// 벽이거나 방문한 경우
    				if (map[nx][ny] == -1 || dis[nx][ny] > 0) continue;
    				// 손님의 도착지점인 경우
    				if (nx == data[passenger][2] && ny == data[passenger][3]) {
    					d = dis[cur[0]][cur[1]];
    					break BFS;
    				}
    				// 그 외의 경우
    				q.add(new int[] {nx, ny});
    				dis[nx][ny] = dis[cur[0]][cur[1]] + 1;
    			}
    		}
    		if (d == 0) {
    			System.out.println(-1);
    			return;
    		}
    		// 큐 클리어
    		q.clear();
    		
    		// 다음 좌표까지 연료 소모
    		fuel -= d;
    		if (fuel < 0) {
    			System.out.println(-1);
    			return;
    		}

    		// 연료 충전
    		fuel += 2*d;
    		
    		// 다음 좌표로 이동
    		cx = data[passenger][2];
    		cy = data[passenger][3];
    	}
    	
    	// m명 다 태웠다면 답 출력
        System.out.println(fuel);
    }
}