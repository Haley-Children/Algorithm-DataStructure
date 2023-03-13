import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1541 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력받은 수식
        String expression = br.readLine();

        // i번째 연산자가 -인지, 0번째는 +고정
        boolean[] isMinus = new boolean[60];
        // i번째 수
        int[] arr = new int[60];

        // 수식을 파싱하기 위한 인덱스
        int tail = 0;
        int head = 0;

        // isMinus에 들어간 boolean 수 = 연산자의 수
        int bCnt = 1;
        // 숫자의 개수
        int nCnt = 0;

        // 수식 파싱
        while(true) {
            if(tail == expression.length()) {
                arr[nCnt++] = Integer.parseInt(expression.substring(head));
                break;
            }
            if(expression.charAt(tail) == '+') {
                arr[nCnt++] = Integer.parseInt(expression.substring(head, tail++));
                head = tail;
                bCnt++;
                continue;
            }
            if(expression.charAt(tail) == '-') {
                arr[nCnt++] = Integer.parseInt(expression.substring(head, tail++));
                head = tail;
                isMinus[bCnt++] = true;
                continue;
            }
            tail++;
        }

        // 모든 -(  ) 안에 들어오는 수들을 최대로 만들면 수식의 결과가 최소
        // 다음 -를 만날 때 까지 수를 계속 더해나간다.
        // -를 만나면 현재까지 더한 수를 총합에 더하고 다시 새로운 합을 구한다
        // 수를 더할 때 시작이 -였다면 -를 곱해서 더한다

        // 합이 시작할 때 몇 번째 값인지
        int startPos = 0;
        // 수식의 결과
        int total = 0;
        // -를 만나기 전 까지의 합
        int sum = 0;
        for (int i = 0; i < nCnt; i++) {
            // 합의 시작이 -라면
            // 수식의 결과에 지금까지의 합을 더하고
            // 지금부터의 합이 -라는 표시 남기고 합을 0으로 초기화
            if(isMinus[i]) {
                total += sum * (isMinus[startPos] ? -1 : 1);
                startPos = i;
                sum = 0;
            }

            // 합을 더해나가기
            sum += arr[i];
        }

        // 마지막 ( ) 항의 합을 결과에 더하기
        total += sum * (isMinus[startPos] ? -1 : 1);

        System.out.println(total);
    }
}

