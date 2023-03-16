import java.io.*;
import java.util.*;

public class Boj10815 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		// 숫자 카드의 수
		int n = Integer.parseInt(br.readLine());
		
		// 숫자 카드 정보 리스트에 담아서 정렬
		List<Integer> list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(list);
		
		// 질문의 수
		int m = Integer.parseInt(br.readLine());
		
		// 질문에 해당하는 답
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<m; i++) {
			int target = Integer.parseInt(st.nextToken());
			boolean find = false;
			int s = 0;
			int e = n-1;
			while (s <= e) {
				int mid = (s+e)/2;
				if (list.get(mid) == target) {
					find = true;
					break;
				}
				if (list.get(mid) < target) s = mid + 1;
				else e = mid - 1;
			}
			
			if(find) {
				sb.append("1 ");
			}else {
				sb.append("0 ");
			}
		}
		
		// 답 출력
		System.out.println(sb);
	}
}
