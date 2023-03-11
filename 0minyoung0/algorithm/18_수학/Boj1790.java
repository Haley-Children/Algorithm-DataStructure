import java.io.*;
import java.util.*;

public class Boj1790 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // i자리 숫자가 몇번째 숫자까지 차지하는지 저장하기 위한 배열
        int[] arr = new int[10];
        for (int i=1; i<=8; i++) {
        	arr[i] = i * 9;
        	for (int j=1; j<i; j++) {
        		arr[i] *= 10;
        	}
        	arr[i] += arr[i-1];
        }
        arr[9] = 1000000000;
        
        // 1부터 n까지의 수를 이어서 쓰기
        int n = Integer.parseInt(st.nextToken());
        // 앞에서 k번째 숫자가 어떤 숫자인지
        int k = Integer.parseInt(st.nextToken());
        
        // k번째 숫자가 포함된 숫자가 몇자리짜리인지
        int digit = 0;
        for (int i=1; i<=9; i++) {
        	if (k<=arr[i]) {
        		digit = i;
        		break;
        	}
        }
        
        // digit자리 숫자들 중에서 몇번째 숫자의 몇번째 자리인지 찾기
        k -= arr[digit-1];
        int n1 = (k - 1) / digit + 1;
        int n2 = (k - 1) % digit + 1;

        // 만약 digit자리 숫자들중에 n1번째 숫자가 n보다 크다면 -1 출력 후 리턴
        int temp = 1;
        for (int i=1; i<digit; i++) {
        	temp *= 10;
        }
        temp--;
        if (temp + n1 > n) {
        	System.out.println(-1);
        	return;
        }
        
        // n2번째 숫자 출력
        System.out.println(String.valueOf(temp+n1).charAt(n2-1));
    }
}