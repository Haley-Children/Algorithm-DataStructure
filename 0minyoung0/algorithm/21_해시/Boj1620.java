import java.io.*;
import java.util.*;

public class Boj1620 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 도감에 수록되어 있는 포켓몬의 개수
		int n = Integer.parseInt(st.nextToken());
		// 맞춰야 하는 문제의 개수
		int m = Integer.parseInt(st.nextToken());
		
		// 포켓몬 도감을 두개의 해시맵으로 저장
		HashMap<String, Integer> hm1 = new HashMap<>();
		HashMap<Integer, String> hm2 = new HashMap<>();
		for (int i=1; i<=n; i++) {
			String s = br.readLine();
			hm1.put(s, i);
			hm2.put(i, s);
		}
		
		// 질문에 대한 답 저장해서 출력
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<m; i++) {
			String s = br.readLine();
			if ('A' <= s.charAt(0) && s.charAt(0) <= 'Z') {
				sb.append(hm1.get(s)).append("\n");
			}
			else {
				sb.append(hm2.get(Integer.parseInt(s))).append("\n");
			}
		}
		System.out.println(sb);
	}
}
