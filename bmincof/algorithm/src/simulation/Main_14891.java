package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 시계 방향이면 인덱스가 -1
 * 반시계 방향이면 인덱스가 +1
 *
 * 주변이 회전하는 경우
 * 오른쪽. 회전시킨 톱니의 north + 2번과 오른쪽 톱니의 north + 6번이 다르면
 * 왼쪽. 회전시킨 톱니의 north + 6번과 왼쪽의 north + 2번이 다르면
 *
 * 주변 톱니는 회전시킨 톱니랑 반대로 회전
 * 좌우 없거나 회전안할때까지 양 옆 확인
 * 
 * func
 * 1. rotate
 * 2. isSamePole
 * 3. hasNext
 */
public class Main_14891 {
	
	static char[][] gears = new char[5][8];
	static int[] north = new int[5];
	
	public static void main(String[] args) throws Exception {
		final int N = 0;
		final int S = 1;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 1; i <= 4; i++) {
			gears[i] = br.readLine().toCharArray();
		}
		
		int cmd = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < cmd; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int gear = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			rotateLefts(gear, dir);
			rotate(gear, -dir);
			rotateRights(gear, dir);
		}
		
		int point = 0;
		for(int i = 1; i <= 4; i++) {
			if(gears[i][north[i]] == S + '0') point += Math.pow(2, i - 1);
		}
		System.out.println(point);
	}
	
	static void rotateLefts(int gear, int dir) {
		if(!hasLeft(gear) || isSamePole(gear, gear - 1)) {
			rotate(gear, dir);
			return;
		}
		rotateLefts(gear - 1, -dir);
		rotate(gear, dir);
	}
	
	static void rotateRights(int gear, int dir) {
		if(!hasRight(gear) || isSamePole(gear, gear + 1)) {
			rotate(gear, dir);
			return;
		}
		rotateRights(gear + 1, -dir);
		rotate(gear, dir);
	}
	
	static void rotate(int gear, int dir) {
		// 시계방향 인풋 = 1, 반시계방향 인풋 = -1 이므로 인덱스 변화와 반대
		north[gear] = (north[gear] - dir + 8) % 8;
	}
	
	static boolean isSamePole(int g1, int g2) {
		// g1이 g2왼쪽에 있으면
		if(g1 < g2) {
			return gears[g1][(north[g1] + 2) % 8] == gears[g2][(north[g2] + 6) % 8];
		}
		// g1이 g2 오른쪽에 있으면
		else {
			return gears[g1][(north[g1] + 6) % 8] == gears[g2][(north[g2] + 2) % 8];
		}
	}
	
	static boolean hasLeft(int gear) {
		return gear > 1;
	}

	static boolean hasRight(int gear) {
		return gear < 4;
	}
	
}

