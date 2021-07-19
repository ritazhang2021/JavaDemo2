package java8;

import project.entity.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Rita
 *提供用于测试的数据
 */
public class EmployeeData {
    public static List<Employee> getEmployees(){
        List<Employee> list = new ArrayList<>();

        list.add(new Employee(1001, "李飞", 34, 2353.33));
        list.add(new Employee(1002, "菁英", 35, 9324.99));
        list.add(new Employee(1003, "何塞努", 35, 2353.22));
        list.add(new Employee(1004, "旷离", 23, 3465.22));
        list.add(new Employee(1005, "酷乐", 53, 4574.456));
        list.add(new Employee(1006, "韩丽", 34, 4724.5756));
        list.add(new Employee(1007, "刘淑兰", 45, 7896.35));
        list.add(new Employee(1008, "马斯特", 56, 6886.546));
        list.add(new Employee(1008, "卡兹洛加", 56, 3131.546));
        list.add(new Employee(1008, "优越", 56, 1111.546));
        list.add(new Employee(1008, "水陆", 56, 5467.546));
        list.add(new Employee(1008, "桂黔", 56, 6342.546));
        list.add(new Employee(1008, "游本昌", 56, 8789.546));

        return list;
    }

}
