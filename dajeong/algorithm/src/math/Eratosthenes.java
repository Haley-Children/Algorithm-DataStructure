package math;

import java.util.ArrayList;

// 에라토스테네스의 체
public class Eratosthenes {

    static boolean[] state;
    static ArrayList<Integer> primes; // 소수 리스트

    public static void main(String[] args) {
        ArrayList<Integer> prime1 = run1(50);
        System.out.println("**** 에라토스테네스체 결과1");
        for (int i = 0; i < prime1.size(); i++) {
            System.out.print(prime1.get(i) + " ");
        }
        System.out.println();

        ArrayList<Integer> prime2 = run2(50);
        System.out.println("**** 에라토스테네스체 최적화 버전)) 결과2");
        for (int i = 0; i < prime2.size(); i++) {
            System.out.print(prime2.get(i) + " ");
        }
    }

    // 최적화 버전 O(Nlog(logN))
    // N = 10,000,000일 때 0.06초, N = 50,000,000일 때 대략 0.5초 안에 계산 가능
    // 현재 바라보고 있는 소인수 제곱 이하의 수는 더 작은 소인수로 나누어떨어지므로 i*i부터 탐색하면 된다.
    private static ArrayList<Integer> run2(int n) {
        primes = new ArrayList<>();
        state = new boolean[n + 1];
        for (int i = 2; i * i <= n; i++) {
            if (state[i]) {
                continue;
            }
            for (int j = i * i; j <= n; j += i) {
                state[j] = true;
            }
        }
        for (int i = 2; i <= n; i++) {
            if (!state[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    private static ArrayList<Integer> run1(int n) {
        primes = new ArrayList<>();
        state = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            if (state[i]) {
                continue;
            }
            for (int j = 2 * i; j <= n; j += i) {
                state[j] = true;
            }
        }
        for (int i = 2; i <= n; i++) {
            if (!state[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

}
