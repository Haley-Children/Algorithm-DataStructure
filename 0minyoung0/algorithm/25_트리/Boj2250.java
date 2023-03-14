import java.io.*;
import java.util.*;

public class Boj2250 {
	static int col, maxLevel;
	static int[] level, colNum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 노드의 개수
		int n = Integer.parseInt(br.readLine());
		
		// 트리를 배열로 구현
		int[][] tree = new int[n+1][2];
		
		// 부모 노드를 배열에 저장
		int[] p = new int[n+1];
		
		// 트리 정보 저장
		for (int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			int cur = Integer.parseInt(st.nextToken());
			tree[cur][0] = Integer.parseInt(st.nextToken());
			tree[cur][1] = Integer.parseInt(st.nextToken());
			if (tree[cur][0] != -1) {
				p[tree[cur][0]] = cur;
			}
			if (tree[cur][1] != -1) {
				p[tree[cur][1]] = cur;
			}
		}
		
		// 루트 찾기
		int root = 1;
		while (p[root] != 0) {
			root = p[root];
		}
		
		// inorder 순회
		level = new int[n+1];
		colNum = new int[n+1];
		inorder(root, 1, tree);
		
		// 각 높이의 왼쪽, 오른쪽 끝 노드 저장
		int[][] edge = new int[maxLevel+1][2];
		for (int i=1; i<=maxLevel; i++) {
			edge[i][0] = n;
		}
		for (int i=1; i<=n; i++) {
			int l = level[i];
			int c = colNum[i];
			edge[l][0] = c < edge[l][0]? c : edge[l][0];
			edge[l][1] = c > edge[l][1]? c : edge[l][1];
		}
		
		// 너비가 가장 넓은 레벨 찾기
		int ans = 0;
		int maxWidth = 0;
		for (int i=1; i<=maxLevel; i++) {
			if (edge[i][1] - edge[i][0] + 1 > maxWidth) {
				ans = i;
				maxWidth = edge[i][1] - edge[i][0] + 1;
			}
		}
		
		// 답 출력
		System.out.println(ans + " " + maxWidth);
	}
	
	// inorder 순회하면서 각 노드의 레벨과 열 번호를 저장
	private static void inorder(int curNode, int curLevel, int[][] tree) {
		if (tree[curNode][0] != -1) {
			inorder(tree[curNode][0], curLevel+1, tree);
		}
		
		level[curNode] = curLevel;
		colNum[curNode] = ++col;
		maxLevel = curLevel > maxLevel? curLevel: maxLevel;
		
		if (tree[curNode][1] != -1) {
			inorder(tree[curNode][1], curLevel+1, tree);
		}
	}
}
