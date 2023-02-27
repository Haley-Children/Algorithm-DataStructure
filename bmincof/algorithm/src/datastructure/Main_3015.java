package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main_3015 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Deque<Node> stack = new ArrayDeque<>();
        int n = Integer.parseInt(br.readLine());

        // 마주보는 쌍의 수
        // 모두 같은 키이면 쌍이 매우 많아진다
        long pairs = 0;

        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(br.readLine());
            // 매번 새로운 사람이 들어올 때 마다
            // 자신보다 작은 사람들의 수를 모두 더하면서 pop 하기
            while(!stack.isEmpty() && stack.peek().height < height) {
                pairs += stack.peek().overlap;
                stack.pop();
            }

            // 바로 앞에 남아있는 사람이 있을 때
            // 앞사람과 키가 같으면 pairs + 앞에 있는 같은 키의 사람 수 + 자신보다 키가 더 큰 사람이 있으면 1명 추가
            if(!stack.isEmpty() && stack.peek().height == height) {
                pairs += (stack.peek().overlap++) + (stack.size() > 1 ? 1 : 0);
            // 바로 앞 사람보다 작으면 pairs + 1 하고 push
            } else if(!stack.isEmpty() && stack.peek().height > height) {
                pairs += 1;
                stack.push(new Node(height));
            // 자신보다 작은 값들은 모두 빼냈으므로 스택이 비어있을 때
            } else {
                stack.push(new Node(height));
            }
        }

        System.out.println(pairs);
    }

    static class Node {
        int height;
        int overlap;
        Node(int height) {
            this.height = height;
            this.overlap = 1;
        }
    }
}
