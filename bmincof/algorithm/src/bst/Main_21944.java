package bst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main_21944 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		final int shift = 1000000;
		// 초기화
		int N = Integer.parseInt(br.readLine());
		
		// 문제를 알고리즘 별로 저장하기 위한 트리 배열 (0번은 알고리즘 분류 x)
		TreeSet<Integer>[] problemSet = new TreeSet[101];
		for(int i = 0; i < 101; i++) {
			problemSet[i] = new TreeSet<>();
		}
		
		// [i]번 문제의 알고리즘 분류를 저장하기 위한 배열, 삭제 쿼리에 사용
		int[][] problemInfo = new int[100001][2];
		
		// 기본적으로 들어 있는 문제를 입력하기
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			// 문제 번호
			int pbNum = Integer.parseInt(st.nextToken());
			// 난이도
			int pbDif = Integer.parseInt(st.nextToken());
			// 분류
			int category = Integer.parseInt(st.nextToken());
			
			problemInfo[pbNum][0] = pbDif;
			problemInfo[pbNum][1] = category;
			// 문제번호와 난이도를 합쳐서 하나의 값으로 저장하기
			int code = pbDif * shift + pbNum;
			
			problemSet[0].add(code);
			problemSet[category].add(code);
		}
		
		// 쿼리 개수
		int Q = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			
			String query = st.nextToken();
			
			// 알고리즘 분류 별로 검색
			if(query.equals("recommend")) {
				int category = Integer.parseInt(st.nextToken());
				int option = Integer.parseInt(st.nextToken());
				
				if(option == 1) {
					sb.append(problemSet[category].last() % shift).append("\n");				
				} else {
					sb.append(problemSet[category].first() % shift).append("\n");
				}
			// 알고리즘 상관 없이 검색
			} else if(query.equals("recommend2")) {
				int option = Integer.parseInt(st.nextToken());
				
				if(option == 1) {
					sb.append(problemSet[0].last() % shift).append("\n");
				} else {
					sb.append(problemSet[0].first() % shift).append("\n");
				}
			// 난이도 지정 검색
			} else if(query.equals("recommend3")) {
				int option = Integer.parseInt(st.nextToken());
				// 난이도
				int diff = Integer.parseInt(st.nextToken());
				
				if(option == 1) {
					Integer found = problemSet[0].higher(diff * shift);
					sb.append(found == null ? -1 : found % shift).append("\n");
				} else {
					Integer found = problemSet[0].lower(diff * shift);
					sb.append(found == null ? -1 : found % shift).append("\n");
				}
			// 추가 쿼리
			} else if(query.equals("add")){
				// 문제 번호
				int pbNum = Integer.parseInt(st.nextToken());
				// 난이도
				int pbDif = Integer.parseInt(st.nextToken());
				// 분류
				int category = Integer.parseInt(st.nextToken());
								
				problemInfo[pbNum][0] = pbDif;
				problemInfo[pbNum][1] = category;
				// 문제번호와 난이도를 합쳐서 하나의 값으로 저장하기
				int code = pbDif * shift + pbNum;
				
				problemSet[0].add(code);
				problemSet[category].add(code);
			// 삭제 쿼리
			} else {
				int pbNum = Integer.parseInt(st.nextToken());
				int pbDif = problemInfo[pbNum][0];
				int category = problemInfo[pbNum][1];
				
				int code = pbDif * shift + pbNum;
				
				problemSet[0].remove(code);
				problemSet[category].remove(code);
			}
		}
		
		System.out.println(sb);
	}
	
}

