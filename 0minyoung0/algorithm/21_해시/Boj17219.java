import java.io.*;
import java.util.*;

public class Boj17219 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 저장된 사이트 주소의 수
		int n = Integer.parseInt(st.nextToken());
		// 비밀번호를 찾으려는 사이트 주소의 수
		int m = Integer.parseInt(st.nextToken());
		
		// 사이트 주소와 비밀번호를 해시맵에 저장
		HashMap<String, String> hm = new HashMap<>();
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			hm.put(st.nextToken(), st.nextToken());
		}
		
		// 답 출력
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<m; i++) {
			String query = br.readLine();
			sb.append(hm.get(query)).append('\n');
		}
		System.out.println(sb);
	}
}
