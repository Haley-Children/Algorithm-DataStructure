package bst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main_23326 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 구역의 개수
        final int N = Integer.parseInt(st.nextToken());
        // 쿼리의 개수
        int q = Integer.parseInt(st.nextToken());

        // 도현이의 현재 위치
        int curr = 0;

        // 명소들을 저장하고 있는 트리셋
        TreeSet<Integer> places = new TreeSet<>();

        st = new StringTokenizer(br.readLine());
        // 1이면 명소로 등록
        for(int i = 0; i < N; i++) {
            if(st.nextToken().equals("1"))
                places.add(i);
        }

        while(q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            // 명소 여부를 토글하기
            if(cmd == 1) {
                // 0-indexed 로 변환
                int number = Integer.parseInt(st.nextToken()) - 1;

                if(places.contains(number))
                    places.remove(number);
                else places.add(number);
            }
            // 시계 방향으로 이동하기
            else if(cmd == 2) {
                // 이동하는 거리
                int dist = Integer.parseInt(st.nextToken());

                curr = (curr + dist) % N;
            }
            // 다음 명소로 이동하기 위해 최소 몇 칸 움직여야 하는지
            else {
                // 명소가 없다면 -1
                if(places.isEmpty()) sb.append(-1);
                else {
                    // 현재보다 번호가 큰 명소 중 가장 가까운 곳을 선택
                    Integer dist = places.ceiling(curr);

                    if(dist == null)
                        dist = N + places.ceiling(0);

                    sb.append(dist - curr);
                }

                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}
