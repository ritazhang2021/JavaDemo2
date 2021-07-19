package java_basic_classes.compare;

/**
 * @Author: Rita
 */
public class Comparator{
    boolean compare(int a, int b){
        if(a==b){
            return true;
        }
        return false;
    }
    boolean compare(String a, String b){
        if(a.compareTo(b)==0){
            return true;
        }
        return false;
    }
    boolean compare(int[] a, int[] b){
        if(a.length == b.length){
            for (int i = 0; i < a.length; i++) {
                if(a[i] != b[i]){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}