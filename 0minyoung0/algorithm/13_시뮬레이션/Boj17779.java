import java.io.*;
import java.util.*;

public class Boj17779 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        // 재현시의 크기
        int n = Integer.parseInt(br.readLine());
        
        // 재현시의 인구 정보
        int[][] pop = new int[n][n];
        for (int i=0; i<n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j=0; j<n; j++) {
        		pop[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // 인구가 가장 많은 선거구와 가장 적은 선거구의 인구 차의 최솟값
        int ans = Integer.MAX_VALUE;
        
        // 가능한 모든 경우의 수 탐색
        for (int x=0; x<n-2; x++) {
        	for (int y=1; y<n-1; y++) {
        		d1: for (int d1=1; d1<n-1; d1++) {
        			d2: for (int d2=1; d2<n-1; d2++) {
        				if (x+d1 >= n || y-d1 < 0) {
        					break d1;
        				}
        				if (x+d1+d2 >=n || y+d2 >= n) {
        					break d2;
        				}
        				
        				int[] p = new int[6];
        				for (int r=0; r<n; r++) {
        					for (int c=0; c<n; c++) {
        						if (r<x+d1 && c<=y && r+c<x+y) {
        							p[1] += pop[r][c];
        						}
        						else if (r<=x+d2 && c>y && r-c<x-y) {
        							p[2] += pop[r][c];
        						}
        						else if (r>=x+d1 && c<y-d1+d2 && r-c>x-y+2*d1) {
        							p[3] += pop[r][c];
        						}
        						else if (r>x+d2 && c>=y-d1+d2 && r+c>x+y+2*d2) {
        							p[4] += pop[r][c];
        						}
        						else {
        							p[5] += pop[r][c];
        						}
        					}
        				}
        				
        				int max_idx = 1;
        				int min_idx = 1;
        				for (int i=2; i<=5; i++) {
        					if (p[max_idx] < p[i]) {
        						max_idx = i;
        					} else if (p[min_idx] > p[i]) {
        						min_idx = i;
        					}
        				}
        				
        				ans = ans < p[max_idx] - p[min_idx]? ans : p[max_idx] - p[min_idx];
        			}
        		}
        	}
        }
        
        System.out.println(ans);
    }
}