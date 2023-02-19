package datastructure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main_10799 {
    // 1. k 번째 막대가 절단된 횟수는 k+1 번째 막대보다 같거나 많다.
    // 2. 괄호를 스택에 하나씩 추가, 각 막대가 잘린 횟수를 저장할 cut배열 추가
    // 3. 스택 사이즈가 1이상일 때 )를 만나면 )를 만났을 때까지 cut당한 횟수에 따라 아래 막대로 전달
    // 4. )를 만났을 때 cut당한 횟수가 0이면 (== cutter면) 아래에 += 1
    // 5. 그 외는 막대이므로 아래에 += 본인이 잘린 횟수
    // 6. 1층의 막대가 잘리면 전체 막대 개수에 누적
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        Deque<Character> stack = new LinkedList<>();
        int[] cut = new int[60000];
        int total = 0;
        for(int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            // 막대 층 쌓기
            if(c == '(') {
                stack.offerLast(c);
            } else {
                // cut을 표시하기 위해 막대 층수 저장
                int level = stack.size();
                // cut[level] == 0 <=> 절단기 이면 아래에 있는 막대의 절단 횟수 + 1
                if(cut[level] == 0) {
                    cut[level - 1] += 1;
                    // cut[level] != 0 <=> 막대이면 이전의 막대가 잘린 횟수만큼 이번 막대에 추가
                } else {
                    cut[level - 1] += cut[level];
                    // 맨 위의 막대의 끝에 도달했으므로 더 이상 잘릴 수 없다
                    // 잘린 횟수 + 1 도막 나므로 이 값을 전체 막대 수에 누적
                    total += cut[level] + 1;
                }
                // 막대의 끝을 만났으면 더 이상 없는 막대이므로 잘린 횟수를 0으로 초기화
                cut[level] = 0;
                // 끝을 만난 막대를 스택에서 빼낸다.
                stack.pollLast();
            }
        }

        System.out.println(total);
    }
}
