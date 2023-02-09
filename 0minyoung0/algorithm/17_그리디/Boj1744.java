// 그리디.boj1744;

import java.io.*;
import java.util.*;

public class Boj1744 {
	// 양이 아닌 정수는 가장 작은 수 부터 2개씩 묶기
	// 2 이상의 정수는 가장 큰 수 부터 2개씩 묶기
	// 1이나 위의 과정에서 남은 수들은 묶지 않기
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();
        int sCnt = 0; // 0이하의 숫자 개수
        int bCnt = 0; // 2이상의 숫자 개수
        for (int i=0; i<n; i++) {
        	list.add(Integer.parseInt(br.readLine()));
        	if (list.get(i) <= 0) {
        		sCnt++;
        	}
        	else if (list.get(i) >= 2) {
        		bCnt++;
        	}
        }
        // 개수가 홀수개면 1개는 못묶으니까 개수 -1
        if (sCnt % 2 == 1) {
        	sCnt--;
        }
        if (bCnt % 2 == 1) {
        	bCnt--;
        }
        // 정렬
        Collections.sort(list);
        
        int ans = 0;
        // 0 ~ sCnt-1 인덱스 묶어서 추가
        for (int i=0; i<sCnt; i+=2) {
        	ans += list.get(i) * list.get(i+1);
        }
        // sCnt ~ n-bCnt-1 인덱스 안묶고 추가
        for (int i=sCnt; i<n-bCnt; i++) {
        	ans += list.get(i);
        }
        // n-bCnt ~ n-1 인덱스 묶어서 추가
        for (int i=n-bCnt; i<n; i+=2) {
        	ans += list.get(i) * list.get(i+1);
        }
        System.out.println(ans);
    }
}