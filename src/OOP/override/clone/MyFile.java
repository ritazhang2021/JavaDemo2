package OOP.override.clone;

/**
 * @Author: Rita
 * @Date:8/17/2021 5:46 PM
 */
public class MyFile implements Cloneable {

    private String path;

    private Info info;



    public MyFile(String path, Info info) {

        this.path = path;

        this.info = info;

    }



    @Override

    public boolean equals(Object obj) {

        if (this == obj) {

            return true;

        }

        if (obj == null) {

            return false;

        }

        if (obj.getClass()!= getClass()) {

            return false;

        }

        MyFile temp = (MyFile) obj;

        if (path == null) {

            if (temp.path != null) {

                return false;

            }

        } else if (!path.equals(temp.path)) {

            return false;

        }

        if (info == null) {

            if (temp.info != null) {

                return false;

            }

        } else if (!info.equals(temp.info)) {

            return false;

        }

        return true;

    }



    @Override

    protected Object clone() throws CloneNotSupportedException {

        return super.clone();

    }



    public static void main(String[] args) throws CloneNotSupportedException {

        Info info = new Info(2, "Hello world.");

        MyFile file1 = new MyFile("c:", info);

        MyFile file2 = (MyFile) file1.clone();



        System.out.println(file1.getClass() == file2.getClass());//true

        System.out.println(file1 == file2);//false

        System.out.println(file1.equals(file2));//true

        System.out.println(file1.info.getClass() == file2.info.getClass());//true

        System.out.println(file1.info == file2.info);//true

        System.out.println(file1.info.equals(file2.info));//true

    }

}
