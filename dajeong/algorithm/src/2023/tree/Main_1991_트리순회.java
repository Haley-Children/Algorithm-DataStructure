package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1991_트리순회 {

    static int N;
    static int[][] bsTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        bsTree = new int[2][N
            + 1]; // 정점별 왼쪽 자식, 오른쪽 자식 저장하는 배열 => 개인적인 생각) lc, rc 배열 따로 관리하는 것이 더 직관적일 수 있을 것 같다.
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            // 문자가 아닌 숫자로 정점 처리
            int root = st.nextToken().charAt(0) - 'A' + 1;
            int lc = -1; // 자식이 없을 경우 -1
            int rc = -1;
            char lcChar = st.nextToken().charAt(0);
            if (lcChar != '.') {
                lc = lcChar - 'A' + 1;
            }
            char rcChar = st.nextToken().charAt(0);
            if (rcChar != '.') {
                rc = rcChar - 'A' + 1;
            }

            bsTree[0][root] = lc; // 왼쪽 자식
            bsTree[1][root] = rc; // 오른쪽 자식
        }

        preorder(1); // 전위 순회
        System.out.println();
        inorder(1); // 중위 순회
        System.out.println();
        postorder(1); // 후위 순회


    }

    private static void postorder(int v) {
        if (v == -1) {
            return;
        }
        postorder(bsTree[0][v]);
        postorder(bsTree[1][v]);
        System.out.print((char) (v + 65 - 1));
    }

    private static void inorder(int v) {
        if (v == -1) {
            return;
        }
        inorder(bsTree[0][v]);
        System.out.print((char) (v + 65 - 1));
        inorder(bsTree[1][v]);
    }

    private static void preorder(int v) {
        if (v == -1) {
            return;
        }
        System.out.print((char) (v + 65 - 1));
        preorder(bsTree[0][v]);
        preorder(bsTree[1][v]);
    }

}
