// boj 2630
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2630 {
	static int[][] paper;
	static int n;
	static int[] cnt = new int[2];
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
		System.out.println(cnt[0]+"\n"+cnt[1]);
	}
	public static int NumOfPaper(int n, int x, int y) {
		if (n==1) {
			cnt[paper[x][y]]++;
			return paper[x][y];
		}
		int[] dx = {0,n/2,0,n/2};
		int[] dy = {0,0,n/2,n/2};
		int[] val = new int[4];
		for (int dir=0; dir<4; dir++) {
			val[dir] = NumOfPaper(n/2, x+dx[dir], y+dy[dir]);
		}
		if (val[0] == 3) return 3;
		int ans = val[0];
		for (int dir=1; dir<4; dir++) {
			if (val[0] != val[dir]) ans = 3;
		}
		if (ans != 3) cnt[ans] -= 3;
		return ans;
	}
}
