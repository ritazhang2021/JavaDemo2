package algorithm.data_structures;

/**
 * @Author: Rita
 */
public class BinaryTreeDemo {

	public static void main(String[] args) {
		BinaryTree binaryTree = new BinaryTree();
		RootNode root = new RootNode(1, "Ming");
		RootNode node2 = new RootNode(2, "Huang");
		RootNode node3 = new RootNode(3, "Zhen");
		RootNode node4 = new RootNode(4, "Zhang");
		RootNode node5 = new RootNode(5, "Lai");

		root.setLeft(node2);
		root.setRight(node3);
		node3.setRight(node4);
		node3.setLeft(node5);
		binaryTree.setRoot(root);

		//test
		System.out.println("preOrder"); // 1,2,3,5,4
		binaryTree.preOrder();

		//test
		System.out.println("infixOrder");
		binaryTree.infixOrder(); // 2,1,5,3,4

		System.out.println("postOrder");
		binaryTree.postOrder(); // 2,5,4,3,1

		//Number of pre-order traversal ：4
		System.out.println("preOrder traversal~~~");
		RootNode resNode = binaryTree.preOrderSearch(5);
		if (resNode != null) {
			System.out.printf("found, num=%d name=%s ", resNode.getNum(), resNode.getName());
		} else {
			System.out.printf("not found, the people num = %d ", 5);
		}

		//infix order to traverse 3 times
		System.out.println("infixOrder~~~");
		/*RootNode resNode = binaryTree.infixOrderSearch(5);
		if (resNode != null) {
			System.out.printf("found, num=%d name=%s", resNode.getNum(), resNode.getName());
		} else {
			System.out.printf("not found, the people num = %d ", 5);
		}*/

		//post order traverse 2 times
//		System.out.println("postOrder~~~");
//		RootNode resNode = binaryTree.postOrderSearch(5);
//		if (resNode != null) {
//			System.out.printf("found, num=%d name=%s", resNode.getNum(), resNode.getName());
//		} else {
//			System.out.printf("not found, the people num = %d ", 5);
//		}

		//Test delete node
		System.out.println("before delete,preOrder");
		binaryTree.preOrder(); //  1,2,3,5,4
		binaryTree.delNode(5);
		//binaryTree.delNode(3);
		System.out.println("after delete，preOrder");
		binaryTree.preOrder(); // 1,2,3,4



	}

}

class BinaryTree {
	private RootNode root;

	public void setRoot(RootNode root) {
		this.root = root;
	}

	public void delNode(int num) {
		if(root != null) {
			//only one root
			if(root.getNum() == num) {
				root = null;
			} else {
				//recursive delete
				root.delNode(num);
			}
		}else{
			System.out.println("empty tree!");
		}
	}

	public void preOrder() {
		if(this.root != null) {
			this.root.preOrder();
		}else {
			System.out.println("empty tree!");
		}
	}


	public void infixOrder() {
		if(this.root != null) {
			this.root.infixOrder();
		}else {
			System.out.println("empty tree!");
		}
	}

	public void postOrder() {
		if(this.root != null) {
			this.root.postOrder();
		}else {
			System.out.println("empty tree!");
		}
	}


	public RootNode preOrderSearch(int num) {
		if(root != null) {
			return root.preOrderSearch(num);
		} else {
			return null;
		}
	}

	public RootNode infixOrderSearch(int num) {
		if(root != null) {
			return root.infixOrderSearch(num);
		}else {
			return null;
		}
	}

	public RootNode postOrderSearch(int num) {
		if(root != null) {
			return this.root.postOrderSearch(num);
		}else {
			return null;
		}
	}
}

class RootNode {
	private int num;
	private String name;
	private RootNode left; 
	private RootNode right; 
	public RootNode(int num, String name) {
		this.num = num;
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RootNode getLeft() {
		return left;
	}
	public void setLeft(RootNode left) {
		this.left = left;
	}
	public RootNode getRight() {
		return right;
	}
	public void setRight(RootNode right) {
		this.right = right;
	}
	@Override
	public String toString() {
		return "RootNode [num=" + num + ", name=" + name + "]";
	}

	// Recursively delete the node
	//1. If the deleted node is a leaf node, delete the node
	//2. If the deleted node is a non-leaf node, then the subtree is deleted
	public void delNode(int num) {

		//2. If the left child of the current node is not null and the left child is the one to be deleted, this.left = null;And just return (end recursive deletion)
		if(this.left != null && this.left.num == num) {
			this.left = null;
			return;
		}
		//3.If the right child of the current node is not null and the right child is the one to be deleted, then this.right= null;And just return (end recursive deletion)
		if(this.right != null && this.right.num == num) {
			this.right = null;
			return;
		}
		//4.Recursive deletion to the left subtree
		if(this.left != null) {
			this.left.delNode(num);
		}
		//5.Recursive deletion to the right subtree
		if(this.right != null) {
			this.right.delNode(num);
		}
	}

	//preOrder
	public void preOrder() {
		System.out.println(this); //first print parent
		//recursive from left subtree
		if(this.left != null) {
			this.left.preOrder();
		}
		//recursive from right subtree
		if(this.right != null) {
			this.right.preOrder();
		}
	}
	//infixOrder
	public void infixOrder() {

		//recursive from left subtree
		if(this.left != null) {
			this.left.infixOrder();
		}
		//print parent
		System.out.println(this);
		//recursive from right subtree
		if(this.right != null) {
			this.right.infixOrder();
		}
	}
	//postOrder
	public void postOrder() {
		if(this.left != null) {
			this.left.postOrder();
		}
		if(this.right != null) {
			this.right.postOrder();
		}
		System.out.println(this);
	}

	public RootNode preOrderSearch(int num) {
		System.out.println("pre order");
		if(this.num == num) {
			return this;
		}
		//1. The left child of the current node is determined to be null. If not, it is searched recursively in preorder
		//2. If the node is found, then return
		RootNode resNode = null;
		if(this.left != null) {
			resNode = this.left.preOrderSearch(num);
		}
		if(resNode != null) {
			return resNode;
		}
		//1. If the node is found, return, or not continue to judge,
		//2. Is the right child node of the current node empty? If not, continue to search right recursive preorder
		if(this.right != null) {
			resNode = this.right.preOrderSearch(num);
		}
		return resNode;
	}

	//infixOrderSearch
	public RootNode infixOrderSearch(int num) {
		RootNode resNode = null;
		if(this.left != null) {
			resNode = this.left.infixOrderSearch(num);
		}
		if(resNode != null) {
			return resNode;
		}
		System.out.println("infixOrderSearch");
		// If it is found, it is returned; if not, it is compared with the current node; if it is, it is returned
		if(this.num == num) {
			return this;
		}
		// Otherwise, continue with the right-recursive middle order search
		if(this.right != null) {
			resNode = this.right.infixOrderSearch(num);
		}
		return resNode;

	}

	//postOrderSearch
	public RootNode postOrderSearch(int num) {
		RootNode resNode = null;
		if(this.left != null) {
			resNode = this.left.postOrderSearch(num);
		}
		if(resNode != null) {//found in left sub-tree
			return resNode;
		}

		//if no left sub-tree，search right sub-tree by postOrder
		if(this.right != null) {
			resNode = this.right.postOrderSearch(num);
		}
		if(resNode != null) {
			return resNode;
		}
		System.out.println("Post Order");
		//if no left sub-tree and right sub-tree, compare current node
		if(this.num == num) {
			return this;
		}
		return resNode;
	}

}


