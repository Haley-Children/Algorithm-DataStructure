import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		System.out.println(power(a,b,c));
		
	}
	
	static long power(int a, int k, int c) {
		if(k==1) return a % c;
		
		long result = power(a, k/2, c);
		result = result * result % c;
		if (k % 2 == 0) return result;
		return result * a % c;
	}
}

