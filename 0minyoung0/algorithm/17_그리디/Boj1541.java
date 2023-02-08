// 그리디.boj1541;

import java.io.*;
import java.util.*;

public class Boj1541 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int temp = 0;
		int ans = 0;
		// 마이너스가 한번이라도 나오면 그 이후로는 계속 숫자를 빼면 됨
		boolean minusMode = false;
		for (int i=0; i<s.length(); i++) {
			if (s.charAt(i) == '-') {
				ans += temp;
				temp = 0;
				minusMode = true;
			}
			else if (s.charAt(i) == '+') {
				ans += temp;
				temp = 0;
			}
			// 마이너스 모드일때는 숫자를 계속 마이너스로 바꿔서 합산
			else {
				int cur = s.charAt(i) - '0';
				temp *= 10;
				if (minusMode) {
					temp -= cur;
				}
				else {
					temp += cur;
				}
			}
			
		}
		System.out.println(ans + temp);
	}
}
