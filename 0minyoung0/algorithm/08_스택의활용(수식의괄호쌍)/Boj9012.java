import java.io.*;

public class Boj9012 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 테스트 데이터 개수
		int t = Integer.parseInt(br.readLine());
		
		// 괄호 종류가 1개이므로 남은 '('의 개수만 카운팅
		// 한번이라도 카운트가 음수가 되면 "NO"
		// 끝나고 카운트가 0이 아니면 "NO"
		// 그 외에는 "YES"
		test: for (int i=0; i<t; i++) {
			String s = br.readLine();
			// 남아있는 열린 괄호의 수를 셀 카운터 변수
			int cnt = 0;
			
			// 데이터를 순회하면서 처리
			for (int j=0; j<s.length(); j++) {
				// 열린 괄호 카운트 +1
				if (s.charAt(j) == '(') {
					cnt++;
				} else { // s.charAt(j) == '('
					// 남은 열린 괄호가 없는 경우
					if (cnt == 0) {
						// NO, continue test
						sb.append("NO\n");
						continue test;
					} else { // cnt > 0
						cnt--;
					}
				}
			}
			
			// 순회 종료 후 카운트가 0인지 확인
			// 0이 아니면 NO, 0이면 YES
			if (cnt != 0) {
				sb.append("NO\n");
			} else { // cnt == 0
				sb.append("YES\n");
			}
		}
		
		// 모든 테스트 케이스가 종료되면 sb 출력
		System.out.println(sb);
	}
}
