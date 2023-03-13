import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2457 {

    static int[] dayFromJan = {0,0,31,59,90,120,151,181,212,243,273,304,334};
    static int[] bloom = new int[400];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int sMonth = Integer.parseInt(st.nextToken());
            int sDay = Integer.parseInt(st.nextToken());
            int eMonth = Integer.parseInt(st.nextToken());
            int eDay = Integer.parseInt(st.nextToken());

            // 날짜를 일로 변환
            int start = dateToDay(sMonth, sDay);
            int end = dateToDay(eMonth, eDay);
            // 각 시작일마다 가장 꽃이 늦게 지는 날짜 선택
            bloom[start] = Math.max(bloom[start], end);
        }

        // 꽃의 개수
        int count = 0;
        // 현재까지 선택한 꽃들 중 가장 오래 펴있는 꽃
        int endOfPeriod = 0;
        int curDay = 0;

        boolean isPicked = false;
        while(true) {

            // 꽃이 펴있는 기간 중 또 다른 꽃이 가장 마지막에 지는 날짜 선택
            // 기간을 겹치면서 그 기간동안 새롭게 피는 꽃 중에서 가장 오래 피는 것만 선택하면 된다
            int longest = 0;
            for (int day = curDay; day <= endOfPeriod; day++) {
                // 꽃이 펴있는 기간 중 현재까지 선택한 꽃보다 더 오래 펴있는 꽃이 있으면 선택
                if(bloom[day] > endOfPeriod && bloom[day] > longest) {
                    longest = bloom[day];
                    isPicked = true;
                    curDay = day;
                }
            }

            endOfPeriod = longest;
            if(!isPicked || endOfPeriod > 274) break;

            count++;
            isPicked = false;
        }

        if(!isPicked) System.out.println(0);
        // 274일째 되는 꽃을 찾았을 떄 count를 하나 덜 셌으므로
        else System.out.println(count + 1);
    }

    // 날짜를 1월 1일부터의 일수로 변환한다.
    // 3월 1일을 0으로 설정하고 11월 31일(274일 째)를 마지막으로 한다.
    static int dateToDay(int month, int day) {
        int result = dayFromJan[month] + day - 60;
        if(result < 0) result = 0;
        else if(result > 274) result = 299;

        return result;
    }
}

