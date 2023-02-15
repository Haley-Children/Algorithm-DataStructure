package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_1931 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		ArrayList<int[]> meetings = new ArrayList<>();
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int finish = Integer.parseInt(st.nextToken());
			
			meetings.add(new int[] {start, finish});
		}
		// 더 일찍 끝나는 회의부터 선택한다
		// 선택하려는 회의의 시작이 이전에 선택한 회의의 끝보다 일찍이면 선택하지 않는다
		
		// 이전 회의의 끝나는 시간
		int before = 0;
		// 가능한 회의의 수
		int count = 0;
		
		// 일찍 끝나는 회의가 먼저 오도록 정렬
		Collections.sort(meetings, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]) {
					return o1[0] - o2[0];
				} else {
					return o1[1] < o2[1] ? -1 : 1;
				}
			}
		});
		
		// 시작시간이 이전과 겹치지 않고 먼저 끝나는 회의부터 선택 
		for(int[] m : meetings) {
			if(m[0] >= before) {
				before = m[1];
				count++;
			}
		}
		System.out.println(count);
	}
}

