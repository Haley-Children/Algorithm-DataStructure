package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_1991 {
    static int[][] tree;
    static String indices = ".ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // [i번째 노드]의 [왼쪽, 오른쪽] 인덱스
        tree = new int[indices.length()][2];

        for (int i = 0; i < n; i++) {
            String[] nodes = br.readLine().split(" ");
            // 입력받은 문자를 인덱스로 변환
            int idx = indices.indexOf(nodes[0]);
            int lc = indices.indexOf(nodes[1]);
            int rc = indices.indexOf(nodes[2]);

            // 해당 인덱스의 lc, rc 저장
            tree[idx][0] = lc;
            tree[idx][1] = rc;
        }

        preorder(1);
        sb.append("\n");
        inorder(1);
        sb.append("\n");
        postorder(1);
        sb.append("\n");

        System.out.println(sb);
    }

    static void preorder(int idx) {
        sb.append(indices.charAt(idx));
        if(tree[idx][0] != 0) preorder(tree[idx][0]);
        if(tree[idx][1] != 0) preorder(tree[idx][1]);
    }

    static void inorder(int idx) {
        if(tree[idx][0] != 0) inorder(tree[idx][0]);
        sb.append(indices.charAt(idx));
        if(tree[idx][1] != 0) inorder(tree[idx][1]);
    }

    static void postorder(int idx) {
        if(tree[idx][0] != 0) postorder(tree[idx][0]);
        if(tree[idx][1] != 0) postorder(tree[idx][1]);
        sb.append(indices.charAt(idx));
    }
}
