package algorithm;

/**
 * @Author: Rita
 */
public class Search {


    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while(left <= right) { //说明继续查找
            int mid = (left + right) / 2;
            if(arr[mid] == target) {
                return mid;
            } else if ( arr[mid] > target) {
                right = mid - 1;//需要向左边查找
            } else {
                left = mid + 1; //需要向右边查找
            }
        }
        return -1;
    }

    public static int findSecondBiggestInt(int[] arr){
        if(arr.length == 0 || arr == null){
            return Integer.MIN_VALUE;
        }
        if(arr.length == 1){
            return arr[0];
        }
        int biggestInt = Integer.MIN_VALUE;
        int secondBiggestInt = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > biggestInt){
                secondBiggestInt = biggestInt;
                biggestInt = arr[i];
            }
        }
        return secondBiggestInt;
    }
}
