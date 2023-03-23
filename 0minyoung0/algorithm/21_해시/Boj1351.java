import java.io.*;
import java.util.*;

public class Boj1351 {
	static int p, q;
	static Map<Long, Long> hm;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long n = Long.parseLong(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		
		// 자료의 최대 인덱스가 너무 크니까 해시맵을 배열처럼 쓰자!
		hm = new HashMap<>();
		hm.put(0L, 1L);
		
		// 재귀로 답 출력
		System.out.println(func(n));
	}
	private static long func(long i) {
		// 해시맵에 이미 저장된 값이면 바로 리턴
		if (hm.containsKey(i)) return hm.get(i);
		
		// dfs(i/p)와 dfs(i/q)의 합을 계산해서 해시맵에 저장
		long result = func(i/p) + func(i/q);
		hm.put(i, result);
		
		// 리턴
		return result;
	}
}
