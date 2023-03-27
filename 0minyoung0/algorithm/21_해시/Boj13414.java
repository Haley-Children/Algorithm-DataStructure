import java.io.*;
import java.util.*;

public class Boj13414 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 과목의 수강 가능 인원
		int k = Integer.parseInt(st.nextToken());
		// 대기목록의 길이
		int l = Integer.parseInt(st.nextToken());
		
		// 대기 목록을 링크드 해시셋으로 저장
		LinkedHashSet<String> lhs = new LinkedHashSet<>();
		for (int i=0; i<l; i++) {
			String s = br.readLine();
			// 이미 대기 목록에 있으면 제거
			if (lhs.contains(s)) lhs.remove(s);
			// 대기 목록에 추가
			lhs.add(s);
		}
		
		// 답 출력
		StringBuilder sb = new StringBuilder();
		Iterator<String> iter = lhs.iterator();
		while (k-- != 0) {
			if (!iter.hasNext()) break;
			sb.append(iter.next()).append("\n");
		}
		System.out.println(sb);
	}
}
