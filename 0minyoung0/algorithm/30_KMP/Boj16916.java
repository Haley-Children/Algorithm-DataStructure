import java.io.*;

public class Boj16916 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// P가 S의 부분 문자열인지 확인하기
		char[] S = br.readLine().toCharArray();
		char[] P = br.readLine().toCharArray();
		
		// P의 실패함수
		int[] f = faliure(P);
		
		// KMP 알고리즘
		int j = 0;
		for (int i=0; i<S.length; i++) {
			while (j > 0 && S[i] != P[j]) j = f[j-1];
			if (S[i] == P[j]) j++;
			if (j == P.length) {
				System.out.print(1);
				return;
			}
		}
		System.out.print(0);
	}
	
	// 실패함수 만드는 메서드
	private static int[] faliure(char[] s) {
		int[] f = new int[s.length];
		int j = 0;
		for (int i=1; i<s.length; i++) {
			while (j > 0 && s[i] != s[j]) j = f[j-1];
			if (s[i] == s[j]) f[i] = ++j;
		}
		return f;
	}
}
