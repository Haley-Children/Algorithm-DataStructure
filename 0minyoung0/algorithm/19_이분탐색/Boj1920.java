import java.io.*;
import java.util.*;

public class Boj1920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 자연수의 개수
        int n = Integer.parseInt(br.readLine());
        
        // 자연수를 리스트에 저장 후 정렬
        ArrayList<Integer> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
        	list.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(list);
        
        // 존재 여부를 파악할 숫자의 개수
        int m = Integer.parseInt(br.readLine());
        
        // 존재 여부 파악하기
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<m; i++) {
        	if (binarySearch(Integer.parseInt(st.nextToken()), list, 0, n-1)) {
        		sb.append("1\n");
        	}else {
        		sb.append("0\n");
        	}
        }
        
        // 답 출력
        System.out.println(sb);
    }
    private static boolean binarySearch(int x, ArrayList<Integer> list, int s, int e) {
    	if (s > e) return false;
    	int mid = (s + e) / 2;
    	if (x == list.get(mid)) return true;
    	if (x < list.get(mid)) return binarySearch(x, list, s, mid-1);
    	else return binarySearch(x, list, mid+1, e);
    }
}