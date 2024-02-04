package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class boj_6198_옥상정원꾸미기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        long sum = 0;
        for (int i = N-1; i >= 0; i--) {
            int t = arr[i];
            while (!stack.isEmpty()) {
                if (t > stack.peek()) {
                    sum++;
                } else {
                    stack.pop();
                    break;
                }
            }
            stack.push(t);

        }
    }

}
