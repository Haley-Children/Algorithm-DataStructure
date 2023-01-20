// boj 1992
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1992 {
	static char[][] video;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String s;
		video = new char[n][n];
		for (int i=0; i<n; i++) {
			s = br.readLine();
			for (int j=0; j<n; j++) video[i][j] = s.charAt(j);
		}
		System.out.println(zip(n,0,0));
	}
	static String zip(int n, int x, int y) {
		if (n==1) return Character.toString(video[x][y]);
		String[] temp = new String[4];
		int[] dx = {0,0,n/2,n/2};
		int[] dy = {0,n/2,0,n/2};
		for (int dir=0; dir<4; dir++) temp[dir] = zip(n/2, x+dx[dir], y+dy[dir]);
		if (temp[0].length() == 1 && temp[0].equals(temp[1]) && temp[0].equals(temp[2]) && temp[0].equals(temp[3])) return temp[0];
		return "("+temp[0]+temp[1]+temp[2]+temp[3]+")";
	}
}