package generic.project;

import org.junit.Test;
import project.entity.Customer;
import project.entity.Student;

import java.util.List;

/**
 * @Author: Rita
 */
public class GenericCRUDTest {
    @Test
    public void test1(){
        CustomerDAO dao1 = new CustomerDAO();

        dao1.add(new Customer());
        List<Customer> list = dao1.getForList(10);


        StudentDAO dao2 = new StudentDAO();
        Student student = dao2.getIndex(1);
    }
}
