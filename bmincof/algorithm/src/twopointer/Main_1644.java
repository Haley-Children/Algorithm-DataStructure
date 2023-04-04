package twopointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_1644 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int n = Integer.parseInt(br.readLine());
		
		boolean[] isNotPrime = new boolean[n+1];
		List<Integer> primes = new ArrayList<>();
		
		for(int i = 2; i*i <= n; i++) {
			if(isNotPrime[i]) continue;
			primes.add(i);
			
			for(int j = i*2; j <= n; j+=i) {
				isNotPrime[j] = true;
			}
		}
		
		int end = 0;
		int sum = 2;
		
		int count = 0;
		for(int start = 0; start < primes.size(); start++) {
			while(end < primes.size() && sum < n) {
				if(++end == primes.size()) break;
				sum += primes.get(end);
			}
			
			if(end == primes.size()) break;
			if(sum == n) count++;
			sum -= primes.get(start);
		}
		
		System.out.println(count);
	}
}

