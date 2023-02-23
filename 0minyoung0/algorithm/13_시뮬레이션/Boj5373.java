import java.io.*;
import java.util.*;

public class Boj5373 {
	static char[][][] cube = new char[5][5][5];;
	static char[][] newTop = new char[5][5];
	static char[][] newSecond = new char[5][5];
	static char[][][] newCube = new char[5][5][5];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스 개수
		int t = Integer.parseInt(br.readLine());
		
		// 각 테스트 케이스 별 실행
		for (int i=0; i<t; i++) {
			// cube 생성
			for (int j=1; j<4; j++) {
				for (int k=1; k<4; k++) {
					// 윗면 아랫면 채우기
					cube[0][j][k] = 'w';
					cube[4][j][k] = 'y';
					// 앞면 뒷면 채우기
					cube[j][4][k] = 'r';
					cube[j][0][k] = 'o';
					// 왼쪽면 오른쪽면 채우기
					cube[j][k][0] = 'g';
					cube[j][k][4] = 'b';
				}
			}
			
			// 큐브를 돌린 횟수
			int n = Integer.parseInt(br.readLine());
			// 큐브 조작
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				// 큐브를 돌린 방법
				String oper = st.nextToken();
				
				// turnCube에 앞서서 rollCube
				switch(oper.charAt(0)) {
					case 'D':
						rollCube(0);
					case 'F':
						rollCube(0);
						break;
					case 'B':
						rollCube(1);
						break;
					case 'L':
						rollCube(2);
						break;
					case 'R':
						rollCube(3);
						break;
					default:
						break;
				}
				
				// turnCube
				turnCube(oper.charAt(1));
				
				// 원래 상태로 돌리기 위해서 rollCube
				switch(oper.charAt(0)) {
					case 'D':
						rollCube(1);
					case 'F':
						rollCube(1);
						break;
					case 'B':
						rollCube(0);
						break;
					case 'L':
						rollCube(3);
						break;
					case 'R':
						rollCube(2);
						break;
					default:
						break;
				}
			}
			
			// 스트링 빌더에 큐브의 윗면을 append
			for (int j=1; j<4; j++) {
				for (int k=1; k<4; k++) {
					sb.append(cube[0][j][k]);
				}
				sb.append("\n");
			}
		}
		
		// StringBuilder에 저장된 답 출력
		System.out.println(sb);
	}
	
	// 큐브의 회전시킬 2*5*5짜리 배열을 주면 회전시켜서 리턴해주는 메소드
	// oper = '+'이면 시계방향, oper = '-'이면 반시계방향
	private static void turnCube(char oper){
		// 시계방향 회전
		if (oper == '+') {
			// 윗면 회전
			for (int i=1; i<4; i++) {
				for (int j=1; j<4; j++) {
					newTop[j][4-i] = cube[0][i][j];
				}
			}
			for (int i=1; i<4; i++) {
				for (int j=1; j<4; j++) {
					cube[0][i][j] = newTop[i][j];
				}
			}
			
			// 인접면의 인접한 줄이 이웃면으로 이동
			for (int i=0; i<5; i++) {
				for (int j=0; j<5; j++) {
					newSecond[j][4-i] = cube[1][i][j];
				}
			}
			for (int i=0; i<5; i++) {
				for (int j=0; j<5; j++) {
					cube[1][i][j] = newSecond[i][j];
				}
			}
		}
		
		// 반시계방향 회전
		else { // oper == '-'
			turnCube('+');
			turnCube('+');
			turnCube('+');
		}

	}
	
	// 큐브를 다른 면이 위로 오도록 돌리는 메소드
	// oper = 0,1,2,3일때 각각 front, back, left, right가 위로 오도록 함
	private static void rollCube(int oper){
		
		if (oper == 0) {
			for (int i=0; i<5; i++) {
				for (int j=0; j<5; j++) {
					for (int k=0; k<5; k++) {
						newCube[4-j][i][k] = cube[i][j][k];
					}
				}
			}
			for (int i=0; i<5; i++) {
				for (int j=0; j<5; j++) {
					for (int k=0; k<5; k++) {
						cube[i][j][k] = newCube[i][j][k];
					}
				}
			}
		}
		
		else if (oper == 1) {
			rollCube(0);
			rollCube(0);
			rollCube(0);
		}
		
		else if (oper == 2) {
			for (int i=0; i<5; i++) {
				for (int j=0; j<5; j++) {
					for (int k=0; k<5; k++) {
						newCube[k][j][4-i] = cube[i][j][k];
					}
				}
			}
			for (int i=0; i<5; i++) {
				for (int j=0; j<5; j++) {
					for (int k=0; k<5; k++) {
						cube[i][j][k] = newCube[i][j][k];
					}
				}
			}
		}
		
		else { // oper == 3
			rollCube(2);
			rollCube(2);
			rollCube(2);
		}
	}
}
