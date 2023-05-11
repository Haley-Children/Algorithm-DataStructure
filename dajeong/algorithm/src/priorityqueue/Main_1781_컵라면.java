package priorityqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1781_컵라면 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int ramenCnt = Integer.parseInt(st.nextToken());
            list.add(new int[]{deadline, ramenCnt});
        }
        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0]==o2[0]) return o2[1]-o1[1];
                return o1[0]-o2[0];
            }
        });

        for (int i = 0; i < list.size(); i++) {
            int[] problem = list.get(i);
//            System.out.println("problem = " + problem[0]+" "+problem[1]);
//            System.out.println("minHeap = " + minHeap.size());
            if (minHeap.size()<problem[0]) {
                minHeap.offer(problem[1]);
            } else if (minHeap.size()==problem[0]) {
                if (minHeap.peek() < problem[1]) {
                    minHeap.poll();
                    minHeap.offer(problem[1]);
                }
            }
        }
        int cnt = 0;
        while(!minHeap.isEmpty()) {
            cnt += minHeap.poll();
        }
        System.out.println(cnt);

    }

}
