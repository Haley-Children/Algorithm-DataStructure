import java.io.*;
import java.util.*;

public class Boj2531 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 회전 초밥 벨트에 놓인 접시의 수
		int n = Integer.parseInt(st.nextToken());
		// 초밥의 가짓수
		int d = Integer.parseInt(st.nextToken());
		// 연속해서 먹는 접시의 수
		int k = Integer.parseInt(st.nextToken());
		// 쿠폰 번호
		int c = Integer.parseInt(st.nextToken());
		
		// 회전 초밥 벨트 위의 초밥
		int[] sushi = new int[n];
		for (int i=0; i<n; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		
		// 먹을 초밥을 저장할 해시맵
		HashMap<Integer, Integer> hm = new HashMap<>();
		
		// 0번부터 k-1번까지 접시를 해시맵에 추가
		for (int i=0; i<k; i++) {
			if (!hm.containsKey(sushi[i])) {
				hm.put(sushi[i], 1);
			}else {
				hm.put(sushi[i], hm.get(sushi[i]) + 1);
			}
		}
		
		// 먹을 수 있는 초밥 종류 세기
		int ans = hm.size();
		// 쿠폰으로 추가로 먹을 수 있으면 + 1
		if (!hm.containsKey(c)) ans++;
		
		// 1번부터 먹는 경우 ~ n-1번부터 먹는 경우
		for (int i=0; i<n-1; i++) {
			// 이미 k+1개의 초밥을 먹을 수 있으면 답 출력 후 리턴
			if (ans == k+1) {
				System.out.println(k+1);
				return;
			}
			
			// i번 초밥 제거
			if (hm.get(sushi[i]) == 1) {
				hm.remove(sushi[i]);
			}else {
				hm.put(sushi[i], hm.get(sushi[i]) - 1);
			}
			
			// (i+k)%n번 초밥 추가
			if (!hm.containsKey(sushi[(i+k)%n])) {
				hm.put(sushi[(i+k)%n], 1);
			}else {
				hm.put(sushi[(i+k)%n], hm.get(sushi[(i+k)%n]) + 1);
			}
			
			// 먹을 수 있는 초밥 종류 세기
			int temp = hm.size();
			// 쿠폰으로 추가로 먹을 수 있으면 + 1
			if (!hm.containsKey(c)) temp++;
			
			// ans 갱신
			if (ans < temp) ans = temp;
		}
		
		// 답 출력
		System.out.println(ans);
	}
}
