package algorithm.data_structures;

import java.util.Scanner;

/**
 * @Author: Rita
 */
public class ArrayStackDemo {

	public static void main(String[] args) {
		ArrayStack stack = new ArrayStack(4);
		String key = "";
		boolean loop = true;
		Scanner scanner = new Scanner(System.in);

		while(loop) {
			System.out.println("show");
			System.out.println("exit");
			System.out.println("push");
			System.out.println("pop");
			System.out.println("please choose");
			key = scanner.next();
			switch (key) {
				case "show":
					stack.list();
					break;
				case "push":
					System.out.println("input a number");
					int value = scanner.nextInt();
					stack.push(value);
					break;
				case "pop":
					try {
						int res = stack.pop();
						System.out.printf("The data out of the stack is %d\n", res);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case "exit":
					scanner.close();
					loop = false;
					break;
				default:
					break;
			}
		}

		System.out.println("exit~~~");
	}

}

class ArrayStack {
	private int maxSize;
	private int[] stack;
	private int top = -1;

	public ArrayStack(int maxSize) {
		this.maxSize = maxSize;
		stack = new int[this.maxSize];
	}

	public boolean isFull() {
		return top == maxSize - 1;
	}
	public boolean isEmpty() {
		return top == -1;
	}

	public void push(int value) {
		if(isFull()) {
			System.out.println("full");
			return;
		}
		top++;
		stack[top] = value;
	}

	public int pop() {
		if(isEmpty()) {
			throw new RuntimeException("stack empty~");
		}
		int value = stack[top];
		top--;
		return value;
	}
	public void list() {
		if(isEmpty()) {
			System.out.println("stack empty~");
			return;
		}
		for(int i = top; i >= 0 ; i--) {
			System.out.printf("stack[%d]=%d\n", i, stack[i]);
		}
	}

}
