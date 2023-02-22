package recursion;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * 1. void func(int r, int c, int size)
 * 2. base condition : 모든 칸이 같은 값일 때
 * 3. newSize = size/2
 * func(r, c, size) = func(r, c, newSize) + func(r + newSize, c, newSize)
 * 						+ func(r, c + newSize) + func(r + newSize, c + newSize, newSize)
 */
public class Main_1992 {
	
	static char[][] img;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		img = new char[n][];
		for(int i = 0; i < n; i++) {
			img[i] = br.readLine().toCharArray();
		}
		
		comp(0, 0, n);
		System.out.println(sb);
	}
	
	static void comp(int r, int c, int size) {
		boolean hasOnly0 = true;
		boolean hasOnly1 = true;
		
		for(int i = r; i < r + size; i++) {
			for(int j = c; j < c + size; j++) {
				if(!hasOnly0 && !hasOnly1) break;
				
				if(hasOnly0 && img[i][j] == '1') {
					hasOnly0 = false;
				}
				if(hasOnly1 && img[i][j] == '0') {
					hasOnly1 = false;
				}
			}
		}
		
		// 둘 중 하나의 값만 있으면
		if(hasOnly0 || hasOnly1) {
			sb.append(hasOnly0 ? '0' : '1');
		}
		// 값이 섞여있으면
		else {
			int newSize = size / 2;
			sb.append('(');
			comp(r, c, newSize);
			comp(r, c + newSize, newSize);
			comp(r + newSize, c, newSize);
			comp(r + newSize, c + newSize, newSize);
			sb.append(')');
		}
		
	}
	
}

