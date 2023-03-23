import java.io.*;
import java.util.*;

public class Boj21944 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 문제의 개수
		int n = Integer.parseInt(br.readLine());
		
		// set 배열 (0번에는 모든 데이터, 1~100번에는 알고리즘 종류(g)에 따라 저장)
		TreeSet<int[]>[] set = new TreeSet[101];
		for (int i=0; i<=100; i++) {
			set[i] = new TreeSet<>(new Comparator<int[]>() {
				public int compare(int[] o1, int[] o2) {
					if (o1[1] != o2[1]) return o1[1] - o2[1];
					return o1[0] - o2[0];
				}
			});
		}
		
		// p로 l과 g를 알아내기 위한 맵
		HashMap<Integer, int[]> map = new HashMap<>();
		
		// 추천 문제 리스트 저장
		while (n-- > 0) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			set[0].add(new int[] {p, l, g});
			set[g].add(new int[] {p, l});
			map.put(p, new int[] {l, g});
		}
		
		// 명령문의 개수
		int m = Integer.parseInt(br.readLine());
		
		// 명령문 실행
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = 0;
			int p = 0;
			int l = 0;
			int g = 0;
			switch(st.nextToken()) {
			case "recommend":
				g = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());
				if (x == 1) System.out.println(set[g].last()[0]);
				else System.out.println(set[g].first()[0]);
				break;
			case "recommend2":
				x = Integer.parseInt(st.nextToken());
				if (x == 1) System.out.println(set[0].last()[0]);
				else System.out.println(set[0].first()[0]);
				break;
			case "recommend3":
				x = Integer.parseInt(st.nextToken());
				l = Integer.parseInt(st.nextToken());
				if (x == 1) {
					if (set[0].ceiling(new int[] {0, l, 0}) == null) {
						System.out.println(-1);
					}else {
						System.out.println(set[0].ceiling(new int[] {0, l, 0})[0]);
					}
				}else {
					if (set[0].lower(new int[] {0, l, 0}) == null) {
						System.out.println(-1);
					}else {
						System.out.println(set[0].lower(new int[] {0, l, 0})[0]);
					}
				}
				break;
			case "add":
				p = Integer.parseInt(st.nextToken());
				l = Integer.parseInt(st.nextToken());
				g = Integer.parseInt(st.nextToken());
				set[0].add(new int[] {p, l, g});
				set[g].add(new int[] {p, l});
				map.put(p, new int[] {l, g});
				break;
			case "solved":
				p = Integer.parseInt(st.nextToken());
				int[] temp = map.get(p);
				l = temp[0];
				g = temp[1];
				set[0].remove(new int[] {p, l, g});
				set[g].remove(new int[] {p, l});
				map.remove(p);
				break;
			}
		}
		
	}
}
