// 그리디.boj2457;

import java.io.*;
import java.util.*;

public class Boj2457 {
	public static void main(String[] args) throws IOException {
		// 3월 1일 : 60, 11월 30일 : 334
		int[] month = {0,31,59,90,120,151,181,212,243,273,304,334};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		List<int[]> flowers = new ArrayList<>();
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int temp1 = month[Integer.parseInt(st.nextToken()) - 1];
			temp1 += Integer.parseInt(st.nextToken());
			int temp2 = month[Integer.parseInt(st.nextToken()) - 1];
			temp2 += Integer.parseInt(st.nextToken());
			flowers.add(new int[] {temp1, temp2});
		}
		// 피는 시작일 기준 정렬, 같으면 지는 날짜 기준
		Collections.sort(flowers, new Comparator<int[]>(){
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]!=o2[0] ? o1[0]-o2[0] : o1[1]-o2[1];
			}
		});
		// 3/1에 포인터 p1, p2를 둔다
		int p1 = 60;
		int p2 = 60;
		// flowers를 순회할 인덱스 변수 선언
		int index = 0;
		// 핀 꽃의 개수를 저장할 정답 변수 선언
		int ans = 0;
		// n보다 작은 index에 대해서 순회한다.
		while(index < n) {
			// p1보다 일찍 피는 꽃중에서 가장 늦게 지는 꽃의 날짜에 p2를 둔다
			if (flowers.get(index)[0] <= p1) {
				// 11/30보다 늦게 지는 꽃을 찾았다면 ans에 1을 더해서 답을 출력하고 종료한다
				if (flowers.get(index)[1] > 334) {
					System.out.println(ans + 1);
					return;
				}
				// 지는 날짜가 p2보다 크다면 p2를 갱신한다
				p2 = Math.max(p2, flowers.get(index)[1]);
				// 순회를 위해 index를 증가시킨다
				index++;
			}
			// p1보다 일찍 피지 않는 꽃에 도달하면 아래 조건을 확인한다
			else {
				// p1과 p2가 같다면 꽃이 이어서 피지 못했으므로 불가능
				if (p1 == p2) {
					System.out.println(0);
					return;
				}
				// p1 < p2라면 꽃 개수 카운트를 1 증가시키고 p1을 p2의 위치로 옮긴다
				ans++;
				p1 = p2;
			}
		}
		// n-1번 index까지 순회했는데 종료 조건에 도달하지 못했으면 불가능
		System.out.println(0);
	}
}















