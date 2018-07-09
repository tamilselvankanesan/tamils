package com.success;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
 
public class ThreadSafeIteratorExample {
	private void add(){
		
	}
    public static void main(String[] args) {
    	List<String> myList1 = new ArrayList<>();
    	myList1.add("1");
        myList1.add("2");
        myList1.add("3");
        myList1.add("4");
        myList1.add("5");
        
        List<String> myList = new CopyOnWriteArrayList<String>(myList1);
        /*myList.add("1");
        myList.add("2");
        myList.add("3");
        myList.add("4");
        myList.add("5");*/
        
        myList1.add("9");
        Iterator<String> it = myList.iterator();
        while(it.hasNext()){
            String value = it.next();
            System.out.println("List Value:"+value);
            if(value.equals("3")){
                myList.remove("4");
                myList.add("6");
                myList.add("7");
            }
        }
        System.out.println("List Size:"+myList.size());
 
        Map<String,String> myMap = 
             new ConcurrentHashMap<String,String>();
        myMap.put("1", "1");
        myMap.put("2", "2");
        myMap.put("3", "3");
 
        Iterator<String> it1 = myMap.keySet().iterator();
        while(it1.hasNext()){
            String key = it1.next();
            System.out.println("Map Value:"+myMap.get(key));
            if(key.equals("1")){
                myMap.remove("3");
                myMap.put("4", "4");
                myMap.put("5", "5");
            }
        }
 
        System.out.println("Map Size:"+myMap.size());
        
        try {
			main1(args);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public static void main1(String[] args) throws NoSuchMethodException, SecurityException {
    	List<Method> myList1 = new ArrayList<>();
    	myList1.add(ThreadSafeIteratorExample.class.getDeclaredMethod("add"));
    	myList1.add(ThreadSafeIteratorExample.class.getDeclaredMethod("add"));
    	myList1.add(ThreadSafeIteratorExample.class.getDeclaredMethod("add"));
    	myList1.add(ThreadSafeIteratorExample.class.getDeclaredMethod("add"));
    	myList1.add(ThreadSafeIteratorExample.class.getDeclaredMethod("add"));
        
        List<Method> myList = new CopyOnWriteArrayList<Method>(myList1);
        /*myList.add("1");
        myList.add("2");
        myList.add("3");
        myList.add("4");
        myList.add("5");*/
        int index = 0;
        myList1.add(ThreadSafeIteratorExample.class.getDeclaredMethod("add"));
        Iterator<Method> it = myList.iterator();
        while(it.hasNext()){
            Method value = it.next();
            System.out.println("List Value:"+value);
            index++;
            if(index == 3){
                myList.remove(4);
                System.out.println("removed");
                myList.add(ThreadSafeIteratorExample.class.getDeclaredMethod("add"));
                myList.add(ThreadSafeIteratorExample.class.getDeclaredMethod("add"));
            }
        }
        System.out.println("List Size:"+myList.size());
 
        Map<String,String> myMap = 
             new ConcurrentHashMap<String,String>();
        myMap.put("1", "1");
        myMap.put("2", "2");
        myMap.put("3", "3");
 
        Iterator<String> it1 = myMap.keySet().iterator();
        while(it1.hasNext()){
            String key = it1.next();
            System.out.println("Map Value:"+myMap.get(key));
            if(key.equals("1")){
                myMap.remove("3");
                myMap.put("4", "4");
                myMap.put("5", "5");
            }
        }
 
        System.out.println("Map Size:"+myMap.size());
    }
}
