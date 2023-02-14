// 배열.boj3273;

import java.io.*;
import java.util.*;

public class Boj3273 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // n이 백만 이하 -> O(nlogn) 풀이
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        // 정렬할거라서 List에 넣음
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<n; i++) {
        	list.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(list);
        int x = Integer.parseInt(br.readLine());
        int ans = 0;
        // 포인터 두개 사용해서 양끝에서부터 다가오는 방식
        int cur1 = 0;
        int cur2 = list.size()-1;
        while (cur1 != cur2) {
        	// 합이 목표와 같다면 ans에 1추가
        	if (list.get(cur1) + list.get(cur2) == x) {
        		ans++;
        	}
        	// 합이 목표보다 작거나 같을때
        	if (list.get(cur1) + list.get(cur2) <= x) {
        		cur1++;
        	}
        	// 합이 목표보다 클 때
        	else {
        		cur2--;
        	}
        }
        System.out.println(ans);
    }
}