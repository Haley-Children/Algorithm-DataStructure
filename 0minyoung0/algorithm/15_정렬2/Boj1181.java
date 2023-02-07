// 정렬.boj1181;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Boj1181 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		String[] sArr = new String[n];
		for (int i=0; i<n; i++) {
			sArr[i] = br.readLine();
		}
		Arrays.sort(sArr, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				if (s1.length() < s2.length()) return -1;
				else if (s1.length() > s2.length()) return 1;
				return s1.compareTo(s2);
			}
		});
		sb.append(sArr[0]).append("\n");
		for (int i=1; i<n; i++) {
			if (!sArr[i].equals(sArr[i-1])) sb.append(sArr[i]).append("\n");
		}
		System.out.println(sb);
	}
}
