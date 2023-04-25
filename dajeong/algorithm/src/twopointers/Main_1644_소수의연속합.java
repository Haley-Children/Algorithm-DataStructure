package twopointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_1644_소수의연속합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 소수 배열 만들기
        boolean[] isPrime = new boolean[N+1];
        Arrays.fill(isPrime, true);
        isPrime[1] = false;
        for (int i = 2; i * i<= N; i++) {
            if (!isPrime[i]) continue;
            for (int j = i*i; j <= N; j+=i) {   // j=i*2 최적화
                isPrime[j] = false;
            }
        }

        List<Integer> prime = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) prime.add(i);
        }
        

        // 소수의 연속합 길이 구하기
        int e = 0;
        int cnt = 0;
        for (int s = 0; s < prime.size(); s++) {
            int tmpSum = prime.get(s);
            e = s;
            while(e<prime.size()) {
                e++;
                if (e != prime.size()) tmpSum+= prime.get(e);
                if (tmpSum == N) {
                    cnt++;
                    break;
                } else if (tmpSum > N) break;
            }
        }
        System.out.println(cnt);











    }
}
