import java.io.*;
import java.util.*;

public class Boj7662 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 데이터를 저장할 트리맵 선언
		TreeMap<Integer, Integer> tm = new TreeMap<>();
		
		// 테스트 데이터의 수
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			
			// 트리맵 초기화
			tm.clear();
			
			// 연산의 개수
			int k = Integer.parseInt(br.readLine());
			while (k-- > 0) {
				st = new StringTokenizer(br.readLine());
				
				// 연산을 나타내는 문자에 따라 연산하기
				// 삽입 연산
				if (st.nextToken().charAt(0) == 'I') {
					// 키를 삽입
					int key = Integer.parseInt(st.nextToken());
					// 키가 존재 하지 않았던 경우
					if (!tm.containsKey(key)) tm.put(key, 1);
					// 키가 존재하는 경우
					else tm.put(key, tm.get(key) + 1);
				}
				
				// 삭제 연산
				else {
					// 삭제 명령의 종류 (1: 최댓값 삭제, -1: 최솟값 삭제)
					int d = Integer.parseInt(st.nextToken());
					// 트리맵이 비어있으면 무시
					if (tm.isEmpty()) continue;
					// 삭제할 키
					int key = 0;
					if (d == 1) key = tm.lastKey();
					else key = tm.firstKey();
					// 키 삭제하기
					if (tm.get(key) == 1) tm.remove(key);
					else tm.put(key, tm.get(key) - 1);
				}
			}
			
			// 트리맵이 비어있으면 EMPTY 출력 후 continue
			if (tm.isEmpty()) {
				System.out.println("EMPTY");
				continue;
			}
			
			// 트리맵에 있는 최대값과 최솟값 출력
			System.out.println(tm.lastKey() + " " + tm.firstKey());
		}
	}
}
