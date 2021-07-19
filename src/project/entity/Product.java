package project.entity;

/**
 * @Author: Rita
 */
public class Product implements Comparable{

    private String name;
    private double price;

    public Product() {
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    //指明商品比较大小的方式:按照价格从低到高排序,再按照产品名称从高到低排序
    @Override
    public int compareTo(Object o) {
//        System.out.println("**************");
        if(o instanceof Product){
            Product product = (Product)o;
            //方式一：
            if(this.price > product.price){
                return 1;
            }else if(this.price < product.price){
                return -1;
            }else{
//                return 0;
                return -this.name.compareTo(product.name);
            }
            //方式二：
//           return Double.compare(this.price,product.price);
        }
//        return 0;
        throw new RuntimeException("传入的数据类型不一致！");
    }

}
