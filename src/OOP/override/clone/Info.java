package OOP.override.clone;

/**
 * @Author: Rita
 * @Date:8/17/2021 5:28 PM
 */
public class Info implements Cloneable {

    private int id;

    private String text;



    public Info(int id, String text) {

        this.id = id;

        this.text = text;

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

        Info temp = (Info) obj;

        if (id != temp.id) {

            return false;

        }

        if (text == null) {

            if (temp.text != null) {

                return false;

            }

        } else if (!text.equals(temp.text)) {

            return false;

        }

        return true;

    }



    @Override

    protected Object clone() throws CloneNotSupportedException {

        return super.clone();

    }



    public static void main(String[] args) throws CloneNotSupportedException {

        Info info1 = new Info(1, "I am Colyn Lu.");

        Info info2 = (Info) info1.clone();



        System.out.println(info1.getClass() == info2.getClass());//true

        System.out.println(info1 == info2);//false

        System.out.println(info1.equals(info2));//true

    }

}
