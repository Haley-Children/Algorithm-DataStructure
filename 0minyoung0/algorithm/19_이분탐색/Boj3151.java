import java.io.*;
import java.util.*;

public class Boj3151 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        // 학생의 수
        int n = Integer.parseInt(br.readLine());
        
        // 각 학생의 코딩 실력
        List<Integer> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
        	list.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(list);
        
        // 답을 저장할 변수
        long ans = 0;
        
        // i번째 학생이 선택한 가장 낮은 실력의 학생인 경우
        for (int i=0; i<n-2; i++) {
        	int target = -list.get(i);
        	int j = i+1;
        	int k = n-1;
        	while (j < k) {
        		// 찾은 경우
        		if (list.get(j) + list.get(k) == target) {
        			// 두 값이 같은 경우
        			if (list.get(j) - list.get(k) == 0) {
        				// (k-j+1)C2를 ans에 더해주고 break
        				ans += (k-j+1)*(k-j)/2;
        				break;
        			}
        			
        			// 두 값이 다른 경우
        			int temp1 = j;
        			while (list.get(j) - list.get(temp1) == 0) {
        				temp1++;
        			}
        			int temp2 = k;
        			while (list.get(temp2) - list.get(k) == 0) {
        				temp2--;
        			}
        			ans += (temp1 - j) * (k - temp2);
        			j = temp1;
        			k = temp2;
        		}
        		
        		// 값이 작은 경우
        		else if (list.get(j) + list.get(k) < target) {
        			j++;
        		}
        		
        		// 값이 큰 경우
        		else {
        			k--;
        		}
        	}
        }
        
        // 답 출력
        System.out.println(ans);
    }
}