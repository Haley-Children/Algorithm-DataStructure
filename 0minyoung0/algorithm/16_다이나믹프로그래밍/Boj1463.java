// dp.boj1463;

import java.io.*;
import java.util.*;

public class Boj1463 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n+1];
		for (int i=2; i<=n; i++) {
			int temp = arr[i-1] + 1;
			if (i % 2 == 0) {
				temp = Math.min(temp, arr[i/2] + 1);
			}
			if (i % 3 == 0) {
				temp = Math.min(temp, arr[i/3] + 1);
			}
			arr[i] = temp;
		}
		System.out.println(arr[n]);
	}
}
