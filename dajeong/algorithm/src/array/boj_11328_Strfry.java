package array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_11328_Strfry {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 배열 완전탐색으로 돌면서 찾으면 길이 다른 경우 조건 놓칠 수 없어서 더 좋은듯?
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int[] arr = new int[27];

            String a = st.nextToken();
            String b = st.nextToken();

            // 서로 문자열이 같으면 바로 Possible 리턴
            if (a.equals(b)) {
                sb.append("Possible").append("\n");
                continue;
            }
            // *** 길이가 다르면 Impossible
            if (a.length() != b.length()) {
                sb.append("Impossible").append("\n");
                continue;
            }
            // 첫번째 문자열 카운트 배열 입력
            for (char c : a.toCharArray()) {
                arr[c - 'a']++;
            }
            // 두번째 문자열 돌면서 카운트 배열에 없으면 Impossible, 있으면 계속 진행하면서 -1처리
            boolean flag = true;
            for (char c : b.toCharArray()) {
                if (arr[c - 'a'] <= 0) {
                    flag = false;
                    sb.append("Impossible").append("\n");
                    break;
                } else {
                    arr[c - 'a']--;
                }
            }

            // 중간에 break 되지 않았다면 Possible 리턴
            if (flag) {
                sb.append("Possible").append("\n");
            }
        }
        System.out.println(sb);
    }
}
