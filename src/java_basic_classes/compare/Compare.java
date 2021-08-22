package java_basic_classes.compare;

import org.junit.Test;
import project.entity.Product;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Rita
 */
public class Compare {
    /*
        Comparable接口的使用举例：  自然排序
        1.像String、包装类等实现了Comparable接口，重写了compareTo(obj)方法，给出了比较两个对象大小的方式。
        2.像String、包装类重写compareTo()方法以后，进行了从小到大的排列
        3. 重写compareTo(obj)的规则：
            如果当前对象this大于形参对象obj，则返回正整数，
            如果当前对象this小于形参对象obj，则返回负整数，
            如果当前对象this等于形参对象obj，则返回零。
        4. 对于自定义类来说，如果需要排序，我们可以让自定义类实现Comparable接口，重写compareTo(obj)方法。
           在compareTo(obj)方法中指明如何排序
         */
    @Test
    public void test1(){
        String[] arr = new String[]{"AA","CC","KK","MM","GG","JJ","DD"};
        //
        Arrays.sort(arr);

        System.out.println(Arrays.toString(arr));

    }

    @Test
    public void test2(){
        Product[] arr = new Product[5];
        arr[0] = new Product("lenovoMouse",34);
        arr[1] = new Product("dellMouse",43);
        arr[2] = new Product("xiaomiMouse",12);
        arr[3] = new Product("huaweiMouse",65);
        arr[4] = new Product("microsoftMouse",43);

        Arrays.sort(arr);

        System.out.println(Arrays.toString(arr));
    }

    /*
    Comparator接口的使用：定制排序
    1.背景：
    当元素的类型没有实现java.lang.Comparable接口而又不方便修改代码，
    或者实现了java.lang.Comparable接口的排序规则不适合当前的操作，
    那么可以考虑使用 Comparator 的对象来排序
    2.重写compare(Object o1,Object o2)方法，比较o1和o2的大小：
    如果方法返回正整数，则表示o1大于o2；
    如果返回0，表示相等；
    返回负整数，表示o1小于o2。

     */
    @Test
    public void test3(){
        String[] arr = new String[]{"AA","CC","KK","MM","GG","JJ","DD"};
        //需要去重的话用set，里面加一个comparator
        Arrays.sort(arr,new java.util.Comparator(){

            //按照字符串从大到小的顺序排列
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof String && o2 instanceof  String){
                    String s1 = (String) o1;
                    String s2 = (String) o2;
                    return -s1.compareTo(s2);
                }
//                return 0;
                throw new RuntimeException("输入的数据类型不一致");
            }
        });
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test4(){
        Product[] arr = new Product[6];
        arr[0] = new Product("lenovoMouse",34);
        arr[1] = new Product("dellMouse",43);
        arr[2] = new Product("xiaomiMouse",12);
        arr[3] = new Product("huaweiMouse",65);
        arr[4] = new Product("huaweiMouse",224);
        arr[5] = new Product("microsoftMouse",43);


        Arrays.sort(arr, new java.util.Comparator() {
            //指明商品比较大小的方式:按照产品名称从低到高排序,再按照价格从高到低排序
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Product && o2 instanceof Product){
                    Product g1 = (Product)o1;
                    Product g2 = (Product)o2;
                    if(g1.getName().equals(g2.getName())){
                        return -Double.compare(g1.getPrice(),g2.getPrice());
                    }else{
                        return g1.getName().compareTo(g2.getName());
                    }
                }
                throw new RuntimeException("输入的数据类型不一致");
            }
        });

        System.out.println(Arrays.toString(arr));

        List<Product> products = Arrays.asList(arr);
    }

}


