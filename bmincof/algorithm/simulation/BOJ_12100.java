import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
2048(easy)

1. i번째 이동마다 상하좌우 모두 이동 시켜보기
2. 5번째 이동 후 보드에서 가장 큰 수 반환하기

구현해야 하는 메서드

1. move(상하좌우)
각 행 또는 열의 요소마다 이동하려는 방향으로 자신과 같은 값의 블록이 연속되게 존재하면 더해서 하나의 블록으로 변경
모두 변경시킨 후 이동하려는 방향의 끝부터 채우기
모든 행 또는 열에 대해서 진행

*/
public class BOJ_12100 {

	static int n;
	static int[] movement;
	
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	
	static int[][] board;
	static int[][] moved;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		
		int maxBlock = Integer.MIN_VALUE;
		
		board = new int[n][n];
		moved = new int[n][];
		
		// 게임 초기상태 만들기
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//총 경우의 수
		int totalCase = (int) Math.pow(4, 5);
		
		// 각 경우의 수마다 5번 이동시킨 결과를 최대와 비교해서 저장
		for(int c = 0; c < totalCase; c++) {
			int tmp = c;
			
			// 각 이동 시에 어느 방향으로 이동시킬지 저장하는 변수
			movement = new int[5];
			for(int i = 0; i < n; i++) {
				moved[i] = Arrays.copyOf(board[i], n);
			}
			
			// 각 이동 시 어느 방향으로 이동시킬지 저장
			int idx = 0;
			while(tmp > 0) {
				movement[idx++] = tmp % 4;
				tmp /= 4;
			}
			
			// 정해진 순서대로 이동시키기
			for(int i = 0; i < 5; i++) move(movement[i]);
			
			// 모두 이동시킨 결과를 최댓값과 비교
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					maxBlock = Math.max(maxBlock, moved[i][j]);
				}
			}
			
		}
		
		System.out.println(maxBlock);
		
	}
	
	/*
	 * 각 행 또는 열의 요소마다 이동하려는 방향으로 자신과 같은 값의 블록이 연속되게 존재하면 더해서 하나의 블록으로 변경
	 * 모두 변경시킨 후 이동하려는 방향의 끝부터 채우기
	 * 모든 행 또는 열에 대해서 진행
	 */
	static void move(int dir) {
		
		// 블록 합치기
		merge(dir);
		
		// 블록 끝까지 밀기
		push(dir);
		
	}
	
	// i, j번째 블럭이 dir 방향으로 합칠 수 있는 블럭이 있는지 확인
	static int[] hasSameBlock(int i, int j, int dir) {
		int curBlock = moved[i][j];
		int x = i + dx[dir];
		int y = j + dy[dir];
		
		while(x >= 0 && x < n && y >= 0 && y < n) {
			if(moved[x][y] == 0) {
				x += dx[dir];
				y += dy[dir];
				continue;
			}
			else if(moved[x][y] == curBlock) {
				return new int[] {x,y};
			} else {
				return null;
			}
		}
		return null;
	}
	
	
	// 이동시키려는 방향부터 블록을 합치는 메서드
	static void merge(int dir) {
		// 아래로 합치기
		if(dir == 0) {
			for(int j = 0; j < n; j++) {
				for(int i = n-1; i >= 0; i--) {
					if(moved[i][j] == 0) continue;
					
					int[] sameBlock = hasSameBlock(i, j, 1);			// 이동시키려는 방향부터 합쳐야함
					if(sameBlock != null) {
						int nx = sameBlock[0];
						int ny = sameBlock[1];
						
						moved[nx][ny] *= 2;
						moved[i][j] = 0;
						i = nx;
						j = ny;
					}
				}
			}
		}
		
		// 위로 합치기
		else if(dir == 1) {
			for(int j = 0; j < n; j++) {
				for(int i = 0; i < n; i++) {
					if(moved[i][j] == 0) continue;
					
					int[] sameBlock = hasSameBlock(i, j, 0);			// 이동시키려는 방향부터 합쳐야함
					if(sameBlock != null) {
						int nx = sameBlock[0];
						int ny = sameBlock[1];
						
						moved[nx][ny] *= 2;
						moved[i][j] = 0;
						i = nx;
						j = ny;
					}
				}
			}
		}
		
		// 오른쪽으로 합치기
		else if(dir == 2) {
			for(int i = 0; i < n; i++) {
				for(int j = n - 1; j >= 0; j--) {
					if(moved[i][j] == 0) continue;
					
					int[] sameBlock = hasSameBlock(i, j, 3);			// 이동시키려는 방향부터 합쳐야함
					if(sameBlock != null) {
						int nx = sameBlock[0];
						int ny = sameBlock[1];
						
						moved[nx][ny] *= 2;
						moved[i][j] = 0;
						i = nx;
						j = ny;
					}
				}
			}
		}
		
		// 왼쪽으로 합치기
		else {
			for(int i = 0; i < n; i++) {
				int count = 0;
				for(int j = 0; j < n; j++) {
					if(moved[i][j] == 0) continue;
					
					int[] sameBlock = hasSameBlock(i, j, 2);			// 이동시키려는 방향부터 합쳐야함
					if(sameBlock != null) {
						int nx = sameBlock[0];
						int ny = sameBlock[1];
						
						moved[nx][ny] *= 2;
						moved[i][j] = 0;
						i = nx;
						j = ny;
					}
				}
			}
		}
		
	}
	
	// 블럭을 이동시키려는 방향으로 미는 메서드
	static void push(int dir) {
		// 아래로 밀기
		if(dir == 0) {
			for(int j = 0; j < n; j++) {
				int count = n-1;
				for(int i = n-1; i >= 0; i--) {
					if(moved[i][j] != 0) {
						moved[count--][j] = moved[i][j];
					}
				}
				
				for(int i = count; i >=0; i--) {
					moved[i][j] = 0;
				}
			}
			
			
		}
		
		// 위로 밀기
		else if(dir == 1) {
			for(int j = 0; j < n; j++) {
				int count = 0;
				for(int i = 0; i < n; i++) {
					if(moved[i][j] != 0) {
						moved[count++][j] = moved[i][j];
					}
				}
				
				for(int i = count; i < n; i++) {
					moved[i][j] = 0;
				}
			}
		}
		
		// 오른쪽으로 밀기
		else if(dir == 2) {
			for(int i = 0; i < n; i++) {
				int count = n - 1;
				for(int j = n - 1; j >= 0; j--) {
					if(moved[i][j] != 0) {
						moved[i][count--] = moved[i][j];
					}
				}
				
				for(int j = count; j >=0; j--) {
					moved[i][j] = 0;
				}
			}
		}
		
		// 왼쪽으로 밀기
		else {
			for(int i = 0; i < n; i++) {
				int count = 0;
				for(int j = 0; j < n; j++) {
					if(moved[i][j] != 0) {
						moved[i][count++] = moved[i][j];
					}
				}
				
				for(int j = count; j < n; j++) {
					moved[i][j] = 0;
				}
			}
		}
		
	}
	
}

