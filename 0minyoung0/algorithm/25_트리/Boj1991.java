import java.io.*;
import java.util.*;

public class Boj1991 {
	static int[][] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 이진트리 노드의 개수
        int n = Integer.parseInt(br.readLine());
        
        // 트리를 배열로 구현
        tree = new int[26][2];
        for (int i=0; i<n; i++) {
        	st = new StringTokenizer(br.readLine());
        	int cur = st.nextToken().charAt(0) - 'A';
        	tree[cur][0] = st.nextToken().charAt(0) - 'A';
        	tree[cur][1] = st.nextToken().charAt(0) - 'A';
        }
        
        // 전위 순회
        preOrder(0);
        System.out.println();
        // 중위 순회
        inOrder(0);
        System.out.println();
        // 후위 순회
        postOrder(0);
    }
    private static void preOrder(int node) {
    	if (node == '.' - 'A') return;
    	System.out.print((char)(node + 'A'));
    	preOrder(tree[node][0]);
    	preOrder(tree[node][1]);
    }
    private static void inOrder(int node) {
    	if (node == '.' - 'A') return;
    	inOrder(tree[node][0]);
    	System.out.print((char)(node + 'A'));
    	inOrder(tree[node][1]);
    }
    private static void postOrder(int node) {
    	if (node == '.' - 'A') return;
    	postOrder(tree[node][0]);
    	postOrder(tree[node][1]);
    	System.out.print((char)(node + 'A'));
    }
}