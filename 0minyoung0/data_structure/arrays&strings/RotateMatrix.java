// 이미지 회전 알고리즘 - Rotate Matrix
// https://youtu.be/Z6QwmMQYZr8
// 이미지 정보가 NxN 배열에 담겨있다.
// 별도의 저장 공간을 사용하지 않고 90도 회전하는 알고리즘을 구현하시오.

package arraysAndStrings.rotateMatrix;

public class RotateMatrix {
	
	private static int[][] rotateImage(int[][] image){
		int tmp;
		for (int s=0, e=image.length-1; s < e; s++, e--) {
			for (int i=s, j=e; i<e; i++, j--) {
				tmp = image[s][i];
				image[s][i] = image[i][e];
				image[i][e] = image[e][j];
				image[e][j] = image[j][s];
				image[j][s] = tmp;
			}
		}
		return image;
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
	
	public static void main(String[] args) {
		int[][] image = {
				{1, 0, 0, 0, 1},
				{0, 1, 0, 1, 0},
				{0, 0, 1, 0, 0},
				{0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0}
		};
		printImage(image);
//		1 0 0 0 1 
//		0 1 0 1 0 
//		0 0 1 0 0 
//		0 0 0 0 0 
//		0 0 0 0 0 
		rotateImage(image);
		printImage(image);
//		1 0 0 0 0 
//		0 1 0 0 0 
//		0 0 1 0 0 
//		0 1 0 0 0 
//		1 0 0 0 0 
		rotateImage(image);
		printImage(image);
//		0 0 0 0 0 
//		0 0 0 0 0 
//		0 0 1 0 0 
//		0 1 0 1 0 
//		1 0 0 0 1 
		rotateImage(image);
		printImage(image);
//		0 0 0 0 1 
//		0 0 0 1 0 
//		0 0 1 0 0 
//		0 0 0 1 0 
//		0 0 0 0 1 
		rotateImage(image);
		printImage(image);
//		1 0 0 0 1 
//		0 1 0 1 0 
//		0 0 1 0 0 
//		0 0 0 0 0 
//		0 0 0 0 0 
	}
}
