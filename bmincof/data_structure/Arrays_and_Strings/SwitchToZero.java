// 0이 있는 행과 열의 값을 모두 0으로 변경
public class SwitchToZero {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 1, 1},
                {1, 0, 1, 1},
                {1, 1, 1, 0},
                {1, 0, 1, 1},
        };

        printImage(matrix);
        setZeroToAllZero(matrix);
        printImage(matrix);
    }
    // 메모리를 추가로 사용하고 싶지 않다고 가정
    private static void setZeroToAllZero(int[][] matrix) {
        int fc = -1;
        int fr = -1;

        for(int row = 0; row < matrix.length; row++) {
            for(int col = 0; col < matrix[row].length; col++) {
                if(matrix[row][col] == 0) {
                    if(fc == -1) {
                        fc = col;
                        fr = row;
                    }
                    // 처음 찾은 0이면 행과 열에 표시
                    matrix[fr][col] = 0;
                    matrix[row][fc] = 0;
                }
            }
        }
        // 0이 없으면 종료
        if(fc == -1) return;
        // 마크된 곳들을 변경
        for(int col = 0; col < matrix[0].length; col++) {
            if(matrix[fr][col] == 0 && col != fc)
                setColsToZero(col, matrix);
        }
        for(int row = 0; row < matrix.length; row++) {
            if(matrix[row][fc] == 0)
                setRowsToZero(row, matrix);
        }
        setColsToZero(fc, matrix);
    }

    private static void setColsToZero(int col, int[][] matrix) {
        for(int row = 0; row < matrix.length; row++)
            matrix[row][col] = 0;
    }
    private static void setRowsToZero(int row, int[][] matrix) {
        for(int col = 0; col < matrix.length; col++)
            matrix[row][col] = 0;
    }

    private static void printImage(int[][] image) {
        for(int i = 0; i < image.length; i++) {
            for(int j = 0; j < image[i].length; j++) {
                System.out.print(image[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
