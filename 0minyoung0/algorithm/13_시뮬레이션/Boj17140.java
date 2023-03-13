import java.io.*;
import java.util.*;

public class Boj17140 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int r = Integer.parseInt(st.nextToken()) - 1;
    	int c = Integer.parseInt(st.nextToken()) - 1;
    	int k = Integer.parseInt(st.nextToken());
    	
    	int rCnt = 3;
    	int cCnt = 3;
    	int[][] A = new int[100][100];
    	for (int i=0; i<rCnt; i++) {
    		st = new StringTokenizer(br.readLine());
    		for (int j=0; j<cCnt; j++) {
    			A[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	// 초기 상태에서 조건 만족 -> 0출력 후 리턴
    	if (A[r][c] == k) {
    		System.out.println(0);
    		return;
    	}
    	
    	// 100초가 지나도 ans가 안바뀌면 -1 출력을 위해 -1로 초기화
    	int ans = -1;
    	
    	for (int t=1; t<=100; t++) {
    		// 행의 개수가 열의 개수보다 더 많거나 같을 때
    		if (rCnt >= cCnt) {
    			// 새로운 열의 개수를 저장할 변수
	    		int newCCnt = 0;
	    		
	    		// 각 행에 대해 실행
	    		for (int i=0; i<rCnt; i++) {
	    			// 해쉬맵 선언
	    			HashMap<Integer, Integer> map = new HashMap<>();
	    			
	    			for (int j=0; j<cCnt; j++) {
	    				// 배열 안의 숫자가 0인경우 무시
	    				if (A[i][j] == 0) continue;
	    				// 맵에 카운팅하며 추가
	    				if (!map.containsKey(A[i][j])) {
	    					map.put(A[i][j], 1);
	    				}else {
	    					map.put(A[i][j], map.get(A[i][j]) + 1);
	    				}
	    			}
	    			
	    			// 맵의 키를 리스트에 담고 규칙에 따라 정렬
	    			List<Integer> list = new ArrayList<>(map.keySet());
	    			Collections.sort(list, new Comparator<Integer>() {
						@Override
						public int compare(Integer o1, Integer o2) {
							return map.get(o1)!=map.get(o2) ? map.get(o1)-map.get(o2) : o1-o2;
						}
	    			});
	    			
	    			// 새로운 열의 개수를 결정
	    			newCCnt = newCCnt>2*list.size()? newCCnt : 2*list.size();
	    			newCCnt = newCCnt<100? newCCnt : 100;
	    			
	    			// 새로운 행 데이터를 배열에 저장
	    			for (int j=0; j<(list.size()<50 ? list.size() : 50); j++) {
	    				A[i][2*j] = list.get(j);
	    				A[i][2*j+1] = map.get(list.get(j));
	    			}
	    			for (int j=2*list.size(); j<cCnt; j++) {
	    				A[i][j] = 0;
	    			}
	    		}
	    		
	    		// 새로운 열 개수를 cCnt에 갱신
	    		cCnt = newCCnt;
    		}
    		
    		// 위와 행,열만 바뀐 구조
    		else { // rCnt < cCnt
	    		int newRCnt = 0;
	    		
	    		for (int i=0; i<cCnt; i++) {
	    			HashMap<Integer, Integer> map = new HashMap<>();
	    			
	    			for (int j=0; j<rCnt; j++) {
	    				if (A[j][i] == 0) continue;
	    				if (!map.containsKey(A[j][i])) {
	    					map.put(A[j][i], 1);
	    				}else {
	    					map.put(A[j][i], map.get(A[j][i]) + 1);
	    				}
	    			}
	    			
	    			List<Integer> list = new ArrayList<>(map.keySet());
	    			Collections.sort(list, new Comparator<Integer>() {
						@Override
						public int compare(Integer o1, Integer o2) {
							return map.get(o1)!=map.get(o2) ? map.get(o1)-map.get(o2) : o1-o2;
						}
	    			});
	    			
	    			newRCnt = newRCnt>2*list.size()? newRCnt : 2*list.size();
	    			newRCnt = newRCnt<100? newRCnt : 100;
	    			
	    			for (int j=0; j<(list.size()<50 ? list.size() : 50); j++) {
	    				A[2*j][i] = list.get(j);
	    				A[2*j+1][i] = map.get(list.get(j));
	    			}
	    			for (int j=2*list.size(); j<cCnt; j++) {
	    				A[j][i] = 0;
	    			}
	    		}
	    		
	    		rCnt = newRCnt;
    		}
    		
    		// 배열의 r행 c열이 k이면 ans를 현재 초로 갱신하고 break
    		if (A[r][c] == k) {
    			ans = t;
    			break;
    		}
    	}
    	
    	// 답 출력
    	System.out.println(ans);
    }
}