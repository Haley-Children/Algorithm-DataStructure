package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1525_퍼즐 {

    // 처음에 dfs로 풀었다.
    // 문제
    // 1. stackoverflow (메모리초과)
    // 2. pre라는 변수를 두고 직전에서 바꿔주었던 값을 바꾸지 못하도록 해주었는데, 이렇게 해도 계속 순환이 되는 문제가 생겼다.
    // 3. 퍼즐의 상태 자체의 방문여부(만들어진적 있는지 확인 여부)를 배열을 일일이 돌면서 확인해야했다.
    // 4. 최소이동거리(횟수)를 구하기 위해 인자로 일일이 넘겨주는 수고로움이 있었다. bfs는 탐색이 종료되면 최소이동거리를 바로 찾을 수 있으니 더 편할 것이라 생각했다.

    // 바뀐 풀이)
    // bfs + 퍼즐 상태 자체를 저장하는 방식 이용
    // 퍼즐 상태를 배열보다는 한번에 관리하는 것이 편할 것 같아 String으로 상태 저장
    static String target;
    static int[] dix = {-1, 1, 0, 0};
    static int[] diy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String init = "";
        for (int r = 0; r < 3; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 3; c++) {
                init += st.nextToken();
            }
        }
        //System.out.println(init);

        target = "123456780"; // 최종적으로 되어야 할 모양

        int cnt = bfs(init); // bfs 수행 후 이동 횟수 반환
        System.out.println(cnt);

    }

    private static int bfs(String str) {
        Queue<String> queue = new ArrayDeque<>();
        HashSet<String> vis = new HashSet<>(); // 방문한 상태를 저장할 해시셋
        queue.offer(str);
        vis.add(str);
        int cnt = 0; // 이동횟수
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-->0) {
                String cur = queue.poll();
                if (cur.equals(target)) { // 원하는 최종모양이 되었을 때 종료
                    return cnt;
                }
                int zeroIdx = cur.indexOf('0'); // 0이 있는 위치 (실수: 숫자 0으로 하니까 틀렸음/ cur이 아니라 초기값 str에서 탐색해서 틀렸었음)
                int row = zeroIdx / 3;
                int col = zeroIdx % 3;
                for (int i = 0; i < 4; i++) {
                    int nx = row + dix[i];
                    int ny = col + diy[i];
                    if (nx<0 || ny<0 || nx>=3 || ny>=3) continue;
                    // 행, 열을 String 내의 인덱스로 재정의
                    int nIdx = nx*3+ny;
                    // swap하기 위해 array로 변환 후 swap
                    char[] newState = cur.toCharArray();
                    // 0과 다음 값 swap
                    char tmp = newState[nIdx];
                    newState[nIdx] = newState[zeroIdx];
                    newState[zeroIdx] = tmp;
                    // String으로 다시 만들어주기
                    String changedStr = new String(newState);
                    // 된적이 없는 새로운 상태를 큐에 넣고 방문표시
                    if (!vis.contains(changedStr)) {
                        queue.offer(changedStr);
                        vis.add(changedStr);
                    }
                }
            }
            // 상하좌우 한바퀴 끝나면 이동횟수 +1
            cnt++;
        }
        return -1; // 큐가 비기 전에 종료되지 않았다면 이동이 불가능하므로 -1 리턴

    }

}
