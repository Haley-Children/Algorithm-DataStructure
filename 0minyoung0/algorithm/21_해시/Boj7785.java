import java.io.*;
import java.util.*;

public class Boj7785 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 출입 기록의 수
		int n = Integer.parseInt(br.readLine());
		
		// 사원들을 다룰 해시셋
		HashSet<String> hs = new HashSet<>();
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			String s1 = st.nextToken();
			String s2 = st.nextToken();
			if (s2.equals("enter")) hs.add(s1);
			else hs.remove(s1);
		}
		
		// 해시셋을 리스트로 반환
		List<String> list = new ArrayList<>(hs);
		Collections.sort(list, Collections.reverseOrder());
		
		// 답 출력
		StringBuilder sb = new StringBuilder();
		for (String x : list) {
			sb.append(x).append("\n");
		}
		System.out.println(sb);
	}
}
