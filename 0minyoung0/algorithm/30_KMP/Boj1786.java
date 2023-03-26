import java.io.*;
import java.util.*;

public class Boj1786 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 문자열 T
		char[] T = br.readLine().toCharArray();
		// 문자열 P
		char[] P = br.readLine().toCharArray();
		
		// 문자열 P의 실패함수
		int[] f = failure(P);
		
		// 찾은 위치를 저장할 큐
		Queue<Integer> q = new ArrayDeque<>();
		
		// KMP 알고리즘
		int j = 0;
		for (int i=0; i<T.length; i++) {
			while (j > 0 && T[i] != P[j]) j = f[j-1];
			if (T[i] == P[j]) j++;
			if (j == P.length) {
				q.add(i - P.length + 2);
				j = f[j-1];
			}
		}
		
		// 답 출력
		StringBuilder sb = new StringBuilder();
		sb.append(q.size()).append("\n");
		while (!q.isEmpty()) {
			sb.append(q.poll()).append(" ");
		}
		System.out.println(sb);
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
