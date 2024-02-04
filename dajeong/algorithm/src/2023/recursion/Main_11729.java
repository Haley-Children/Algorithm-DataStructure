package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_11729 {

	// 재귀! - 귀납적 사고를 하도록 노력해보자. 나중에 다시 풀어보기.
	static int cnt;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		recur(1, 3, n);
		System.out.println(cnt);
		System.out.println(sb);
	}

	// 원판 n개를 a번 기둥에서 b번 기둥으로 옮기는 방법을 출력하는 함수.
	private static void recur(int a, int b, int n) {
		if (n == 1) {
			sb.append(a).append(" ").append(b).append("\n");
			cnt++;
			return;
		}
		recur(a, 6-a-b, n-1); // n-1개를 출발지, 목적지가 아닌 장소로 옮겨놓기
		sb.append(a).append(" ").append(b).append("\n"); // 하나 옮기기
		cnt++; // 한 개 이동 횟수 = 1
		recur(6-a-b, b, n-1); // n-1을 목적지로 옮기기
	}
}