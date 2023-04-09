package trie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_5052 {
    static final int ROOT = 1;
    static int unused;
    static boolean[] check;
    static int[][] next;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스의 수
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            List<String> numbers = new ArrayList<>();

            int size = n * 10 + 1;
            unused = 2;
            check = new boolean[size];
            next = new int[size][10];

            // 하나라도 다른 번호에 접두어가 되면 true
            boolean isDuplicated = false;
            while (n-- > 0) numbers.add(br.readLine());

            Collections.sort(numbers);
            for(String number : numbers)
                isDuplicated |= insert(number.toCharArray());
            sb.append(isDuplicated ? "NO" : "YES").append("\n");
        }

        System.out.println(sb);
    }

    // 접두어 트라이
    static boolean insert(char[] s) {
        int curr = ROOT;
        boolean hasPrefix = false;
        for(char c : s) {
            // 처음 방문하는 노드면 추가
            if(next[curr][c-'0'] == 0) next[curr][c-'0'] = unused++;
            curr = next[curr][c-'0'];

            // 이미 접두어가 등록됐으면 일관성 없는 번호
            if(check[curr]) hasPrefix = true;
        }

        check[curr] = true;
        return hasPrefix;
    }
}
