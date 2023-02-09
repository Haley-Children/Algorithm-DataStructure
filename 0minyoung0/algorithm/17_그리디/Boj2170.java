// 그리디.boj2170;

import java.io.*;
import java.util.*;

public class Boj2170 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        List<int[]> list = new ArrayList<>();
        for (int i=0; i<n; i++) {
        	st = new StringTokenizer(br.readLine());
        	int temp1 = Integer.parseInt(st.nextToken());
        	int temp2 = Integer.parseInt(st.nextToken());
        	list.add(new int[] {temp1, temp2});
        }
        
        // n이 백만까지니까 O(NlogN) 가능 => Collections.sort
        Collections.sort(list, new Comparator<int[]>() {
        	@Override
        	// 시작점 기준으로 정렬하고, 같으면 종료점 기준으로 정렬
        	public int compare (int[] o1, int[] o2) {
        		return o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1];
        	}
        });
        
        // 정답 변수 초기값
        int ans = 0;
        // 현재 확인하고 있는 선의 시작점
        int start = list.get(0)[0];
        // 현재 확인하고 있는 선의 종료점
        int end = list.get(0)[1];
        
        // 다음 점을 계속 확인
        for (int i=1; i<n; i++) {
        	// 현재 확인하고 있는 선의 연장이라면
        	if (list.get(i)[0] <= end) {
        		// 종료점을 갱신
        		end = Math.max(end, list.get(i)[1]);
        	}
        	// 현재 확인하고 있는 선 바깥에서 시작한다면
        	else {
        		// 정답 변수에 지금까지의 선의 길이 저장
        		ans += end - start;
        		// 새로운 선의 시작점과 종료점 정해주기
        		start = list.get(i)[0];
        		end = list.get(i)[1];
        	}
        }
        // 다 돌면 마지막 선까지 ans에 추가
        ans += end - start;
        // 정답 출력
        System.out.println(ans);
    }
}