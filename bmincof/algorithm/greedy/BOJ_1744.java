import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_1744 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // 1. 음수와 0
        // 음수는 가장 낮은 것끼리 곱해야 최대
        // 큰 음수들 부터 곱하고 가장 작은 음수와 0을 곱해야 함
        // 2. 1
        // 1과 다른 양수는 곱하는 것보다 각각 더하는 것이 가장 최대
        // 3. 나머지 양수
        // 가장 큰 값부터 곱하기

        // 음수와 0 을 담을 배열 선언
        // 1을 제외한 양수를 담을 배열 선언
        List<Integer> minus = new ArrayList<>();
        List<Integer> plus = new ArrayList<>();

        // 수열로 만든 최댓값
        int result = 0;
        // 각 숫자에 맞는 배열에 저장
        // 1은 바로 최종합에 더하기
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num <= 0) minus.add(num);
            else if(num == 1) result += num;
            else plus.add(num);
        }

        // 조건에 맞게 계산하기 위해 정렬
        Collections.sort(minus);
        Collections.sort(plus, Collections.reverseOrder());

        int mCnt = minus.size();
        int pCnt = plus.size();

        // minus가 2개 이상일 때
        if(mCnt >= 2) {
            // minus가 홀수개면 가장 큰 값은 더하는게 최선
            if(mCnt % 2 == 1) {
                result += minus.get(mCnt-1);
                mCnt--;
            }
            for (int i = 0; i < mCnt; i += 2) {
                result += minus.get(i) * minus.get(i + 1);
            }
        } else if(mCnt == 1){
            result += minus.get(0);
        }

        // plus가 홀수개면 가장 작은 값은 더하는게 최선
        if(pCnt >= 2) {
            if (pCnt % 2 == 1) {
                result += plus.get(pCnt - 1);
                pCnt--;

                for (int i = 0; i < pCnt; i += 2) {
                    result += plus.get(i) * plus.get(i + 1);
                }
            } else {
                for (int i = 0; i < pCnt; i += 2) {
                    result += plus.get(i) * plus.get(i + 1);
                }
            }
        } else if(pCnt == 1) {
            result += plus.get(0);
        }

        System.out.println(result);
    }
}

