package OOP.override;

/**
 * @Author: Rita
 */
public class OverrideObjectMethod {
    public class Student {
        private String name;
        private String sex;
        private String age;
        private float weight;
        private String addr;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public float getWeight() {
            return weight;
        }

        public void setWeight(float weight) {
            this.weight = weight;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }
        // 重写hashcode方法
        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 17 * result + sex.hashCode();
            result = 17 * result + age.hashCode();
            return result;
        }

        // 重写equals方法
        @Override
        public boolean equals(Object obj) {
            // 地址相等
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Student stuObj = (Student) obj;
            return stuObj.name.equals(this.name) && stuObj.sex.equals(this.sex) && stuObj.age.equals(this.age);
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", sex='" + sex + '\'' +
                    ", age='" + age + '\'' +
                    ", weight=" + weight +
                    ", addr='" + addr + '\'' +
                    '}';
        }
    }

}
