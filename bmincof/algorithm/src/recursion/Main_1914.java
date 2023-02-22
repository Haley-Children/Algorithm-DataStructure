package recursion;

import java.math.BigInteger;
import java.util.Scanner;

public class Main_1914 {

	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		BigInteger count = new BigInteger("1");
		for(int i = 0; i < n; i++) count = count.multiply(new BigInteger("2"));
		System.out.println(count.subtract(new BigInteger("1")));
		
		
		if(n <= 20) {
			move(n,1,3);
			System.out.println(sb);
		}
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
		return;
	}
	
}

