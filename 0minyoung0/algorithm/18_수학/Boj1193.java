import java.io.*;

public class Boj1193 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int x = Integer.parseInt(br.readLine());
		
		int idx = -1;
		for (int i=1; i<4500; i++) {
			if (i*(i+1)/2+1 > x) {
				idx = i;
				break;
			}
		}
		x -= (idx-1)*idx/2;
		
		if (idx % 2 == 1) {
			System.out.println((idx+1-x)+"/"+x);
		}else {
			System.out.println(x+"/"+(idx+1-x));
		}
	}
}