import java.io.*;
import java.util.*;

public class Boj17298 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		// 수열의 크기
		int n = Integer.parseInt(br.readLine());
		
		// 오큰수 NGE 배열 선언 및 -1로 초기화
		int[] nge = new int[n];
		for (int i=0; i<n; i++) {
			nge[i] = -1;
		}
		
		// 스택을 덱으로 선언
		Deque<int[]> stack = new ArrayDeque<>();
		
		// 수열 Ai를 받으면서 스택에 값과 주소를 함께 저장
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			int[] cur = new int[] {Integer.parseInt(st.nextToken()), i};
			
			// 스택이 비지 않았고 top의 value가 cur의 value 보다 작으면
			// 오큰수 배열 top의 address 인덱스에 cur value를 저장하고 pop
			while (!stack.isEmpty() && stack.peek()[0] < cur[0]) {
				nge[stack.poll()[1]] = cur[0];
			}
			
			// 스택이 비었거나 top의 value가 cur의 value 보다 크거나 같으면
			// stack에 cur를 push
			stack.offerFirst(cur);
		}
		
		// 오큰수 배열 출력
		for (int i=0; i<n; i++) {
			sb.append(nge[i]).append(" ");
		}
		System.out.println(sb);
	}
}
