import java.io.*;
import java.util.*;

public class Boj20922 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 길이가 n인 수열에서 같은 정수를 k개 이하로 포함하는 최장 연속 부분 수열의 길이 구하기
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		// 수열
		int[] a = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		// 숫자가 나온 횟수를 카운팅 할 해시맵
		HashMap<Integer, Integer> hm = new HashMap<>();
		
		// 답을 저장할 변수
		int ans = n;
		
		// k+1개를 가지고있는 숫자를 저장할 변수
		int over = -1;
		
		// 투포인터
		int s = 0;
		int e = 0;

		// 같은 숫자가 k+1개가 포함될때까지 해시맵에 추가
		for (int i=0; i<n; i++) {
			if (!hm.containsKey(a[i])) {
				hm.put(a[i], 1);
			}else {
				hm.put(a[i], hm.get(a[i]) + 1);
			}
			
			if (hm.get(a[i]) > k) {
				ans = i;
				e = i;
				over = a[i];
				break;
			}
		}
		
		// 답이 n인 경우 출력 후 리턴
		if (ans == n) {
			System.out.println(n);
			return;
		}
		
		// 투포인터로 찾기
		find: while (true) {
			// 같은 숫자가 최대 k개가 될때까지 해시맵에서 제거
			while (true) {
				// 제거하기
				if (hm.get(a[s]) == 1) {
					hm.remove(a[s]);
				}else {
					hm.put(a[s], hm.get(a[s]) - 1);
				}
				
				if (a[s++] == over) break;
			}
			
			// 같은 숫자가 k+1개가 포함될때까지 해시맵에 추가
			while (true) {
				// 수열의 끝에 도달한 경우
				if (++e == n) {
					if (ans < e - s) ans = e - s;
					break find;
				}
				
				// 추가하기
				if (!hm.containsKey(a[e])) {
					hm.put(a[e], 1);
				}else {
					hm.put(a[e], hm.get(a[e]) + 1);
				}
				
				if (hm.get(a[e]) > k) {
					if (ans < e - s) ans = e - s;
					over = a[e];
					break;
				}
			}
		}
		
		// 답 출력
		System.out.println(ans);
	}
}
