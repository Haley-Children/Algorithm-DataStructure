import java.io.*;
import java.util.*;

public class Boj2295 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<n; i++) {
        	list.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(list);
        
        // 두 숫자를 합한 결과를 리스트로 만들기
        List<Integer> list2 = new ArrayList<>();
        for (int i=0; i<n; i++) {
        	for (int j=0; j<n; j++) {
        		list2.add(list.get(i) + list.get(j));
        	}
        }
        Collections.sort(list2);
        
        // 가능한 가장 큰 숫자 찾기
        for (int i=n-1; i>=0; i--) {
        	for (int j=0; j<i; j++) {
        		int x = list.get(i) - list.get(j);
        		int s = 0;
        		int e = list2.size() - 1;
        		while (s <= e) {
        			int mid = (s+e)/2;
        			if (list2.get(mid) == x) {
        				System.out.println(list.get(i));
        				return;
        			}
        			if (list2.get(mid) < x) s = mid + 1;
        			else e = mid - 1;
        		}
        	}
        }
    }
}