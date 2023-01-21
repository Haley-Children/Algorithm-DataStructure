// boj 2447
import java.util.Scanner;

public class Boj2447 {
	static char[][] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int n = sc.nextInt();
		arr = new char[n][n];
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				arr[i][j] = '*';
			}
		}
		makeBlank(n, 0, 0);
		for (int i=0; i<n; i++) {
			sb.append(arr[i]).append("\n");
		}
		System.out.println(sb);
	}
	private static void makeBlank(int n, int x, int y) {
		if (n == 3) {
			arr[x+1][y+1] = ' ';
			return;
		}
		for (int i=x+n/3; i<x+2*n/3; i++) {
			for (int j=y+n/3; j<y+2*n/3; j++) {
				arr[i][j] = ' ';
			}
		}
		int[] dx = {0,0,0,n/3,n/3,2*n/3,2*n/3,2*n/3};
		int[] dy = {0,n/3,2*n/3,0,2*n/3,0,n/3,2*n/3};
		for (int dir=0; dir<8; dir++) {
			makeBlank(n/3, x+dx[dir], y+dy[dir]);
		}
	}
}