// 정렬.boj2910;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2910 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		int cnt = 0;
		label: for (int i=0; i<n; i++) {
			int temp = Integer.parseInt(st.nextToken());
			arr[i] = temp;
			for (int j=0; j<i; j++) {
				if (arr[i] == arr[j]) continue label;
			}
			cnt++;
		}
		int[][] newArr = new int[cnt][2];
		int idx = 0;
		label: for (int i=0; i<n; i++) {
			for (int j=0; j<idx; j++) {
				if (arr[i] == newArr[j][1]) {
					newArr[j][0]++;
					continue label;
				}
			}
			newArr[idx][0] = 1;
			newArr[idx++][1] = arr[i];
		}
		for (int i=0; i<cnt-1; i++) {
			for (int j=0; j<cnt-i-1; j++) {
				if(newArr[j][0] < newArr[j+1][0]) {
					int temp = newArr[j][0];
					newArr[j][0] = newArr[j+1][0];
					newArr[j+1][0] = temp;
					temp = newArr[j][1];
					newArr[j][1] = newArr[j+1][1];
					newArr[j+1][1] = temp;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<cnt; i++) {
			for (int j=0; j<newArr[i][0]; j++) {
				sb.append(newArr[i][1]).append(" ");
			}
		}
		System.out.println(sb);
	}
}