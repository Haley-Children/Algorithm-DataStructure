import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11399 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] cost = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		// 앞에서 사용한 시간만큼 뒷사람이 기다리는 시간에 누적되는 구조
		// -> 먼저 사용하는 사람이 빨리 끝날수록 모든 사람이 기다리는 총 시간의 합이 줄어든다
		// -> 정렬해서 비용이 작은 사람부터 진행하면 된다
		Arrays.sort(cost);
		
		// 모든 사람이 기다린 총 시간의 합
		int total = 0;
		
		// i번째 사람이 기다린 시간
		int sum = 0;
		for(int i = 0; i < n; i++) {
			sum += cost[i];
			total += sum;
		}
		
		System.out.println(total);
	}
	
}

