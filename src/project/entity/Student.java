package project.entity;

/**
 * @Author: Rita
 */
public class Student extends Person{
    private String school;

    public Student() {

    }

    public String getSchool() {
        return school;
    }

    public Student(String name, int age, String s) {
        super(name, age);
        school = s;
    }

    public Student(String name, String s) {
        super(name);
        school = s;
    }


    public Student(String s) {
        school = s;
    }
    //// If Student does not have a null parameter constructor, the superclass is called

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Student)) { //if(obj.getClass().equals(this.getClass())
            return false;
        }
        Student student = (Student) obj;
        //Compare the properties of all Student with those of its superclass
       return this.getName().equals(student.getName()) && this.getBirthDate().equals(student.getBirthDate());
    }


}