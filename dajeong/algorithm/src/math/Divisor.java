package math;

import java.util.LinkedList;

// 약수 구하기
public class Divisor {

    static LinkedList<Integer> div = new LinkedList<>();

    public static void main(String[] args) {
        int n = 18; // n의 약수 구하기
        findDiv(n);
    }

    private static void findDiv(int n) {
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                div.add(i);
            }
        }

        for (int i = div.size() - 1; i >= 0; i--) { // 약수를 크기 순으로 저장하게끔 하기 위해 뒤에서부터 시작
            if (div.get(i) * div.get(i) == n) { // 제곱수의 약수는 한번만 처리할 것
                continue;
            }
            div.add(n / div.get(i));
        }

        for (int i = 0; i < div.size(); i++) {
            System.out.println(div.get(i));
        }
    }

}
