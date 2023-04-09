package trie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 트라이를 이용해서 풀어보기
public class Main_14425 {
    static final int ROOT = 1;
    static int unused = 2;
    static int size;
    static boolean[] check;
    static int[][] next;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 미리 저장해놓을 문자열의 수
        int n = Integer.parseInt(st.nextToken());
        // 검색할 문자열의 수
        int m = Integer.parseInt(st.nextToken());

        // 최대 등장 가능한 글자의 수
        size = n * 500 + 1;
        // [i]번 정점이 마지막 글자인 단어가 있는지
        check = new boolean[size];
        // [i]번 정점 [j]번째 알파벳에 해당하는 다음 노드
        next = new int[size][26];

        // 등록하기
        while(n-- > 0) insert(br.readLine());

        int count = 0;
        // 검색하기
        while(m-- > 0) if(find(br.readLine())) count++;

        System.out.println(count);
    }

    static void insert(String s) {
        int curr = ROOT;

        for(char c : s.toCharArray()) {
            // 다음 자리가 비었으면
            if(next[curr][c-'a'] == 0) {
                next[curr][c-'a'] = unused++;
            }
            // 다음 노드로 이동
            curr = next[curr][c-'a'];
        }
        // 단어가 끝나는 자리에 표시
        check[curr] = true;
    }

    static boolean find(String s) {
        int curr = ROOT;

        for(char c : s.toCharArray()) {
            if(next[curr][c-'a'] == 0) return false;
            curr = next[curr][c-'a'];
        }
        // 해당 자리에 표시된 단어가 있는지 반환
        return check[curr];
    }

}
