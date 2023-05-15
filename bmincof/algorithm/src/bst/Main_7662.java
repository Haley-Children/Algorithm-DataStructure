package bst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_7662 {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < t; tc++) {			
			int n = Integer.parseInt(br.readLine());

			// 값, 개수
			TreeMap<Integer, Integer> bst = new TreeMap<>();
			
			for(int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				String cmd = st.nextToken();
				int val = Integer.parseInt(st.nextToken());
				
				if(cmd.equals("I")) {
					if(bst.containsKey(val)) {
						bst.put(val, bst.get(val) + 1);
					} else {
						bst.put(val, 1);
					}
				} else {
					if(bst.isEmpty()) continue;
					
					// 최댓값
					if(val == 1) {
						int mxVal = bst.lastKey();
						if(bst.get(mxVal) == 1) {
							bst.pollLastEntry();
						} else {
							bst.put(mxVal, bst.get(mxVal) - 1);
						}
					// 최솟값
					} else {
						int mnVal = bst.firstKey();
						if(bst.get(mnVal) == 1) {
							bst.pollFirstEntry();
						} else {
							bst.put(mnVal, bst.get(mnVal) - 1);
						}
					}
				}
			}
			
			if(bst.isEmpty()) {
				sb.append("EMPTY");
			} else {
				sb.append(bst.lastKey()).append(" ").append(bst.firstKey());
			}
			sb.append("\n");
			
		}
		
		System.out.println(sb);
	}
}
