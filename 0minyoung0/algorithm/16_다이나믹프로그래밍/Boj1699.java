import java.io.*;

public class Boj1699 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n+1];
		for (int i=1; i*i<=n; i++) {
			arr[i*i] = 1;
		}
		
		for (int i=2; i<=n; i++) {
			if (arr[i] == 0) {
				int temp = Integer.MAX_VALUE;
				for (int j=1; j*j<i; j++) {
					temp = temp > arr[j*j] + arr[i-j*j]? arr[j*j] + arr[i-j*j] : temp;
				}
				arr[i] = temp;
			}
		}
		
		System.out.println(arr[n]);
	}
}
