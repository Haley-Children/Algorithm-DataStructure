// 이차원배열에서 0의 행과 열을 모두 0으로 바꾸기
// https://youtu.be/Ww832sQH0c0

package arraysAndStrings.setZeroToAllZero;

public class SetZeroToAllZero {
	public static void main(String[] args) {
		int[][] matrix = {
			{1, 1, 1, 1},
			{1, 0, 1, 1},
			{1, 1, 1, 0},
			{1, 0, 1, 1}
		};
		printImage(matrix);
//		1 1 1 1 
//		1 0 1 1 
//		1 1 1 0 
//		1 0 1 1 
		
		setZeroToAllZero(matrix);
		printImage(matrix);
//		1 0 1 0 
//		0 0 0 0 
//		0 0 0 0 
//		0 0 0 0 
	}
	private static void setZeroToAllZero(int[][] matrix) {
		int fc = -1;
		int fr = -1;
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				if (matrix[row][col] == 0) {
					// 만약 처음으로 찾은 0이라면 fc, fr에 좌표 저장
					if (fc == -1) {
						fc = col;
						fr = row;
					}
					// 첫번째 0과 같은 행이나 열 정보만 0으로 바꾸어서 기록하기
					matrix[fr][col] = 0;
					matrix[row][fc] = 0;
				}
			}
		}
		// 0이 없는 경우 바로 리턴
		if (fc == -1) return;
		// 첫번째  0과 같은 열, 행에서 0을 찾아서 해당 데이터를 기준으로 각각 열, 행을 0으로 바꾸기
		for (int col = 0; col < matrix[0].length; col++) {
			if (matrix[fr][col] == 0 && col != fc) {
				setColsToZero(col, matrix);
			}
		}
		for (int row = 0; row < matrix.length; row++) {
			if (matrix[row][fc] == 0) {
				setRowsToZero(row, matrix);
			}
		}
		setColsToZero(fc, matrix);
	}
	private static void setColsToZero(int col, int[][] matrix) {
		for (int row = 0; row < matrix.length; row++) {
			matrix[row][col] = 0;
		}
	}
	private static void setRowsToZero(int row, int[][] matrix) {
		for (int col = 0; col < matrix[row].length; col++) {
			matrix[row][col] = 0;
		}
	}
	private static void printImage(int[][] image) {
		for (int i=0; i<image.length; i++) {
			for (int j=0; j<image[i].length; j++) {
				System.out.print(image[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
