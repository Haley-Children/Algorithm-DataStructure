// 백트래킹.boj9663;

import java.util.Scanner;

public class Boj9663 {
	static int n;
	static int cnt = 0;
	static int[] position;
	static boolean[] colUsed;
	static boolean[] diaUsed1; // index x+y		<- looks like /
	static boolean[] diaUsed2; // index x-y+n-1	<- looks like \
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		colUsed = new boolean[n];
		diaUsed1 = new boolean[2*n-1];
		diaUsed2 = new boolean[2*n-1];		
		putQueen(0);
		System.out.println(cnt);
	}
	private static void putQueen(int k) {
		if (k==n) {
			cnt++;
			return;
		}
		for (int i=0; i<n; i++) {
			if (!colUsed[i] && !diaUsed1[k+i] && !diaUsed2[k-i+n-1]) {
				colUsed[i] = true;
				diaUsed1[k+i] = true;
				diaUsed2[k-i+n-1] = true;
				putQueen(k+1);
				colUsed[i] = false;
				diaUsed1[k+i] = false;
				diaUsed2[k-i+n-1] = false;
			}
		}
	}
}
