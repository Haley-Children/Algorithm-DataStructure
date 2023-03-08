import java.io.*;
import java.util.*;

public class Boj11051 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[][] arr = new int[1001][1001];
		for (int i=1; i<=1000; i++) {
			arr[i][0] = 1;
			for (int j=1; j<i; j++) {
				arr[i][j] = (arr[i-1][j-1] + arr[i-1][j]) % 10007;
			}
			arr[i][i] = 1;
		}
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		System.out.println(arr[n][k]);
	}
}