// 정렬.boj2751;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.ArrayList;

public class Boj2751 {
	static int n,m;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i=0; i<n; i++) {
			list.add(Integer.parseInt(br.readLine()));
		}
		Collections.sort(list);
		for (int i=0; i<n; i++) {
			sb.append(list.get(i)).append(" ");
		}
		System.out.println(sb);
	}
}