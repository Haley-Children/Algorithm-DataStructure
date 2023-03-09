import java.io.*;

public class Boj2011 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String password = br.readLine();
		
		int s = password.length();
		
		if (s == 1) {
			if (password.equals("0")) {
				System.out.println(0);
			}else {
				System.out.println(1);
			}
			return;
		}
		
		int[][] arr = new int[2][s];
		
		if (password.charAt(s-1) != '0') {
			arr[0][s-1] = 1;
		}
		if (password.charAt(s-2) != '0') {
			arr[0][s-2] = arr[0][s-1];
		}
		if (10 <= (password.charAt(s-2)-'0')*10 + (password.charAt(s-1)-'0')
				&& (password.charAt(s-2)-'0')*10 + (password.charAt(s-1)-'0') <= 26) {
			arr[1][s-2] = 1;
		}
		
		for (int i=s-3; i>=0; i--) {
			if (password.charAt(i) != '0') {
				arr[0][i] = (arr[0][i+1] + arr[1][i+1]) % 1000000;
			}
			if (10 <= (password.charAt(i)-'0')*10 + (password.charAt(i+1)-'0')
					&& (password.charAt(i)-'0')*10 + (password.charAt(i+1)-'0') <= 26) {
				arr[1][i] = (arr[0][i+2] + arr[1][i+2]) % 1000000;
			}
		}
		
		System.out.println((arr[0][0] + arr[1][0]) % 1000000);
	}
}