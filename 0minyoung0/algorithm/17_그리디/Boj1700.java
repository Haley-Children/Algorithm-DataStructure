import java.io.*;
import java.util.*;

public class Boj1700 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 멀티탭 구멍의 개수
		int n = Integer.parseInt(st.nextToken());
		// 전기 용품의 총 사용 횟수 (전기용품 이름도 k이하의 자연수)
		int k = Integer.parseInt(st.nextToken());
		
		// 각 제품이 사용되는 순번을 배열과 큐에 저장
		int[] arr = new int[k+1];
		Queue<Integer>[] qList = new Queue[k+1];
		for (int i=1; i<=k; i++) {
			qList[i] = new ArrayDeque<>();
		}
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=k; i++) {
			int temp = Integer.parseInt(st.nextToken());
			arr[i] = temp;
			qList[temp].offer(i);
		}
		
		// 어떤 제품이 사용 중인지, 몇개의 제품이 사용 중인지 확인
		boolean[] use = new boolean[k+1];
		int useCnt = 0;
		
		// 플러그 시뮬
		int ans = 0;
		for (int i=1; i<=k; i++) {
			// 이미 사용중인 제품인경우 넘어감
			if (use[arr[i]]) {
				qList[arr[i]].poll();
				continue;
			}
			// 멀티탭 구멍이 남은 경우
			if (useCnt < n) {
				useCnt++;
			}
			// 멀티탭 구멍이 없는 경우
			else {
				// 아무튼 뽑을거니까 정답 1 증가
				ans++;
				
				// 사용중인 멀티탭을 비교해서 안쓸 코드 찾기
				int target = -1;
				int value = 0;
				for (int j=1; j<=k; j++) {
					if (use[j] && qList[j].size() == 0) {
						target = j;
						break;
					}
				}
				
				// 안쓸 코드가 없다면 사용중인 멀티탭 중 가장 나중에 쓸 코드 찾기
				if (target == -1) {
					for (int j=1; j<=k; j++) {
						if (use[j] && qList[j].peek() > value) {
							target = j;
							value = qList[j].peek();
						}
					}
				}
				
				// 찾은 코드 뽑기
				use[target] = false;
			}
			// 코드 꽂기
			use[arr[i]] = true;
			qList[arr[i]].poll();
		}
		
		// 답 출력
		System.out.println(ans);
	}
}
