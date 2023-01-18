// boj 1780
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1780 {
	static int[][] paper;
	static int n;
	static int[] cnt = new int[3];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		paper = new int[n][n];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) paper[i][j] = Integer.parseInt(st.nextToken());
		}
		NumOfPaper(n, 0, 0);
		System.out.println(cnt[0]+"\n"+cnt[1]+"\n"+cnt[2]);
	}
	public static int NumOfPaper(int n, int x, int y) {
		if (n==1) {
			cnt[paper[x][y]+1]++;
			return paper[x][y];
		}
		int[] dx = {0,n/3,2*n/3,0,n/3,2*n/3,0,n/3,2*n/3};
		int[] dy = {0,0,0,n/3,n/3,n/3,2*n/3,2*n/3,2*n/3};
		int[] val = new int[9];
		for (int dir=0; dir<9; dir++) {
			val[dir] = NumOfPaper(n/3, x+dx[dir], y+dy[dir]);
		}
		if (val[0] == 3) return 3;
		int ans = val[0];
		for (int dir=1; dir<9; dir++) {
			if (val[0] != val[dir]) ans = 3;
		}
		if (ans != 3) cnt[ans+1] -= 8;
		return ans;
	}
}