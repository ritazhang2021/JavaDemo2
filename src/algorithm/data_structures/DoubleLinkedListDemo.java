package algorithm.data_structures;

/**
 * @Author: Rita
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        // 测试
        System.out.println("双向链表的测试");
        // 先创建节点
        Head hero1 = new Head(1, "孙悟空", "猴哥");
        Head hero2 = new Head(2, "猪八戒", "八戒");
        Head hero3 = new Head(3, "沙僧", "沙师弟");
        Head hero4 = new Head(4, "唐僧", "师傅");
        // 创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        
        doubleLinkedList.list();
        // 修改
        Head newHead = new Head(4, "红孩儿", "红孩儿");
        doubleLinkedList.update(newHead);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();
        // 删除
        doubleLinkedList.del(3);
        System.out.println("删除后的链表情况~~");
        doubleLinkedList.list();
    }
}


class DoubleLinkedList {
    private Head head = new Head(0, "", "");
    public Head getHead() {
        return head;
    }
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        Head temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public void add(Head heroNode) {
        Head temp = head;
        while (true) {
            if (temp.next == null) {//
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    public void update(Head newHead) {
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        Head temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == newHead.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newHead.name;
            temp.nickname = newHead.nickname;
        } else {
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newHead.no);
        }
    }
    public void del(int no) {
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }

        Head temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }

}

class Head {
    public int no;
    public String name;
    public String nickname;
    public Head next;
    public Head pre;

    public Head(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
    @Override
    public String toString() {
        return "Head [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }

}
