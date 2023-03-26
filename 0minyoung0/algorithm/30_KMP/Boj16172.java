import java.io.*;

public class Boj16172 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// P가 S의 부분 문자열인지 확인하기
		char[] S = br.readLine().toCharArray();
		char[] K = br.readLine().toCharArray();
		
		// K의 실패함수
		int[] f = failure(K);
		
		// KMP 알고리즘
		int j = 0;
		for (int i=0; i<S.length; i++) {
			if ('0' <= S[i] && S[i] <= '9') continue;
			while (j > 0 && S[i] != K[j]) j = f[j-1];
			if (S[i] == K[j]) {
				if (++j == K.length) {
					System.out.println(1);
					return;
				}
			}
		}
		System.out.println(0);
	}
	
	// 실패함수 만드는 메서드
	private static int[] failure(char[] s) {
		int[] f = new int[s.length];
		int j = 0;
		for (int i=1; i<s.length; i++) {
			while (j > 0 && s[i] != s[j]) j = f[j-1];
			if (s[i] == s[j]) f[i] = ++j;
		}
		return f;
	}
}
