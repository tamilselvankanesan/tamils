package com.success.programs;

import java.util.ArrayList;
import java.util.List;

/**
 * using 2 pointers (o(1))
 * using visited flag - requires change the data structure (o(n))
 * using the addresss -> add the address to the arraylist (o(n))
 * @author btamilselvan
 *
 */
public class CircularLinkedList {

	private Node n1 ;
	
	private void createdCircularLinkedList(){
		Node n2 = new Node("2");
		Node n3 = new Node("3");
		Node n4 = new Node("4");
		Node n5 = new Node("5");
		
		n1 = new Node("1");
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n1;
		
	}
	
	private void detectCircularUsingAddress(){
		List<String> addressList = new ArrayList<>();
		addressList.add(n1.toString());
		while(true){
			if(n1.next != null && addressList.contains(n1.next.toString())){
				System.out.println("circular found using address");
				break;
			}
			if(n1.next == null){
				System.out.println("circular not found using address");
				break;
			}
			addressList.add(n1.next.toString());
			n1 = n1.next;
		}
	}
	
	private void detectCircuarUsingPointers(){
		int p1 = 0;
		Node prev = n1;
		Node current = n1;
		
		while(true){
			
			if(current.next == null){
				System.out.println("no circular dependency ... ");
				break;
			}
			
			if(prev!=null && current.next == prev){
				System.out.println("found circular dependency ... ");
				break;
			}
			
			if(p1%2 == 0){
				prev = prev.next;
			}
			current = current.next;
			p1++;
			
		}
		System.out.println("total loop count = "+p1);
	}
	
	public static void main(String[] args) {
		CircularLinkedList cll = new CircularLinkedList();
		cll.createdCircularLinkedList();
		cll.detectCircuarUsingPointers();
		cll.detectCircularUsingAddress();
	}

	class Node{
		String value;
		Node next;
		Node(String value, Node next){
			this.value = value;
			this.next = next;
		}
		Node(String value){
			this.value = value;
		}
	}
}
