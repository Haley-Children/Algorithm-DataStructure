package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main_10773 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Deque<Integer> stack = new ArrayDeque<>();
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            int cmd = Integer.parseInt(br.readLine());

            if(cmd == 0) {
                stack.removeLast();
            } else {
                stack.addLast(cmd);
            }
        }

        int sum = 0;
        while(!stack.isEmpty()) sum += stack.removeLast();

        System.out.println(sum);
    }
}
