import java.io.*;
import java.util.*;

public class Boj1822 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 집합 A의 원소의 개수
		int nA = Integer.parseInt(st.nextToken());
		// 집합 B의 원소의 개수
		int nB = Integer.parseInt(st.nextToken());
		
		// 집합 A를 나타낼 리스트
		List<Integer> listA = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<nA; i++) {
			listA.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(listA);
		
		// 집합 B를 나타낼 리스트
		List<Integer> listB = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<nB; i++) {
			listB.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(listB);
		
		// 답 구하기
		int ans = 0;
		next: for (int i=0; i<nA; i++) {
			int target = listA.get(i);
			long s = 0;
			long e = nB - 1;
			while (s <= e) {
				long mid = (s + e) / 2;
				if (target == listB.get((int)mid)) continue next;
				if (target < listB.get((int)mid)) e = mid - 1;
				else s = mid + 1;
			}
			ans++;
			sb.append(target).append(" ");
		}
		
		// 답 출력
		System.out.println(ans);
		System.out.println(sb);
	}
}
