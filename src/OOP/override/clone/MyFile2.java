package OOP.override.clone;

/**
 * @Author: Rita
 * @Date:8/17/2021 5:48 PM
 */
public class MyFile2 implements Cloneable {

    private String path;

    private Info info;



    public MyFile2(String path, Info info) {

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

        MyFile2 temp = (MyFile2) obj;

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

        MyFile2 file = (MyFile2) super.clone();

        file.info = (Info) file.info.clone();



        return file;

    }



    public static void main(String[] args) throws CloneNotSupportedException {

        Info info = new Info(2, "Hello world.");

        MyFile2 file1 = new MyFile2("c:", info);

        MyFile2 file2 = (MyFile2) file1.clone();



        System.out.println(file1.getClass() == file2.getClass());//true

        System.out.println(file1 == file2);//false

        System.out.println(file1.equals(file2));//true

        System.out.println(file1.info.getClass() == file2.info.getClass());//true

        System.out.println(file1.info == file2.info);//false

        System.out.println(file1.info.equals(file2.info));//true

    }

}
