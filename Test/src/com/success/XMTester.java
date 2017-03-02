package com.success;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XMTester {
	static Map<String, List<MenuItem>> codeMap = new HashMap<String, List<MenuItem>>();
	
	private static void parseXML(){
		InputStream inputStream = XMTester.class
                .getResourceAsStream("menuItems.xml");
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(MenuItems.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			MenuItems menuItems = (MenuItems) jaxbUnmarshaller.unmarshal(inputStream);
			
			for(MenuItem menuItem : menuItems.getMenuItems()){
				List<MenuItem> itemsList = new ArrayList<>();
				extractData(menuItem, itemsList);	
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	private static void reverseList(){
		
		Set<String> keySet = codeMap.keySet();
		for(String key : keySet){
			List<MenuItem> items = codeMap.get(key);
			Collections.reverse(items);
		}
	}
	private static void extractData(MenuItem menuItem, List<MenuItem> itemsList){
		MenuItem tempItem = new MenuItem();
		tempItem.setCode(menuItem.getCode());
		tempItem.setName(menuItem.getName());
		tempItem.setNavigateTo(menuItem.getNavigateTo());
		itemsList.add(tempItem);
		List<MenuItem> clonedList = new ArrayList<>(itemsList);
		codeMap.put(tempItem.getCode(), clonedList);
		
		if(menuItem.getChildren()!=null && menuItem.getChildren().size()>0){
			for(MenuItem mItem : menuItem.getChildren()){
				extractData(mItem, itemsList);
			}
			itemsList.remove(itemsList.size()-1);
		}else{
			itemsList.remove(itemsList.size()-1);
		}
	}
	
	public static void main(String[] args) {
		parseXML();
	}
}