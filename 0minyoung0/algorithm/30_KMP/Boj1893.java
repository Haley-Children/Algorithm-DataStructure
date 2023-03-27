import java.io.*;
import java.util.*;

public class Boj1893 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스의 수
		int N = Integer.parseInt(br.readLine());
		
		// 테스트 케이스별로 실행
		while (N-- > 0) {
			
			// 알파벳의 순서
			char[] A = br.readLine().toCharArray();
			HashMap<Character, Integer> aMap = new HashMap<>();
			for (int i=0; i<A.length; i++) {
				aMap.put(A[i], i);
			}
			
			// 원문
			char[] W = br.readLine().toCharArray();
			int[] w = new int[W.length];
			for (int i=0; i<W.length; i++) {
				w[i] = aMap.get(W[i]);
			}
			
			// 암호문
			char[] S = br.readLine().toCharArray();
			int[] s = new int[S.length];
			for (int i=0; i<S.length; i++) {
				s[i] = aMap.get(S[i]);
			}
			
			// 원문의 실패함수
			int[] f = failure(w);
			
			// 원문이 한번만 등장하는 시프트 값을 저장할 리스트
			List<Integer> list = new ArrayList<>();
			
			// 시프트 값이 shift인 경우 탐색
			for (int shift=0; shift<A.length; shift++) {
				
				// 시프트값에 따라 원문이 등장하는 횟수를 세기
				int cnt = 0;
				
				// 암호문을 i만큼 시프트 되돌리기
				int[] ss = new int[S.length];
				for (int j=0; j<S.length; j++) {
					ss[j] = (s[j] - shift + A.length) % A.length;
				}
				
				// KMP로 ss에서 w찾기
				int j=0;
				for (int i=0; i<ss.length; i++) {
					while (j > 0 && ss[i] != w[j]) j = f[j-1];
					if (ss[i] == w[j]) {
						if (++j == w.length) {
							j = f[j-1];
							cnt++;
						}
					}
				}
				
				// 원문이 한번 등장했다면 list에 넣기
				if (cnt == 1) list.add(shift);
				
			}
			
			// 답 저장
			if (list.isEmpty()) {
				sb.append("no solution\n");
			}else {
				if (list.size() == 1) sb.append("unique:");
				else sb.append("ambiguous:");
				for (int x : list) sb.append(" ").append(x);
				sb.append("\n");
			}
			
			// 리스트 클리어
			list.clear();
		}
		
		// 답 출력
		System.out.println(sb);
	}
	
	// 실패함수 구하는 메서드
	private static int[] failure(int[] arr) {
		int[] f = new int[arr.length];
		int j = 0;
		for (int i=1; i<arr.length; i++) {
			while (j > 0 && arr[i] != arr[j]) j = f[j-1];
			if (arr[i] == arr[j]) f[i] = ++j;
		}
		return f;
	}
}
