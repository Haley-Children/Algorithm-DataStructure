import java.io.*;
import java.util.*;

public class Boj1202 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 보석의 개수
		int n = Integer.parseInt(st.nextToken());
		// 가방의 개수
		int k = Integer.parseInt(st.nextToken());
		
		// 보석 정보 (무게, 가격)
		List<int[]> jewels = new ArrayList<>();
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			jewels.add(new int[] {m, v});
		}
		// 가격이 높은 보석부터 내림차순으로 정렬
		Collections.sort(jewels, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				if (o1[1] != o2[1]) return o2[1] - o1[1];
				return o2[0] - o1[0];
			}
		});
		
		// 가방 정보를 트리맵에 저장 (담을 수 있는 최대 무게)
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for (int i=0; i<k; i++) {
			int c = Integer.parseInt(br.readLine());
			if (!map.containsKey(c)) map.put(c, 1);
			else map.put(c, map.get(c) + 1);
		}
		
		// 훔칠 수 있는 보석 가격의 합의 최대값
		long ans = 0;
		
		for (int[] jewel : jewels) {
			int mass = jewel[0];
			int value = jewel[1];

			// 해당 보석을 담을 수 있는 가방이 없으면 continue
			if (map.ceilingKey(mass) == null) continue;
			
			// 해당 보석을 담을 수 있는 가장 작은 가방 찾아서 제거
			int key = map.ceilingKey(mass);
			if (map.get(key) == 1) map.remove(key);
			else map.put(key, map.get(key) - 1);
			
			// 가방에 담은 보석의 가격을 ans에 더하기
			ans += value;
		}
		
		// 답 출력
		System.out.println(ans);
	}
}
