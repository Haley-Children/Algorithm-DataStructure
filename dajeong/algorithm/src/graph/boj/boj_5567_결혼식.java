package graph.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_5567_결혼식 {
    static int n,m;
    static ArrayList<Integer>[] adList;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        adList = new ArrayList[n+1];
        for (int i = 0; i < n+1; i++) {
            adList[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            adList[s].add(e);
            adList[e].add(s);
        }   

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        boolean[] vis = new boolean[n+1];
        vis[1] = true;
        int cnt = 0;
        int loop = 0;
        while (!queue.isEmpty()) {
            if (loop >= 2) {
                break;
            }
            loop++;
            // ** queue.size()는 메소드 호출이며, 메소드로부터 반환된 값은 직접 변경 불가능하므로 변수에 담아서 변경해야 한다.
            int size = queue.size();
            while (size-->0) {
                int cur = queue.poll();
                for(int c : adList[cur]) {
                    if (vis[c]) {
                        continue;
                    }
                    queue.offer(c);
                    vis[c] = true;
                    cnt++;
                }
                
            }
        }
        System.out.println(cnt);
    }
}
