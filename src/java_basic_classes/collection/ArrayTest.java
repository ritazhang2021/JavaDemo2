package java_basic_classes.collection;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: Rita
 */
public class ArrayTest {

    public void initialize(){
        //declare
        int[] array1;
        //1 static initialization
        array1 = new int[]{1,2,3,4};
        //2 Dynamic initialization: The initialization of the array is done separately from the assignment of the array elements
        String[] array2 = new String[5];
        //3 Type inference
        int[] array3 = {1,2,3,4,5};
    }
    public void reversal1(String [] arr){
        //方法一：
		for(int i = 0;i < arr.length / 2;i++){
			String temp = arr[i];
			arr[i] = arr[arr.length - i -1];
			arr[arr.length - i -1] = temp;
		}
    }
    public void reversal2(String [] arr){
        //方法二：
		for(int i = 0,j = arr.length - 1;i < j;i++,j--){
			String temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
    }
    @Test
    public void arrayApiTest(){

        //1.boolean Arrays.equals(int[] a,int[] b)
        int[] arr1 = new int[]{1,2,3,4};
        int[] arr2 = new int[]{1,8,7,4};
        boolean isEquals = Arrays.equals(arr1, arr2);
        System.out.println(isEquals);

        //2.String Arrays.toString(int[] a)
        System.out.println(Arrays.toString(arr1));

        //3.void Arrays.fill(int[] a,int val)
        Arrays.fill(arr1,0);
        System.out.println(Arrays.toString(arr1));

        //4.void Arrays.sort(int[] a)
        Arrays.sort(arr2);
        System.out.println(Arrays.toString(arr2));

        //5.int Arrays.binarySearch(int[] a,int key)
        int[] arr3 = new int[]{-33,-234,7,324,56,89,72,467,254,986};
        Arrays.sort(arr3);
        int index = Arrays.binarySearch(arr3, 72);
        if(index >= 0){
            System.out.println("found： index" + index);
        }else{
            System.out.println("not found");
        }
    }
}
