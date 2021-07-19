package algorithm.data_structures;

import java.util.Stack;

/**
 * @Author: Rita
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        Head hero1 = new Head(1, "张飞", "zhangfei");
        Head hero2 = new Head(2, "诸葛亮", "zhugeliang");
        Head hero3 = new Head(3, "项羽", "xiangyu");
        Head hero4 = new Head(4, "曹操", "Caocao");

        SingleLinkedList singleLinkedList = new SingleLinkedList();

        singleLinkedList.add(hero1);
        singleLinkedList.add(hero4);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);

        System.out.println("原来链表的情况~~");
        singleLinkedList.list();

//		System.out.println("反转单链表~~");
//		reversetList(singleLinkedList.getHead());
//		singleLinkedList.list();

        System.out.println("测试逆序打印单链表, 没有改变链表的结构~~");
        reversePrint(singleLinkedList.getHead());

		singleLinkedList.addByOrder(hero1);
		singleLinkedList.addByOrder(hero4);
		singleLinkedList.addByOrder(hero2);
		singleLinkedList.addByOrder(hero3);

		singleLinkedList.list();

		Head newHead = new Head(2, "小卢", "玉麒麟~~");
		singleLinkedList.update(newHead);

		System.out.println("修改后的链表情况~~");
		singleLinkedList.list();

		singleLinkedList.del(1);
		singleLinkedList.del(4);
		System.out.println("删除后的链表情况~~");
		singleLinkedList.list();

		System.out.println("有效的节点个数=" + getLength(singleLinkedList.getHead()));//2

		Head res = findLastIndexNode(singleLinkedList.getHead(), 3);
		System.out.println("res=" + res);


    }

    public static void reversePrint(Head head) {
        if(head.next == null) {
            return;
        }
        Stack<Head> stack = new Stack<Head>();
        Head cur = head.next;
        while(cur != null) {
            stack.push(cur);
            cur = cur.next; 
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop()); 
        }
    }

    public static void reversetList(Head head) {
        if(head.next == null || head.next.next == null) {
            return ;
        }
        Head cur = head.next;
        Head next = null;
        Head reverseHead = new Head(0, "", "");
        while(cur != null) {
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur; 
            cur = next;
        }
        head.next = reverseHead.next;
    }

    public static Head findLastIndexNode(Head head, int index) {
        if(head.next == null) {
            return null;
        }
        int size = getLength(head);
        if(index <=0 || index > size) {
            return null;
        }
        Head cur = head.next; 
        for(int i =0; i< size - index; i++) {
            cur = cur.next;
        }
        return cur;

    }
    public static int getLength(Head head) {
        if(head.next == null) { 
            return 0;
        }
        int length = 0;
        Head cur = head.next;
        while(cur != null) {
            length++;
            cur = cur.next; 
        }
        return length;
    }

}



class SingleLinkedList {
   
    private Head head = new Head(0, "", "");
    public Head getHead() {
        return head;
    }
    public void add(Head heroNode) {
        Head temp = head;
        while(true) {
            if(temp.next == null) {//
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    public void addByOrder(Head heroNode) {
        Head temp = head;
        boolean flag = false;
        while(true) {
            if(temp.next == null) {
                break;
            }
            if(temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag) {
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    public void update(Head newHead) {
        if(head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        Head temp = head.next;
        boolean flag = false;
        while(true) {
            if (temp == null) {
                break;
            }
            if(temp.no == newHead.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag) {
            temp.name = newHead.name;
            temp.nickname = newHead.nickname;
        } else {
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newHead.no);
        }
    }

    public void del(int no) {
        Head temp = head;
        boolean flag = false;
        while(true) {
            if(temp.next == null) {
                break;
            }
            if(temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag) {
            temp.next = temp.next.next;
        }else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }
    public void list() {
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }
        Head temp = head.next;
        while(true) {
            if(temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

}


