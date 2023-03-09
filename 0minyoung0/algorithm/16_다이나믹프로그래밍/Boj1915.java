import java.io.*;
import java.util.*;

public class Boj1915 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		char[][] arr = new char[n][m];
		for (int i=0; i<n; i++) {
			String s = br.readLine();
			for (int j=0; j<m; j++) {
				arr[i][j] = s.charAt(j);
			}
		}
		
		// 해당 좌표가 가장 오른쪽 아래인 최대 정사각형의 한변의 길이를 d에 저장
		int[][] d = new int[n][m];
		for (int i=0; i<n; i++) {
			if (arr[i][0] == '1') {
				d[i][0] = 1;
			}
		}
		for (int j=1; j<m; j++) {
			if (arr[0][j] == '1') {
				d[0][j] = 1;
			}
		}
		
		for (int i=1; i<n; i++) {
			for (int j=1; j<m; j++) {
				if (arr[i][j] == '1') {
					d[i][j] = Math.min(d[i-1][j-1], Math.min(d[i-1][j], d[i][j-1])) + 1;
				}
			}
		}
		
		// 답 출력
		int ans = 0;
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				ans = d[i][j] > ans? d[i][j]: ans;
			}
		}
		System.out.println(ans*ans);
	}
}