// 정렬.boj11652;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Boj11652 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		HashMap<String, Integer> dic = new HashMap<String, Integer>();
		for (int i=0; i<n; i++) {
			String temp = br.readLine();
			int cnt = 1;
			if (dic.containsKey(temp)) {
				cnt = dic.get(temp) + 1;	
			}
			dic.put(temp, cnt);
		}
		String s = "";
		int freq = 0;
		for (String key : dic.keySet()) {
			if (dic.get(key) > freq) {
				s = key;
				freq = dic.get(key);
			}
			else if (dic.get(key) == freq) {
				if (Long.parseLong(key) < Long.parseLong(s)) {
					s = key;
					freq = dic.get(key);
				}
			}
		}
		System.out.println(s);
	}
}