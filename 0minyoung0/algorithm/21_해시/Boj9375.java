import java.io.*;
import java.util.*;

public class Boj9375 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 테스트 케이스 개수
		int T = Integer.parseInt(br.readLine());
		
		// 테스트 케이스 별로 실행
		for (int i=0; i<T; i++) {
			// 혜빈이가 가진 의상의 수
			int n = Integer.parseInt(br.readLine());
			
			// 의상이 없는 경우 0 출력
			if (n == 0) {
				System.out.println(0);
				continue;
			}
			
			// 해시맵에 데이터를 종류별로 몇개인지 저장
			Map<String, Integer> map = new HashMap<>();
			for (int j=0; j<n; j++) {
				st = new StringTokenizer(br.readLine());
				st.nextToken();
				String kind = st.nextToken();
				if (!map.containsKey(kind)) {
					map.put(kind, 1);
				}else {
					map.put(kind, map.get(kind) + 1);
				}
			}
			
			// 해시맵을 순회하면서 경우의 수 구하기
			int ans = 1;
			for (String key : map.keySet()) {
				ans *= map.get(key) + 1;
			}
			System.out.println(ans-1);

		}
	}
}
