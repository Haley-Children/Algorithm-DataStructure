import java.io.*;
import java.util.*;

public class Boj1759 {
	static int l;
	static int c;
	static char[] arr;
	static char[] ch;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 암호 길이
		l = Integer.parseInt(st.nextToken());
		// 암호 후보 문자 개수
		c = Integer.parseInt(st.nextToken());
		
		// 암호 배열
		arr = new char[l];
		// 암호 후보 문자 배열
		ch = new char[c];
		
		// 암호 후보 문자 입력 받기
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<c; i++) {
			ch[i] = st.nextToken().charAt(0);
		}
		
		// 암호 후보 문자 정렬
		Arrays.sort(ch);
		
		func(0, -1);
	}
	// 백트래킹 함수
	private static void func(int k, int pre) {
		// l개의 암호를 모두 골랐다면
		if (k == l) {
			int cntV = countVowel(arr);
			// 모음이 1개 이상이고 자음이 2개 이상이면 암호 출력
			if (1 <= cntV && cntV <= l - 2) {
				for (int i=0; i<arr.length; i++) {
					System.out.print(arr[i]);
				}
				System.out.println();
			}
			return;
		}
		
		// 아직 덜 골랐으면 하나 더 고르고 다음 재귀 호출
		for (int i=pre+1; i<=c-l+k; i++) {
			arr[k] = ch[i];
			func(k+1, i);
		}
	}
	
	// 모음의 개수를 세는 메소드
	private static int countVowel(char[] a) {
		int ans = 0;
		for (int i=0; i<a.length; i++) {
			if (a[i] == 'a' || a[i] == 'e' || a[i] == 'i' || a[i] == 'o' || a[i] == 'u') {
				ans++;
			}
		}
		return ans;
	}
}
