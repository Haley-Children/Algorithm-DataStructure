// 시뮬레이션.boj14500;

import java.io.*;
import java.util.*;

public class Boj14500 {
	static int[][] map; // 원래 종이
	static int[][] map2; // 옆으로 돌린 종이
	static int n,m,ans; // 가로, 세로, 답
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        map2 = new int[m][n];
        for (int i=0; i<n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j=0; j<m; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        		map2[j][n-1-i] = map[i][j];
        	}
        }
        int temp = 0;
        // 1x4짜리 완전탐색
        for (int i=0; i<n; i++) {
        	for (int j=0; j<m-3; j++) {
        		temp = 0;
        		temp += map[i][j] + map[i][j+1] + map[i][j+2] + map[i][j+3];
        		if (temp > ans) ans = temp;
        	}
        }
        for (int i=0; i<m; i++) {
        	for (int j=0; j<n-3; j++) {
        		temp = 0;
        		temp += map2[i][j] + map2[i][j+1] + map2[i][j+2] + map2[i][j+3];
        		if (temp > ans) ans = temp;
        	}
        }
        // 2x2짜리 완전탐색
        for (int i=0; i<n-1; i++) {
        	for (int j=0; j<m-1; j++) {
        		temp = 0;
        		temp += map[i][j] + map[i][j+1] + map[i+1][j] + map[i+1][j+1];
        		if (temp > ans) ans = temp;
        	}
        }
        // 2x3 T모양, ㄴ모양, Z모양 완전탐색
        for (int i=0; i<n-1; i++) {
        	for (int j=0; j<m-2; j++) {
        		int sum6 = map[i][j] + map[i][j+1] + map[i][j+2] + map[i+1][j] + map[i+1][j+1] + map[i+1][j+2];
        		temp = sum6 - map[i][j] - map[i][j+1];
        		if (temp > ans) ans = temp;
        		temp = sum6 - map[i][j] - map[i][j+2];
        		if (temp > ans) ans = temp;
        		temp = sum6 - map[i][j] - map[i+1][j+2];
        		if (temp > ans) ans = temp;
        		temp = sum6 - map[i][j+1] - map[i][j+2];
        		if (temp > ans) ans = temp;
        		temp = sum6 - map[i][j+2] - map[i+1][j];
        		if (temp > ans) ans = temp;
        		temp = sum6 - map[i+1][j] - map[i+1][j+1];
        		if (temp > ans) ans = temp;
        		temp = sum6 - map[i+1][j] - map[i+1][j+2];
        		if (temp > ans) ans = temp;
        		temp = sum6 - map[i+1][j+1] - map[i+1][j+2];
        		if (temp > ans) ans = temp;
        	}
        }
        for (int i=0; i<m-1; i++) {
        	for (int j=0; j<n-2; j++) {
        		int sum6 = map2[i][j] + map2[i][j+1] + map2[i][j+2] + map2[i+1][j] + map2[i+1][j+1] + map2[i+1][j+2];
        		temp = sum6 - map2[i][j] - map2[i][j+1];
        		if (temp > ans) ans = temp;
        		temp = sum6 - map2[i][j] - map2[i][j+2];
        		if (temp > ans) ans = temp;
        		temp = sum6 - map2[i][j] - map2[i+1][j+2];
        		if (temp > ans) ans = temp;
        		temp = sum6 - map2[i][j+1] - map2[i][j+2];
        		if (temp > ans) ans = temp;
        		temp = sum6 - map2[i][j+2] - map2[i+1][j];
        		if (temp > ans) ans = temp;
        		temp = sum6 - map2[i+1][j] - map2[i+1][j+1];
        		if (temp > ans) ans = temp;
        		temp = sum6 - map2[i+1][j] - map2[i+1][j+2];
        		if (temp > ans) ans = temp;
        		temp = sum6 - map2[i+1][j+1] - map2[i+1][j+2];
        		if (temp > ans) ans = temp;
        	}
        }
        System.out.println(ans);
    }
}