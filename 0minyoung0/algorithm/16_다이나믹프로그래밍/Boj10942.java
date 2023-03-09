import java.io.*;
import java.util.*;

public class Boj10942 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 자연수의 개수
		int n = Integer.parseInt(br.readLine());
		
		// 칠판에 적힌 자연수를 int[]에 저장 (1-indexed)
		int[] arr = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 팰린드롬 판단할 boolean[][] 선언
		boolean[][] isPalindrome = new boolean[n+1][n+1];
		// 팰린드롬 배열 초기화 (차이 0일때, 1일때)
		for (int i=1; i<=n; i++) {
			isPalindrome[i][i] = true;
		}
		for (int i=1; i<n; i++) {
			if (arr[i] == arr[i+1]) {
				isPalindrome[i][i+1] = true;
			}
		}
		
		// 팰린드롬 배열 차이 2일때부터 n-1일때까지 순차적으로 계산
		for (int length=2; length<=n-1; length++) {
			for (int i=1; i+length<=n; i++) {
				// i번째와 i+length번째 숫자가 같으면 그 사이에 있는게 팰린드롬이면 팰린드롬
				if (arr[i] == arr[i+length]) {
					isPalindrome[i][i+length] = isPalindrome[i+1][i+length-1];
				}
			}
		}
		
		// 출력을 위한 스트링 빌더
		StringBuilder sb = new StringBuilder();
		
		// 질문의 개수
		int m = Integer.parseInt(br.readLine());
		
		// 팰린드롬 질문에 따라 스트링빌더에 저장
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			if (isPalindrome[s][e]) {
				sb.append("1\n");
			}else {
				sb.append("0\n");
			}
		}
		System.out.println(sb);
	}
}