import java.util.Arrays;
import java.util.Scanner;

/*
 * 별 찍기 - 11
 * 삼각형의 가장 위 꼭짓점 좌표를 전달
 * 1. void func(int n, int r, int c)
 * 2. n == 3 , 삼각형 찍기
 * 3. func(n, r, c) -> func(n/2, r, c) + func(n/2, r+n/2, c-n/2) + func(n/2, r+n/2, c+n/2)
 */
public class BOJ_2448 {

	static char[][] stars;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		stars = new char[n][2*n];
		for(int i = 0; i < n; i++) {
			Arrays.fill(stars[i], ' ');
		}
		func(n, 0, n);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < 2*n; j++) {
				sb.append(stars[i][j]);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	static void func(int n, int r, int c) {
		if(n == 3) {
			int o = c-n-1;
			stars[r][o+3] = '*';
			stars[r+1][o+2] = '*'; stars[r+1][o+4] = '*';
			for(int i = 1; i <= 5; i++) {
				stars[r+2][o+i] = '*';
			}
			return;
		}
		
		func(n/2, r, c);
		func(n/2, r + n/2, c - n/2);
		func(n/2, r + n/2, c + n/2);
	}
}

