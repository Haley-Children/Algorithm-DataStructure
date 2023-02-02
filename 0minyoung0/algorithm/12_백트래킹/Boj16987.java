// 백트래킹.boj16987;

import java.io.*;
import java.util.*;

public class Boj16987 {
    static int n, ans;
    static int[] w;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        int[] s = new int[n];
        w = new int[n];
        for (int i=0; i<n; i++) {
        	st = new StringTokenizer(br.readLine());
        	s[i] = Integer.parseInt(st.nextToken()); // 내구도
        	w[i] = Integer.parseInt(st.nextToken()); // 무게
        }
        // static으로 넘길 데이터 : 계란의 개수 n, 계란의 무게 w, 정답 저장할 ans
        // 인자로 넘길 데이터 : 현재 계란들 상황(지금 들고 있는 계란, 각 계란의 내구도, 남은개수)
        func(0, s, n);
        System.out.println(ans);
    }
    private static void func(int k, int[] s, int eggNum) {
    	// 마지막 계란까지 작업을 끝냈거나 깰 계란이 없으면 깬 계란 개수 리턴
    	if (k==n || eggNum <= 1) {
    		if (ans < n - eggNum) {
    			ans = n - eggNum;
    		}
    		return;
    	}
    	// k번째 계란이 사용가능한지 확인
    	if (s[k] <= 0) {
    		// 불가능하다면 다음 계란 들기
    		func(k+1, s, eggNum);
    		return;
    	}
    	// k번째 계란이 사용가능한 경우
    	// 내려칠 계란 고르기. 손에 든 k번째 계란이 아니면서 내구도가 0 이상
    	for (int i=0; i<n; i++) {
    		if (i!=k && s[i]>0) {
    			// 새 내구도 배열 만들어서 내구도 데이터 복사
    	    	int[] newS = new int[n];
    	    	for (int j=0; j<n; j++) {
    	    		newS[j] = s[j];
    	    	}
    	    	// k번 계란으로 i번 계란 치기!
    	    	int newEggNum = eggNum;
    	    	newS[k] -= w[i];
    	    	if (newS[k] <= 0) {
    	    		newEggNum--;
    	    	}
    	    	newS[i] -= w[k];
    	    	if (newS[i] <= 0) {
    	    		newEggNum--;
    	    	}
    	    	// 다음 계란 들기
    	    	func(k+1, newS, newEggNum);
    		}
    	}
    }
}