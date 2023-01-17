import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int r = sc.nextInt();
		int c = sc.nextInt();
		System.out.println(traverse((int)Math.pow(2, n), r, c));
		
	}
	
	static int traverse(int n, int row, int col) {
		
		if(n == 1) return 0;
		
		int half = n / 2;
		
		if(row < half && col < half) return traverse(half, row, col);
		else if(row < half && col >= half) return half * half + traverse(half, row, col - half);
		else if(row >= half && col < half) return 2 * half * half + traverse(half, row - half, col);
		else return 3 * half * half + traverse(half, row - half, col - half);
		
		
	}
	
}

