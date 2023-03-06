import java.io.*;

public class Boj9251 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		String s2 = br.readLine();
		
		// Longest Common Subsequence 구하기
		// 배열의 첫행, 첫열은 모두 0
		// 두 문자가 같으면 arr[i+1][j+1] = arr[i][j] + 1;
		// 두 문자가 다르면 arr[i+1][j+1] = (arr[i+1][j]와 arr[i][j+1]중 큰 값)
		int[][] arr = new int[s1.length()+1][s2.length()+1];
		
		for (int i=0; i<s1.length(); i++) {
			for (int j=0; j<s2.length(); j++) {
				if (s1.charAt(i) == s2.charAt(j)) {
					arr[i+1][j+1] = arr[i][j] + 1;
				}else {
					arr[i+1][j+1] = arr[i+1][j] > arr[i][j+1]? arr[i+1][j] : arr[i][j+1];
				}
			}
		}
		
		System.out.println(arr[s1.length()][s2.length()]);
	}
}
