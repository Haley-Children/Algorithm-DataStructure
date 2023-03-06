import java.io.*;
import java.util.*;

public class Boj4991 {
    static int w, h, d, ans;
    static char[][] map;
    static String s;
    static int[][] point;
    static Queue<int[]> q = new ArrayDeque<>();;
    static int[][] p_dis;
    static int[][] dis;
    static int[] route;
    static boolean[] vis;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 델타배열
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        
        // 방의 가로, 세로 크기
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        while (w!=0 && h!=0) {
            // 방 정보
            map = new char[h][w];
            // 시작점과 더러운칸 좌표
            point = new int[11][2];
            // 더러운 칸 개수
            d = 0;
            
            // 방 정보와 시작점, 더러운칸 좌표 저장
            for (int i=0; i<h; i++) {
                s = br.readLine();
                for (int j=0; j<w; j++) {
                    map[i][j] = s.charAt(j);
                    if (map[i][j] == '*') {
                        point[++d][0] = i;
                        point[d][1] = j;
                    }else if (map[i][j] == 'o') {
                        point[0][0] = i;
                        point[0][1] = j;
                    }
                }
            }
            
            // 시작점과 더러운칸 사이의 거리를 저장할 배열
            p_dis = new int[d+1][d+1];
            
            // 이번 루프가 불가능인지 저장할 boolean 변수 선언
            boolean impossible = false;
            
            // 시작점에서부터 더러운 점들까지 각각 한번씩 큐에 넣고 BFS
            for (int i=0; i<d; i++) {
            	// 거리 계산을 위한 배열
            	dis = new int[h][w];
            	
            	q.add(new int[] {point[i][0], point[i][1]});
            	dis[point[i][0]][point[i][1]] = 1;
            	
            	while(!q.isEmpty()) {
            		int[] cur = q.poll();
                    for (int dir=0; dir<4; dir++) {
                        int nx = cur[0] + dx[dir];
                        int ny = cur[1] + dy[dir];
                        // out of index
                        if (nx<0 || nx>=h || ny<0 || ny>=w) continue;
                        // 방문한적이 있거나 가구인 경우
                        if (dis[nx][ny] > 0 || map[nx][ny] == 'x') continue;
                        // 큐에 넣기
                        q.add(new int[] {nx, ny});
                        dis[nx][ny] = dis[cur[0]][cur[1]] + 1;
                    }
            	}
            	
            	// i가 0일때 : 시작점에 대한 BFS일때
            	// 더러운칸을 모두 방문했는지 check
            	if (i == 0) {
                    for (int j=1; j<=d; j++) {
                    	// 더러운 칸을 하나라도 방문하지 않았다면
                    	if (dis[point[j][0]][point[j][1]] == 0) {
                    		impossible = true;
                    		break;
                    	}
                    }
            	}
                if (impossible) break;
                
                for (int j=i+1; j<=d; j++) {
                	// i번 점에서 j번 더러운칸 사이의 거리 저장
                	p_dis[i][j] = dis[point[j][0]][point[j][1]] - 1;
                	p_dis[j][i] = p_dis[i][j];
                }
                
                // 맵에서 i번 점 지우기
                map[point[i][0]][point[i][1]] = '.';
            }
            
            if (impossible) {
            	System.out.println(-1);
            }
            
            else {
	            // 더러운칸의 방문 순서를 저장할 배열
	            route = new int[d];
	            // 더러운칸의 방문 여부를 저장할 배열
	            vis = new boolean[d+1];
	
	            // 정답을 저장할 변수 ans를 Integer.MAX_VALUE로 초기화
	            ans = Integer.MAX_VALUE;
	            
	            func(0);
	            
	            System.out.println(ans);
            }
            
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
        }
    }
    
    // 백트래킹으로 시작점에서 시작해서 d개의 더러운 점을 모두 방문하는 경로 만들기
    private static void func(int k) {
    	// 더러운 칸을 모두 방문한 경우
    	if (k == d) {
    		// 경로 길이 계산
    		int temp = p_dis[0][route[0]];
    		for (int i=1; i<d; i++) {
    			temp += p_dis[route[i-1]][route[i]];
    		}
    		// ans 와 비교해서 더 작으면 대입
    		ans = ans < temp? ans : temp;
    	}
    	
    	// 더러운 칸 경로 덜 만든 경우
    	for (int i=1; i<=d; i++) {
    		// i번쨰 더러운칸 방문 안했다면
    		if (!vis[i]) {
    			// 방문 표시 남기고
    			route[k] = i;
    			vis[i] = true;
    			// 다음 재귀 호출
    			func(k+1);
    			// 방문 표시 제거
    			vis[i] = false;
    		}
    	}
    }
}