// 시뮬레이션.boj13335;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj13335 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int[] tWeight = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
        	tWeight[i] = Integer.parseInt(st.nextToken());
        }
        int[] onBridge = new int[1000001]; // index 시간에 다리 위 트럭의 총 무게
        int waitingTruck = 0;
        int time = 0;
        for (int i=1; i<=1000000; i++) {
        	if (onBridge[i] + tWeight[waitingTruck] <= l) {
        		for (int j=0; j<w; j++) {
        			onBridge[i+j] += tWeight[waitingTruck];
        		}
        		waitingTruck++;
        	}
        	if (waitingTruck == n) {
        		time = i + w;
        		break;
        	}
        }
        System.out.println(time);
    }
}