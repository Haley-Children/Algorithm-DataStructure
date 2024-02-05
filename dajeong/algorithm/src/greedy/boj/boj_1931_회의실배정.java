package greedy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
1. 완전탐색: O(2^N)
2. DP: O(N^2) -> 퇴사1 문제
- 회의를 끝나는 시간이 빠른 순으로, 끝나는 시간이 같다면 시작 시간이 빠른 순으로 정렬
- d[i] = i번째 회의를 마지막으로 진행했을 때 최대 회의의 수
- d[i] = max(d[j]) + 1 (j번째 회의 끝나는 시간이 i번쨰 회의 시작 시간 이하인 모든 j 탐색)
3. DP + 투포인터: O(NlogN) -> 퇴사2 문제
- 이전의 모든 회의를 탐색하지 않고 dp 수행
- 끝나는 시간 기준으로 정렬한 후, 최대 j의 위치를 포인터로 저장해나가면서 dp배열 갱신
4. 그리디
- 끝나는 시간이 빠른순으로, 끝나는 시간이 같다면 시작하는 시간이 빠른순으로 정렬하여 그리디적으로 선택
 */
public class boj_1931_회의실배정 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 회의의 수
        Conf[] arr = new Conf[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new Conf(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.parallelSort(arr);

        int cnt = 1;
        int e = arr[0].e;
        for (int i = 1; i < N; i++) {
            if (e <= arr[i].s) {
                cnt++;
                e = arr[i].e;
            }
        }
        System.out.println(cnt);
    }

    private static class Conf implements Comparable<Conf>{
        int s;
        int e;

        public Conf(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Conf o) {
            if (e != o.e) {
                return e - o.e;
            } else {
                return s - o.s;
            }
        }

    }
}
