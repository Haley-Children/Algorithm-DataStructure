import java.io.*;

public class Boj1038 {
	static int n, digit;
	static int[] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // n번째 감소하는 수 찾기
        n = Integer.parseInt(br.readLine());
        
        // 조합 계산을 위한 배열 (Combination[n][k] = nCk)
        int[][] Combination = new int[11][11];
        for (int i=0; i<=10; i++) {
        	Combination[i][0] = 1;
        	for (int j=1; j<=i; j++) {
        		Combination[i][j] = Combination[i-1][j-1] + Combination[i-1][j];
        	}
        }
        
        // 찾는 수의 자리 수 찾기
        int temp_sum = 0;
        for (int i=1; i<=10; i++) {
        	temp_sum += Combination[10][i];
        	if (n < temp_sum) {
        		digit = i;
        		n -= (temp_sum - Combination[10][i]);
        		break;
        	}
        }
        
        // n번째 감소하는 수가 없다면 -1 출력 후 리턴
        if (digit == 0) {
	        System.out.println(-1);
	        return;
        }
        
        // 백트래킹을 위한 배열 초기화
        ans = new int[digit];
	        
        // digit 자리수의 n번째 수 찾기 백트래킹
        find(0, 10);
    }
    private static void find(int d, int pre) {
    	// digit 자리 숫자를 모두 골랐으면
    	if (d == digit) {
    		// n이 0이면 답 출력, n에 -1
    		if (n-- == 0) {
    			for (int i=0; i<digit; i++) {
    				System.out.print(ans[i]);
    			}
    		}
    		// 리턴
    		return;
    	}
    	// d번째 자리 수 고르기
    	for (int i=0; i<pre; i++) {
    		ans[d] = i;
    		find(d+1, i);
    	}
    }
}