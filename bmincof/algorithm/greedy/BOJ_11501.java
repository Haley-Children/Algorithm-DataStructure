import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11501 {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int test = 0; test < t; test++) {
            int n = Integer.parseInt(br.readLine());
            int[] price = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                price[i] = Integer.parseInt(st.nextToken());
            }

            // 1. 주식의 값이 최대일 때 팔아야 최대 이득이 된다
            // 2. 언제 팔지 결정하기 위해 가장 마지막 날을 가격 비교 기준일로 정한다.
            // 3-1. 이전 날짜의 주식 가격이 기준일보다 싸면 이전 날짜의 주식을 산다.
            // 3-2. 이전 날짜의 주식 가격이 기준일보다 비싸면 가격 비교 기준일을 이전 날짜로 변경한다.
            long profit = 0;
            int sell = n - 1;

            // 마지막의 전날부터 가격 확인
            for (int i = n - 2; i >= 0; i--) {
                // 오늘보다 기준일이 더 비싸면 산다. (차익을 더한다)
                if (price[i] < price[sell]) {
                    profit += (price[sell] - price[i]);

                // 오늘이 기준일보다 더 비싸면 (기준일을 바꾼다)
                } else {
                    sell = i;
                }
            }

            System.out.println(profit);
        }
    }
}

