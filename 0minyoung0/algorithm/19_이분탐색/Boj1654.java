import java.io.*;
import java.util.*;

public class Boj1654 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 랜선의 개수
        int k = Integer.parseInt(st.nextToken());
        // 필요한 랜선의 개수
        int n = Integer.parseInt(st.nextToken());
        
        // 각 랜선의 길이
        int[] arr = new int[k];
        for (int i=0; i<k; i++) {
        	arr[i] = Integer.parseInt(br.readLine());
        }
        
        // 이분 탐색
        long s = 1;
        long e = Integer.MAX_VALUE;
        while (s < e) {
        	long mid = (s+e+1)/2;
        	long lan = 0;
        	for (int i=0; i<k; i++) {
        		lan += arr[i] / mid;
        	}
        	if (lan < n) e = mid - 1;
        	else s = mid;
        }
        System.out.println(s);
    }
}