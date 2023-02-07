import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_11047 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] fLine = br.readLine().split(" ");
		int n = Integer.parseInt(fLine[0]);
		int k = Integer.parseInt(fLine[1]);
		
		int[] coins = new int[n];
		
		for(int i = 0; i < n; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		// i번째 동전 단위가 i-1번째 동전 단위의 배수이므로 그리디 적용가능
		
		// 총 사용한 동전의 개수
		int count = 0;
		
		for(int i = n-1; i >= 0; i--) {
			if(k >= coins[i]) {
				count += k / coins[i];
				k %= coins[i];
			}
			
		}
		
		System.out.println(count);
		
	}
}

