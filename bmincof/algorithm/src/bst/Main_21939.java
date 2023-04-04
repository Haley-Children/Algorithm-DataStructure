package bst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_21939 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		
		TreeSet<Problem> set = new TreeSet<>();
		Map<Integer, Problem> problems = new HashMap<>();
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int num = Integer.parseInt(st.nextToken());
			int diff = Integer.parseInt(st.nextToken());
			
			Problem p = new Problem(num, diff);
			problems.put(num, p);
			set.add(p);
		}
		
		int m = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			String cmd = st.nextToken();
			
			if(cmd.equals("add")) {
				int num = Integer.parseInt(st.nextToken());
				int diff = Integer.parseInt(st.nextToken());
				
				Problem p = new Problem(num, diff);
				
				problems.put(num, p);
				set.add(p);
				
			} else if(cmd.equals("solved")) {
				int num = Integer.parseInt(st.nextToken());
				
				Problem p = problems.remove(num);
				set.remove(p);
				
			} else if(cmd.equals("recommend")) {
				int query = Integer.parseInt(st.nextToken());
				
				if(query == 1) {
					sb.append(set.last().number).append("\n");
				} else {
					sb.append(set.first().number).append("\n");
				}
			}
		}
		
		System.out.println(sb);
	}
	
	static class Problem implements Comparable<Problem> {
		int number;
		int difficulty;
		
		Problem(int number, int difficulty) {
			this.number = number;
			this.difficulty = difficulty;
		}
		
		public int compareTo(Problem o) {
			if(this.difficulty == o.difficulty) {
				return this.number - o.number;
			}
			
			return this.difficulty - o.difficulty;
		}
	}
}

