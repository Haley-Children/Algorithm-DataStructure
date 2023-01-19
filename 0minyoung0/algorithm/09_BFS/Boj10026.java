// boj 10026
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;

public class Boj10026 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		char[][] pic = new char[n][n];
		for (int i=0; i<n; i++) {
			String s = br.readLine();
			for (int j=0; j<n; j++) pic[i][j] = s.charAt(j);
		}
		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,1,-1};
		boolean[][] vis1 = new boolean[n][n];
		int cnt1 = 0;
		Queue<int[]> Q1 = new LinkedList<>();
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				if (vis1[i][j]) continue;
				cnt1++;
				char temp = pic[i][j];
				Q1.add(new int[] {i,j});
				vis1[i][j] = true;
				while (!Q1.isEmpty()) {
					int[] cur = Q1.poll();
					int curX = cur[0];
					int curY = cur[1];
					for (int dir=0; dir<4; dir++) {
						int nx = curX + dx[dir];
						int ny = curY + dy[dir];
						if (nx<0 || nx>=n || ny<0 || ny>=n) continue;
						if (vis1[nx][ny] || pic[nx][ny] != temp) continue;
						Q1.add(new int[] {nx,ny});
						vis1[nx][ny] = true;
					}
				}
			}
		}
		for (int i=0; i<n; i++) for (int j=0; j<n; j++) if (pic[i][j] == 'G') pic[i][j] = 'R';
		boolean[][] vis2 = new boolean[n][n];
		int cnt2 = 0;
		Queue<int[]> Q2 = new LinkedList<>();
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				if (vis2[i][j]) continue;
				cnt2++;
				char temp = pic[i][j];
				Q2.add(new int[] {i,j});
				vis2[i][j] = true;
				while (!Q2.isEmpty()) {
					int[] cur = Q2.poll();
					int curX = cur[0];
					int curY = cur[1];
					for (int dir=0; dir<4; dir++) {
						int nx = curX + dx[dir];
						int ny = curY + dy[dir];
						if (nx<0 || nx>=n || ny<0 || ny>=n) continue;
						if (vis2[nx][ny] || pic[nx][ny] != temp) continue;
						Q2.add(new int[] {nx,ny});
						vis2[nx][ny] = true;
					}
				}
			}
		}
		System.out.println(cnt1+" "+cnt2);
	}
}