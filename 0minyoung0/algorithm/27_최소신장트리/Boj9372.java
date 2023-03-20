import java.io.*;
import java.util.*;

public class Boj9372 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	// 테스트 케이스 수
    	int T = Integer.parseInt(br.readLine());
    	
    	// 테스트 케이스 별로 실행
    	for (int t=1; t<=T; t++) {
    		st = new StringTokenizer(br.readLine());
    		// 국가의 수
    		int n = Integer.parseInt(st.nextToken());
    		// 비행기의 종류
    		int m = Integer.parseInt(st.nextToken());
    		
    		// 비행기 정보 (버림)
    		while (m-- != 0) {
    			br.readLine();
    		}
    		
    		// 답 출력
    		System.out.println(n-1);
    	}
    }
}