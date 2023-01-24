// 시뮬레이션.boj18808;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj18808 {
	static int n,m,k,ans;
	static int[][] laptop;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		laptop = new int[n][m];
		k = Integer.parseInt(st.nextToken());
		for (int i=0; i<k; i++) {
			int degree = 0;
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int[][] sticker = new int[r][c];
			for (int x=0; x<r; x++) {
				st = new StringTokenizer(br.readLine());
				for (int y=0; y<c; y++) {
					sticker[x][y] = Integer.parseInt(st.nextToken());
				}
			}
			isAttached(0, r, c, sticker);
		}
		System.out.println(ans);
	}
	private static boolean isAttached(int degree, int stX, int stY, int[][] sticker) {
		if (degree == 360) return false; // 한바퀴 돈 경우
		if (stX<=n && stY<=m) { // 스티커가 노트북보다 크지 않다면
			// 붙여보기
			for (int i=0; i<=n-stX; i++) {
				label: for (int j=0; j<=m-stY; j++) {
					for (int x=0; x<stX; x++) {
						for (int y=0; y<stY; y++) {
							if (sticker[x][y] == 1 && laptop[i+x][j+y] == 1) {
								continue label;
							}
						}
					}
					// continue 안타고 여기 도달하면 붙일 수 있는 상태. 노트북에 붙이고 ans증가. true 리턴
					for (int x=0; x<stX; x++) {
						for (int y=0; y<stY; y++) {
							if (sticker[x][y] == 1) {
								laptop[i+x][j+y] = 1;
								ans++;
							}
						}
					}
					return true;
				}
			}
		}
		// true 리턴에 실패하면 90도 돌려서 재귀 호출
		degree += 90;
		int[][] rotatedSticker = new int[stY][stX];
		for (int i=0; i<stX; i++) {
			for (int j=0; j<stY; j++) {
				rotatedSticker[j][stX-i-1] = sticker[i][j];
			}
		}
		return isAttached(degree, stY, stX, rotatedSticker);
	}
}
