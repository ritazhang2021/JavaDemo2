package design_patterns;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Rita
 * @Date:5/26/2021 10:50 PM
 */
public class CompositePattern {
    public static void main(String[] args) {
        Employee2 CEO = new Employee2("John","CEO");

        Employee2 headSales = new Employee2("Rob","Sales");

        Employee2 headMarketing = new Employee2("Mike","Marketing");

        Employee2 programmer1 = new Employee2("Lili","Programmer");
        Employee2 programmer2 = new Employee2("Bob","Programmer");

        Employee2 tester1 = new Employee2("Jack","Tester");
        Employee2 tester2 = new Employee2("Tom","Tester");

        CEO.add(headSales);
        CEO.add(headMarketing);

        headSales.add(tester1);
        headSales.add(tester2);

        headMarketing.add(programmer1);
        headMarketing.add(programmer2);

        //print all employees of the organization
        System.out.println(CEO);
        for (Employee2 headEmployee : CEO.getSubordinates()) {
            System.out.println(headEmployee);
            for (Employee2 employee : headEmployee.getSubordinates()) {
                System.out.println(employee);
            }
        }
    }
}

class Employee2 {
    private String name;
    private String title;
    private List<Employee2> subordinates;

    public Employee2(String name,String title) {
        this.name = name;
        this.title = title;
        subordinates = new ArrayList<Employee2>();
    }

    public void add(Employee2 e) {
        subordinates.add(e);
    }

    public void remove(Employee2 e) {
        subordinates.remove(e);
    }

    public List<Employee2> getSubordinates(){
        return subordinates;
    }

    @Override
    public String toString(){
        return "Employee :[ Name : "+ name
                +", dept : "+ title +subordinates +" ]";
    }
}
