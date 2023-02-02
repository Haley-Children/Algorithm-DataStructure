// 재귀.boj2448;

import java.io.*;

public class Boj2448 {
    static char[][] t;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        t = new char[n][2*n];
        // *로 채우기
        for (int i=0; i<n; i++) {
        	for (int j=0; j<2*i+1; j++) {
        		t[i][j] = '*';
        	}
        }
        // 빈칸 뚫기
        makeBlank(n, 0, 0);
        // 출력하기
        for (int i=0; i<n; i++) {
        	// 왼쪽 빈 영역
        	for (int j=0; j<n-1-i; j++) {
        		sb.append(" ");
        	}
        	// 삼각형 영역
        	for (int j=0; j<2*i+1; j++) {
        		sb.append(t[i][j]);
        	}
        	// 오른쪽 빈 영역
        	for (int j=0; j<n-1-i; j++) {
        		sb.append(" ");
        	}
        	// 줄 띄기
        	sb.append("\n");
        }
        System.out.println(sb);
    }
    private static void makeBlank(int k, int x, int y) {
    	if (k==3) {
    		t[x+1][y+1] = ' ';
    		return;
    	}
    	// 빈칸 뚫기
    	for (int i=k/2; i<k; i++) {
    		for (int j=2*i-k+1; j<k; j++) {
    			t[x+i][y+j] = ' ';
    		}
    	}
    	// 재귀 호출
    	makeBlank(k/2, x, y);
    	makeBlank(k/2, x+k/2, y);
    	makeBlank(k/2, x+k/2, y+k);
    }
}


//*                        
//* *                       
//*****                      
//*     *                     
//* *   * *                    
//***** *****                   
//*           *                  
//* *         * *                 
//*****       *****                
//*     *     *     *               
//* *   * *   * *   * *              
//***** ***** ***** *****             
//*                       *            
//* *                     * *           
//*****                   *****          
//*     *                 *     *         
//* *   * *               * *   * *        
//***** *****             ***** *****       
//*           *           *           *      
//* *         * *         * *         * *     
//*****       *****       *****       *****    
//*     *     *     *     *     *     *     *   
//* *   * *   * *   * *   * *   * *   * *   * *  
//***** ***** ***** ***** ***** ***** ***** *****