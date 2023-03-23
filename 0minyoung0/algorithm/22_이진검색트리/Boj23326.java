import java.io.*;
import java.util.*;

public class Boj23326 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 구역의 개수
		int n = Integer.parseInt(st.nextToken());
		// 쿼리의 개수
		int q = Integer.parseInt(st.nextToken());
		
		// 명소를 저장할 트리셋
		TreeSet<Integer> set = new TreeSet<>();
		
		// 명소 저장
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=n; i++) {
			if (st.nextToken().equals("1")) set.add(i);
		}
		
		// 도현이의 위치
		int dh = 1;
		
		// 쿼리 처리하기
		StringBuilder sb = new StringBuilder();
		while (q-- > 0) {
			st = new StringTokenizer(br.readLine());
			switch(st.nextToken()) {
			case "1":
				int i = Integer.parseInt(st.nextToken());
				if (set.contains(i)) set.remove(i);
				else set.add(i);
				break;
			case "2":
				int x = Integer.parseInt(st.nextToken());
				dh = (dh + x - 1) % n + 1;
				break;
			case "3":
				if (set.isEmpty()) sb.append("-1\n");
				else if (set.ceiling(dh) != null) sb.append(set.ceiling(dh) - dh).append("\n");
				else sb.append(set.first() - dh + n).append("\n");
				break;
			}
		}
		System.out.println(sb);
	}
}
