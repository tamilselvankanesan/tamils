package com.success.programs;

public class TreeInsert {

	Node parent1 = null;

	private static void insert1(Integer key, String value, Node parent) {

		Node newNode = new Node(key, value);

		if (parent == null) {
			parent = newNode;
			System.out.println("new node");
			return;
		} else if (parent.key == key) {
			// already exist.. skip
			System.out.println("skipped");
			return;
		} else if (parent.left == null && parent.key > key) {
			parent.left = newNode;
			System.out.println("inserted in the left");
			return;
		} else if (parent.right == null && parent.key < key) {
			parent.right = newNode;
			System.out.println("inserted in the right");
			return;
		}

		if (parent.key > key) {
			insert1(key, value, parent.left);
		} else {
			insert1(key, value, parent.right);
		}
	}

	public static void main(String[] args) {

		TreeInsert insert = new TreeInsert();
		
		Node parent = new Node(10, "Parent");
		insert1(8, "child 1", parent);
		insert1(12, "child 2", parent);
		insert1(18, "child 3", parent);
		insert1(6, "child 4", parent);
//		System.out.println(parent);

		
		insert.traverse(parent);
		
		insert.insert2(8, "child 1");
		insert.insert2(12, "child 2");
		insert.insert2(18, "child 3");
		insert.insert2(6, "child 4");
		insert.insert2(15, "child 4");
		insert.insert2(2, "child 4");

//		System.out.println(insert.parent1);
		System.out.println("############################################");
		
		insert.traverse(insert.parent1);
	}

	void insert2(Integer key, String value) {
		Node newNode = new Node(key, value);

		if (parent1 == null) {
			parent1 = newNode;
			return;
		}
		Node another = parent1;
		while (true) {

			if (another.key == key) {
				break;
			}
			if (another.key > key) {
				// left node
				if (another.left == null) {
					another.left = newNode;
					break;
				}
				another = another.left;
			} else {
				if (another.right == null) {
					another.right = newNode;
					break;
				}
				another = another.right;
			}

		}

	}

	void traverse(Node tree) {

		if (tree == null) {
			return;
		}
		System.out.println(tree.toString());
		traverse(tree.left);
		traverse(tree.right);
	}
}

class Node {

	Integer key;
	String value;

	Node left;
	Node right;

	Node(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

	public String toString() {
		return "Key is " + this.key + " value is " + this.value;
	}
}