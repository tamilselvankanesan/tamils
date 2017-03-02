package com.success.amazon;


public class ReverseLinkedList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReverseLinkedList rll = new ReverseLinkedList();
		Node n1 = rll.new Node();
		n1.data="a";
		
		Node n2 = rll.new Node();
		n2.data="b";
		
		Node n3 = rll.new Node();
		n3.data="c";
		
		Node n4 = rll.new Node();
		n4.data="d";
		
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		
		Node reversedNode = null;
		Node parenNode = n1;
		Node prevNode = null;
		while(parenNode.next!=null){
			if(prevNode!=null){
				prevNode.next = reversedNode;
			}
			reversedNode = prevNode;
			prevNode = parenNode;
			parenNode = parenNode.next;
		}
		prevNode.next = reversedNode;
		parenNode.next = prevNode;
		while(parenNode.next!=null){
			System.out.println(parenNode.data);
			parenNode = parenNode.next;
		}
		System.out.println(parenNode.data);
		
	}
	class Node{
		String data;
		Node next;
		Node(){
			
		}
		Node(Node parenNode){
			this.data = parenNode.data;
			this.next = parenNode.next;
		}
	}
}
