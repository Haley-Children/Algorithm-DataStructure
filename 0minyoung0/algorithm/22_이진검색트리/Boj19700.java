import java.io.*;
import java.util.*;

public class Boj19700 {
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	// 학생의 수
    	int n = Integer.parseInt(br.readLine());
    	
    	// 학생을 저장할 우선순위 큐
    	PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
    		// 키가 큰 사람부터 넣기, 키가 같다면 최소 등수가 작은 사람부터 넣기
    		public int compare(int[] o1, int[] o2) {
    			if (o1[0] != o2[0]) return o2[0] - o1[0];
    			return o1[1] - o2[1];
    		}
    	});
    	
    	// 우선 순위큐에 학생 저장
    	while (n-- > 0) {
    		st = new StringTokenizer(br.readLine());
    		int h = Integer.parseInt(st.nextToken());
    		int k = Integer.parseInt(st.nextToken());
    		pq.add(new int[] {h, k});
    	}
    	
    	// 팀을 저장할 트리맵
    	TreeMap<Integer, Integer> map = new TreeMap<>();
    	
    	// 우선 순위큐에서 학생을 꺼내면서 팀에 넣기
    	while (!pq.isEmpty()) {
    		// 팀에 넣을 학생
    		int[] cur = pq.poll();
    		
    		// 최소 등수보다 학생 수가 적은 팀(중에서 가장 학생 수가 많은 팀)의 인원 수
    		Integer team = map.lowerKey(cur[1]);
    		
    		// 최소 등수보다 작은 팀이 없다면
    		if (team == null) {
    			// 팀을 하나 만들기
    			if (!map.containsKey(1)) {
    				map.put(1, 1);
    			}else {
    				map.put(1, map.get(1) + 1);
    			}
    		}
    		
    		// 최소 등수보다 작은 팀이 있다면
    		else {
    			// 해당 인원의 팀 하나 제거
    			if (map.get(team) == 1) {
    				map.remove(team);
    			}else {
    				map.put(team, map.get(team) - 1);
    			}
    			
    			// 해당 인원+1인 인원의 팀 하나 추가
    			if (!map.containsKey(team+1)) {
    				map.put(team+1, 1);
    			}else {
    				map.put(team+1, map.get(team+1) + 1);
    			}
    		}
    	}
    	
    	// 팀 개수 세기
    	int ans = 0;
    	for (int x : map.keySet()) {
    		ans += map.get(x);
    	}
    	System.out.println(ans);
    }
}