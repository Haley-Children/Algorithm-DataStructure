import java.io.*;
import java.util.*;

public class Boj2004 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // nCm의 끝자리 0의 개수 찾아서 출력하기
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        // nCm = nCn-m
        m = m > n/2? n-m: m;
        
        // 약수에 포함된 2의 개수 세기
        int cnt2 = 0;
        for (long i=2; i<=n; i*=2) {
        	cnt2 += n/i - (n-m)/i - m/i;
        }
        
        // 약수에 포함된 5의 개수 세기
        int cnt5 = 0;
        for (long i=5; i<=n; i*=5) {
        	cnt5 += n/i - (n-m)/i - m/i;
        }
        
        // 답 출력
        System.out.println(Math.min(cnt2, cnt5));
    }
}