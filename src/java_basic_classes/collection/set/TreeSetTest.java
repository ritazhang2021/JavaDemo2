package java_basic_classes.collection.set;

import org.junit.Test;
import project.entity.Employee;
import project.entity.MyDate;
import project.entity.Person;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @Author: Rita
 */
public class TreeSetTest {
    /*
   1.向TreeSet中添加的数据，要求是相同类的对象。
   2.两种排序方式：自然排序（实现Comparable接口） 和 定制排序（Comparator）
   3.自然排序中，比较两个对象是否相同的标准为：compareTo()返回0.不再是equals().
   4.定制排序中，比较两个对象是否相同的标准为：compare()返回0.不再是equals().
    */
    @Test
    public void test1(){
        TreeSet set = new TreeSet();
        //失败：不能添加不同类的对象
//        set.add(123);
//        set.add(456);
//        set.add("AA");
//        set.add(new Person("Tom",12));
        //举例一：
//        set.add(34);
//        set.add(-34);
//        set.add(43);
//        set.add(11);
//        set.add(8);

        //举例二：
        set.add(new Person("Tom",12));
        set.add(new Person("Jerry",32));
        set.add(new Person("Jim",2));
        set.add(new Person("Mike",65));
        set.add(new Person("Jack",33));
        set.add(new Person("Jack",56));


        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

    @Test
    public void test2(){
        Comparator com = new Comparator() {
            //按照年龄从小到大排列
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Person && o2 instanceof Person){
                    Person u1 = (Person)o1;
                    Person u2 = (Person)o2;
                    return Integer.compare(u1.getAge(),u2.getAge());
                }else{
                    throw new RuntimeException("输入的数据类型不匹配");
                }
            }
        };
        //如果需要去重，还要重写equal方法

        TreeSet set = new TreeSet(com);
        set.add(new Person("Tom",12));
        set.add(new Person("Jerry",32));
        set.add(new Person("Jim",2));
        set.add(new Person("Mike",65));
        set.add(new Person("Mary",33));
        set.add(new Person("Jack",33));
        set.add(new Person("Jack",56));


        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    /**
     *  * 创建该类的 5 个对象，并把这些对象放入 TreeSet 集合中（下一章：TreeSet 需使用泛型来定义）
     *  分别按以下两种方式对集合中的元素进行排序，并遍历输出：
     *
     *  1). 使Employee 实现 Comparable 接口，并按 name 排序
     *  2). 创建 TreeSet 时传入 Comparator对象，按生日日期的先后排序。*/

    //问题二：按生日日期的先后排序。
    @Test
    public void test22(){

        TreeSet<Employee> set = new TreeSet<>(new Comparator<Employee>() {
            //使用泛型以后的写法
            @Override
            public int compare(Employee o1, Employee o2) {
                MyDate b1 = o1.getBirthday();
                MyDate b2 = o2.getBirthday();
                return b1.compareTo(b2);
            }
            //使用泛型之前的写法
            //@Override
//            public int compare(Object o1, Object o2) {
//                if(o1 instanceof Employee && o2 instanceof Employee){
//                    Employee e1 = (Employee)o1;
//                    Employee e2 = (Employee)o2;
//
//                    MyDate b1 = e1.getBirthday();
//                    MyDate b2 = e2.getBirthday();
//                    //方式一：
////                    //比较年
////                    int minusYear = b1.getYear() - b2.getYear();
////                    if(minusYear != 0){
////                        return minusYear;
////                    }
////                    //比较月
////                    int minusMonth = b1.getMonth() - b2.getMonth();
////                    if(minusMonth != 0){
////                        return minusMonth;
////                    }
////                    //比较日
////                    return b1.getDay() - b2.getDay();
//
//                    //方式二：
//                    return b1.compareTo(b2);
//
//                }
////                return 0;
//                throw new RuntimeException("传入的数据类型不一致！");
//            }
        });

        Employee e1 = new Employee("liudehua",55,new MyDate(1965,5,4));
        Employee e2 = new Employee("zhangxueyou",43,new MyDate(1987,5,4));
        Employee e3 = new Employee("guofucheng",44,new MyDate(1987,5,9));
        Employee e4 = new Employee("liming",51,new MyDate(1954,8,12));
        Employee e5 = new Employee("liangzhaowei",21,new MyDate(1978,12,4));

        set.add(e1);
        set.add(e2);
        set.add(e3);
        set.add(e4);
        set.add(e5);

        Iterator<Employee> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }


    //问题一：使用自然排序
    @Test
    public void test11(){
        TreeSet<Employee> set = new TreeSet<Employee>();

        Employee e1 = new Employee("liudehua",55,new MyDate(1965,5,4));
        Employee e2 = new Employee("zhangxueyou",43,new MyDate(1987,5,4));
        Employee e3 = new Employee("guofucheng",44,new MyDate(1987,5,9));
        Employee e4 = new Employee("liming",51,new MyDate(1954,8,12));
        Employee e5 = new Employee("liangzhaowei",21,new MyDate(1978,12,4));

        set.add(e1);
        set.add(e2);
        set.add(e3);
        set.add(e4);
        set.add(e5);

        Iterator<Employee> iterator = set.iterator();
        while (iterator.hasNext()){
            Employee employee = iterator.next();
            System.out.println(employee);
        }
    }

}
