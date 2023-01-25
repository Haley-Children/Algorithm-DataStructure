// 정렬.boj1431;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Boj1431 {
	static String[] sArr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		sArr = new String[n];
		for (int i=0; i<n; i++) {
			sArr[i] = br.readLine();
		}
		Arrays.sort(sArr, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1.length() < o2.length()) return -1;
				else if (o1.length() > o2.length()) return 1;
				int n1 = 0;
				int n2 = 0;
				for (int i=0; i<o1.length(); i++) {
					if ('0' <= o1.charAt(i) && o1.charAt(i) <= '9') n1 += o1.charAt(i) - '0';
					if ('0' <= o2.charAt(i) && o2.charAt(i) <= '9') n2 += o2.charAt(i) - '0';
				}
				if (n1!=n2) return n1-n2;
				return o1.compareTo(o2);
			}
		});
		for (int i=0; i<n; i++) {
			sb.append(sArr[i]).append("\n");
		}
		System.out.println(sb);
	}
}