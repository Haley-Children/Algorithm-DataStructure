import java.io.*;
import java.util.*;

public class Boj21939 {
	static TreeSet<int[]> set = new TreeSet<>(new Comparator<int[]>() {
		public int compare(int[] o1, int[] o2) {
			if (o1[1] != o2[1]) return o1[1] - o2[1];
			return o1[0] - o2[0];
		}
	});
	static HashMap<Integer, Integer> map = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 추천 문제 리스트에 있는 문제의 개수
		int n = Integer.parseInt(br.readLine());
		
		// 초기 데이터 넣기
		while (n-- > 0) addData(new StringTokenizer(br.readLine()));
		
		// 명령문의 개수
		int m = Integer.parseInt(br.readLine());
		
		// 명령어 처리
		while (m-- > 0) operation(new StringTokenizer(br.readLine()));
		
	}
	
	private static void addData(StringTokenizer st) {
		int p = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		set.add(new int[] {p, l});
		map.put(p, l);
	}
	
	private static void operation(StringTokenizer st) {
		switch(st.nextToken()) {
		// 문제 번호 출력
		case "recommend":
			int x = Integer.parseInt(st.nextToken());
			if (x == 1) System.out.println(set.last()[0]);
			else System.out.println(set.first()[0]);
			break;
		// 문제 추가
		case "add":
			addData(st);
			break;
		// 문제 제거
		case "solved":
			int p = Integer.parseInt(st.nextToken());
			int l = map.get(p);
			set.remove(new int[] {p, l});
			map.remove(p);
			break;
		}
	}
}
