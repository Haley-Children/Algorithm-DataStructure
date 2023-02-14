package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class Time {
    int start;
    int end;

    public Time(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
public class Main_1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Time> meetings = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetings.add(new Time(start, end));
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            int cnt = 1;
            for (int j = i; j < n; j++) {
                if (meetings.get(i).end <= meetings.get(j).start) {
                    cnt++;
                }
            }
            max = Math.max(max, cnt);
        }


        // thenComparing?
        meetings.sort(new Comparator<Time>() {
            @Override
            public int compare(Time o1, Time o2) {
                return o1.start - o2.start;
            }
        });


    }
}
