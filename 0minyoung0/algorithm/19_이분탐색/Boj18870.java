import java.io.*;
import java.util.*;

public class Boj18870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        List<Integer> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
        	int temp = Integer.parseInt(st.nextToken());
        	arr[i] = temp;
        	list.add(temp);
        }

        // 리스트 정렬
        Collections.sort(list);
        // 중복 제거 리스트
        List<Integer> list2 = new ArrayList<>();
        list2.add(list.get(0));
        for (int i=1; i<n; i++) {
        	if ((int)list.get(i-1) != (int)list.get(i)) {
        		list2.add(list.get(i));
        	}
        }
        
        // 이분탐색으로 해당 숫자보다 더 작은 숫자의 개수 찾기
        for (int i=0; i<n; i++) {
        	int s = 0;
        	int e = list2.size()-1;
        	while(s < e) {
        		int mid = (s + e) / 2;
        		if (list2.get(mid) < arr[i]) s = mid + 1;
        		else e = mid;
        	}
        	sb.append(s).append(" ");
        }
        
        // 답 출력
        System.out.println(sb);
    }
}