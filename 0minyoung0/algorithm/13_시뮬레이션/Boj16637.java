import java.io.*;
import java.util.*;

public class Boj16637 {
	static int n;
	static long ans = Long.MIN_VALUE;
	static long[] num;
	static char[] oper;
	static boolean[] select;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 수식의 길이
		n = Integer.parseInt(br.readLine());
		// 문자열
		String s = br.readLine();
		
		// 숫자
		num = new long[n/2+1];
		for (int i=0; i<n/2+1; i++) {
			num[i] = s.charAt(2*i) - '0';
		}
		// 연산자
		oper = new char[n/2];
		for (int i=0; i<n/2; i++) {
			oper[i] = s.charAt(2*i+1);
		}
		// 괄호 칠 위치
		select = new boolean[n/2];
		
		// 백트래킹으로 모든 경우 탐색하여 최대값 찾기
		func(0);
		
		// 답 출력
		System.out.println(ans);
	}
	
	// 계산해주는 메소드
	private static long cal (long n1, long n2, char oper) {
		switch(oper) {
			case '+':
				return n1 + n2;
			case '-':
				return n1 - n2;
			case '*':
				return n1 * n2;
			default:
				return 0;
		}
	}
	
	// 백트래킹으로 모든 경우 탐색해서 최대값 찾아주는 메소드
	private static void func (int k) {
		// 괄호를 다 추가한 경우
		if (k == n/2) {
			// n/2번째까지 순회하면서 리스트에 계산할 숫자와 연산자 넣기
			List<Long> numList = new ArrayList<>();
			List<Character> operList = new ArrayList<>();
			for (int i=0; i<n/2; i++) {
				// i번째에 괄호가 있으면
				if (select[i]) {
					// 계산된 값을 numList에 넣고 i++
					numList.add(cal(num[i], num[i+1], oper[i]));
					i++;
					// 마지막 숫자면 break
					if (i == n/2) break;
				}
				else {
					// numList에 넣기
					numList.add(num[i]);
				}
				
				// operList에 넣기
				operList.add(oper[i]);
			}
			// 마지막 숫자 안들어갔으면 넣어주기
			if (numList.size() == operList.size()) {
				numList.add(num[n/2]);
			}
			
			// numList와 operList로 계산하기
			long temp = numList.get(0);
			for (int i=0; i<operList.size(); i++) {
				temp = cal(temp, numList.get(i+1), operList.get(i));
			}
			
			// 최대값인지 확인
			ans = temp > ans? temp : ans;
			
			return;
		}
		
		// k-1번째 위치에 괄호가 없다면 k번째 위치에 괄호 치기
		if (k == 0 || !select[k-1]) {
			select[k] = true;
			// 다음 재귀 호출
			func(k+1);
		}
		// k번째 위치에 괄호를 안친 경우도 재귀 호출
		select[k] = false;
		func(k+1);
	}
}
