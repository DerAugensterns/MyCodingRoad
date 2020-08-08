package cn.adiong.container;

import java.util.Arrays;

public class TestBinarySearch {
    public static void main(String[] args) {
        int[] arrs = {100, 400, 50, 897, 7, 394, 76, 69, 8};
        Arrays.sort(arrs);//排序后才能使用二分法查找
        System.out.println(Arrays.toString(arrs));
        System.out.println(select(arrs, 400));
    }

    public static int select(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {

            int mid = (low + high) / 2;

            if (arr[mid] == value) {
                return mid;
            } else if (value > arr[mid]) {
                low = mid + 1;
            } else if (value < arr[mid]) {
                high = mid - 1;
            }
        }
        return -1;
    }
}
