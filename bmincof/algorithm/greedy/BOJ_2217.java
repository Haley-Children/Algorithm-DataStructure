import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2217 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] ropes = new int[n];
		for(int i = 0; i < n; i++) {
			ropes[i] = Integer.parseInt(br.readLine());
		}
		
		// 로프를 중량별로 정렬한다
		Arrays.sort(ropes);
		
		// k번째 로프가 들 수 있는 중량만큼 n-k개의 로프가 들 수 있다. k번째 로프를 기준으로 삼았을 때 한계 중량 = (n-k) * k
		// 한계 중량이 가장 큰 경우를 고르면 됨
		int maxWeight = Integer.MIN_VALUE;
		for(int i = 0; i < n; i++) {
			maxWeight = Math.max(maxWeight, ropes[i] * (n-i));
		}
		
		System.out.println(maxWeight);
	}
}

