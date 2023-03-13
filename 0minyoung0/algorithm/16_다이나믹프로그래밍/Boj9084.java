import java.io.*;
import java.util.*;

public class Boj9084 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        // 테스트 케이스 개수
        int T = Integer.parseInt(br.readLine());
        
        // 테스트 케이스별 실행
        for (int t=1; t<=T; t++) {
            // 동전의 가지 수
            int n = Integer.parseInt(br.readLine());
            
            // 동전의 각 금액 (오름차순)
            st = new StringTokenizer(br.readLine());
            int[] coins = new int[n];
            for (int i=0; i<n; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            
            // 만들어야 할 금액
            int m = Integer.parseInt(br.readLine());
            
            // 특정 가격을 만들 수 있는 가지수를 담을 배열
            int[] ans = new int[m+1];
            ans[0] = 1;
            for (int j=coins[0]; j<=m; j+=coins[0]) {
            	ans[j] = ans[j-coins[0]];
            }
            for (int i=1; i<n; i++) {
                for (int j=coins[i]; j<=m; j++) {
                    ans[j] += ans[j-coins[i]];
                }
            }
            
            // 답 출력
            System.out.println(ans[m]);
        }
    }
}