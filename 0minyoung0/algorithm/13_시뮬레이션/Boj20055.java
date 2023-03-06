import java.io.*;
import java.util.*;

public class Boj20055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 컨베이어 벨트 길이
        int n = Integer.parseInt(st.nextToken());
        // 내구도 0인 칸이 k개 이상이 되면 종료
        int k = Integer.parseInt(st.nextToken());
        
        // 벨트의 내구도
        int[] belt = new int[2*n];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<2*n; i++) {
        	belt[i] = Integer.parseInt(st.nextToken());
        }
        
        // 로봇의 위치
        boolean[] robot = new boolean[n];
        
    	// 턴을 나타낼 변수
        int t = 1;
        
        // 내구도가 0이 된 벨트의 개수를 셀 변수
        int zeroCnt = 0;
        
        while (true) {
        	// 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
        	// 벨트 돌리기, 모든 로봇 위치 +1
        	int temp = belt[2*n-1];
        	for (int i=2*n-2; i>=0; i--) {
        		belt[i+1] = belt[i];
        	}
        	belt[0] = temp;
        	for (int i=n-2; i>=0; i--) {
        		robot[i+1] = robot[i];
        	}
        	robot[0] = false;
        	// 내리기 체크
        	robot[n-1] = false;
        	
        	// 2. 가장 먼저 벨트에 올라간 로봇부터, 한 칸 이동할 수 있다면 이동한다.
        	for (int i=n-2; i>=1; i--) {
        		// 로봇이 존재하고, 앞에 로봇이 없고, 내구도가 있어 이동 가능하다면
        		if (robot[i] && !robot[i+1] && belt[i+1] > 0) {
        			// 로봇 이동
        			robot[i+1] = robot[i];
        			robot[i] = false;
        			// 내구도 감소 후 체크
        			if (--belt[i+1] == 0) {
        				zeroCnt++;
        			}
        		}
        	}
        	// 내리기 체크
        	robot[n-1] = false;
        	
        	// 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
        	if (belt[0] != 0) {
        		// 올리기
        		robot[0] = true;
    			// 내구도 감소 후 체크
    			if (--belt[0] == 0) {
    				zeroCnt++;
    			}
        	}
        	
        	// 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다.
        	if (zeroCnt >= k) {
        		System.out.println(t);
        		return;
        	}
        	// 그렇지 않다면 1번으로 돌아간다.
        	t++;
        }
    }
}