package OOP;

/**
 * @Author: Rita
 */
public class Overload {
    public void getSum(int i,int j){
        System.out.println("1");
    }

    public void getSum(double d1,double d2){
        System.out.println("2");
    }
    public void getSum(double d1,double d2, int i){
        System.out.println("2");
    }

    public void getSum(String s ,int i){
        System.out.println("3");
    }

    public void getSum(int i,String s){
        System.out.println("4");
    }
    public void show(String ... strs){
        System.out.println("show(String ... strs)");

        for(int i = 0;i < strs.length;i++){
            System.out.println(strs[i]);
        }
    }
    //can't be overload, because the same with upper
	/*public void show(String[] strs){

	}*/
}
