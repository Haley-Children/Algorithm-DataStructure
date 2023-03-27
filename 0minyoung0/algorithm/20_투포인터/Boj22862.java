import java.io.*;
import java.util.*;

public class Boj22862 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	// 수열의 길이 n
    	int n = Integer.parseInt(st.nextToken());
    	// 숫자를 삭제할 수 있는 최대 횟수 k
    	int k = Integer.parseInt(st.nextToken());
    	
    	// 수열에서 홀수인 숫자가 있는 인덱스만 리스트에 저장
    	List<Integer> list = new ArrayList<>();
    	st = new StringTokenizer(br.readLine());
    	for (int i=0; i<n; i++) {
    		int temp = Integer.parseInt(st.nextToken());
    		if (temp % 2 == 1) list.add(i);
    	}
    	
    	// 홀수가 k개 이하인 경우
    	if (list.size() <= k) {
    		System.out.println(n - list.size());
    		return;
    	}
    	
    	// 연속한 부분 수열 중 가장 긴 길이
    	int ans = list.get(k) - k;
    	for (int x=k+1; x<list.size(); x++) {
    		int temp = list.get(x) - list.get(x-k-1) - 1 - k;
    		if (temp > ans) ans = temp;
    	}
    	int temp = n - list.get(list.size() - k - 1) - 1 - k;
    	if (temp > ans) ans = temp;
    	
    	// 답 출력
    	System.out.println(ans);
    }
}