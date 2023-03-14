import java.io.*;
import java.util.*;

public class Boj21276 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 석호촌에 사는 사람의 수
        int n = Integer.parseInt(br.readLine());
        
        // 현재 살고 있는 사람들의 이름 저장
        ArrayList<String> people = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        HashMap<String, Integer> hm1 = new HashMap<>();
        HashMap<Integer, String> hm2 = new HashMap<>();
        for (int i=0; i<n; i++) {
        	String name = st.nextToken();
        	people.add(name);
        	hm1.put(name, i);
        	hm2.put(i, name);
        }
        
        // 정보의 개수
        int m = Integer.parseInt(br.readLine());
        
        // 정보를 받으면서 선후관계 리스트와 indegree 배열에 넣기
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i=0; i<=n; i++) {
        	graph.add(new ArrayList<>());
        }
        int[] indegree = new int[n+1];
        for (int i=0; i<m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int x = hm1.get(st.nextToken());
        	int y = hm1.get(st.nextToken());
        	graph.get(y).add(x);
        	indegree[x]++;
        }
        
        // 큐에 indegree가 0인 사람 번호 넣기
        Queue<Integer> q = new ArrayDeque<>();
        for (int i=0; i<n; i++) {
        	if (indegree[i] == 0) {
        		q.offer(i);
        	}
        }
        
        // 가문의 개수 = 현재 큐에 담긴 사람의 수
        sb.append(q.size()).append("\n");
        
        // 각 가문의 시조의 이름 = 현재 큐에 담긴 사람 이름
        ArrayList<String> ancestors = new ArrayList<>();
        for (int i=0; i<q.size(); i++) {
        	int temp = q.poll();
        	ancestors.add(hm2.get(temp));
        	q.offer(temp);
        }
        Collections.sort(ancestors);
        for (int i=0; i<ancestors.size(); i++) {
        	sb.append(ancestors.get(i)).append(" ");
        }
        sb.append("\n");
        
        // 큐에서 BFS 돌리면서 각 사람의 자식 정보 저장
        ArrayList<ArrayList<String>> children = new ArrayList<>();
        for (int i=0; i<=n; i++) {
        	children.add(new ArrayList<>());
        }
        while(!q.isEmpty()) {
        	int cur = q.poll();
        	for (int x : graph.get(cur)) {
        		if (--indegree[x] == 0) {
        			children.get(cur).add(hm2.get(x));
        			q.offer(x);
        		}
        	}
        }
        
        // 사전순으로 사람의 이름, 자식의 수, 사전순으로 자식의 이름
        Collections.sort(people);
        for (int i=0; i<n; i++) {
        	String name = people.get(i);
        	sb.append(name).append(" ");
        	
        	int num = hm1.get(name);
        	int cNum = children.get(num).size();
        	sb.append(cNum).append(" ");
        	
        	Collections.sort(children.get(num));
        	for (int j=0; j<cNum; j++) {
        		sb.append(children.get(num).get(j)).append(" ");
        	}
        	sb.append("\n");
        }
        
        // 답 출력
        System.out.println(sb);
    }
}