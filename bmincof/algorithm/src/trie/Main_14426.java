package trie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14426 {
    static final int ROOT = 1;
    static int unused = 2;
    static boolean[] check;
    static int[][] next;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int size = n * 500 + 1;
        check = new boolean[size];
        next = new int[size][26];

        while(n-- > 0) insert(br.readLine().toCharArray());

        int cnt = 0;
        while(m-- > 0) if(find(br.readLine().toCharArray())) cnt++;

        System.out.println(cnt);
    }

    static void insert(char[] s) {
        int curr = ROOT;
        for(char c : s) {
            if(next[curr][c-'a'] == 0) {
                next[curr][c-'a'] = unused++;
            }
            curr = next[curr][c-'a'];
            // 가능한 접두사마다 체크하기
            check[curr] = true;
        }
    }

    static boolean find(char[] s) {
        int curr = ROOT;
        for(char c : s) {
            if(next[curr][c-'a'] == 0) return false;
            curr = next[curr][c-'a'];
        }
        return check[curr];
    }

}
