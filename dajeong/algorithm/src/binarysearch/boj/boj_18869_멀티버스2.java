package binarysearch.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;


public class boj_18869_멀티버스2 {
    static int N,M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 우주의 개수
        N = Integer.parseInt(st.nextToken()); // 우주에 있는 행성의 개수

        // 정렬 후 중복 제거를 위해 set에 저장
        HashSet<String> sets = new HashSet<>();
        ArrayList<int[]> list = new ArrayList<>();
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int[] tmpSpace = new int[N];
            int[] tmpOrder = new int[N];
            for (int n = 0; n < N; n++) {
                tmpSpace[n] = Integer.parseInt(st.nextToken());
                tmpOrder[n] = tmpSpace[n];
            }
            Arrays.parallelSort(tmpOrder);
            String s = Arrays.toString(tmpOrder);
            if (!sets.contains(s)) {
                list.add(tmpSpace);
                sets.add(s);
            }
        }

        // 우주 구성이 같은 쌍의 갯수를 저장할 맵
        HashMap<String, Integer> map = new HashMap<>();
        // set iterator 확인해보기
        list.forEach(
            space -> {
                // System.out.println(Arrays.toString(space));
                int[] ub = new int[N];
                int[] sortedSpace = space.clone(); // 정렬!!
                Arrays.parallelSort(sortedSpace);
                for (int i = 0; i < N; i++) {
                    ub[i] = upperBound(space[i], sortedSpace);
                }
                String str = Arrays.toString(ub);
                map.put(str, map.getOrDefault(str, 0)+1);
            }
        );

        // 서로 구성이 같은 멀티버스 쌍의 갯수 찾기 (map 순회하며 nC2)
        int ans = 0;
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            // System.out.println(e.getKey()+": "+e.getValue());
            ans += (e.getValue() * (e.getValue()-1) / 2);
        }

        System.out.println(ans);

    }

    private static int upperBound(int x, int[] space) {
        int s = 0;
        int e = space.length;

        while (s < e) {
            int mid = (s + e) >>> 1;
            if (space[mid] <= x) {
                s = mid + 1;
            } else {
                e = mid;
            }
        }
        return e;
    }
}
