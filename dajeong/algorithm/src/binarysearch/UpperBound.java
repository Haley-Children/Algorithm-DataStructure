package binarysearch;

public class UpperBound {
    static int[] a;
    public static void main(String[] args) {
        a = new int[]{2,4,6,10,10,16,16,16,30,32};
        int len = a.length;
        int upperIdx = upperBound(16, len);
        System.out.println("upper idx: "+ upperIdx);
    }
    private static int upperBound(int target, int len) {
        int st = 0;
        int en = len;

        while (st < en) {
            int mid = (st + en) >>> 1;
            if (a[mid] > target) {
                en = mid;
            } else {
                st = mid+1;
            }
        }
        return en;
    }
}
