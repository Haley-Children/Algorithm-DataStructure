package recursion;

import java.util.Scanner;

public class Main_11729 {

	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
        System.out.println((int)Math.pow(2, n) - 1);
		
		
		move(n,1,3);
		System.out.println(sb);

	}
	
	static void move(int disk, int from, int to) {
		if(disk == 1) {
			sb.append(from).append(" ").append(to).append("\n");
			return;
		}
		
		// 옮기려는 원판 위의 모든 원판들을  옮기려는 원판의 출발지도 도착지도 아닌 곳으로 옮겨놓는다.
		move(disk-1, from, 6-from-to);
		sb.append(from).append(" ").append(to).append("\n");
		move(disk-1, 6-from-to, to);
	}
	
}

