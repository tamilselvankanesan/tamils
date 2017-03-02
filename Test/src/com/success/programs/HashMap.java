package com.success.programs;

public class HashMap<K,V> {
	
	private int bucketSize = 10;
	private Entry<K,V>[] entries = new Entry[bucketSize];
	public HashMap(){
	}
	public void put(K key, V value){
		int bucketId = getBucketId(key);
		Entry<K,V> entry = entries[bucketId];
		boolean entryExists = false;
		if(entry == null){
			entry = new Entry<>(key, value, null);
			entries[bucketId] = entry;
		}else{
			while(entry.next!=null){
				if(entry.key.equals(key)){
					//replace value
					entry.value = value;
					entryExists = true;
					break;
				}
				entry = entry.next;
			}
			if(!entryExists){
				Entry<K,V> newEntry = new Entry<>(key, value, null);
				entry.next = newEntry;	
			}
		}
	}
	public V get(K key){
		int bucketId = getBucketId(key);
		if(bucketId<0 || entries.length<bucketId || entries[bucketId] ==null){
			return null;
		}
		Entry<K,V> entry = entries[bucketId];
		while(entry.next!=null){
			if(entry.key.equals(key)){
				break;
			}
			entry = entry.next;
		}
		if(entry.key.equals(key)){
			return entry.value;
		}
		return null;
	}
	private int getBucketId(K key){
		if(key==null){
			return 1;
		}
		int bucketId = Math.abs(key.hashCode())%bucketSize;
		if(bucketId == 0){
			bucketId = 1;
		}
		return bucketId;
	}
	class Entry<X,Y>{
		X key;
		Y value;
		Entry<X,Y> next;
		Entry (X key, Y value, Entry<X,Y> next){
			this.value = value;
			this.key = key;
			this.next = next;
		}
	}
	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<>();
		map.put("Hello", "Test");
		map.put("Hello1", "Test");
		map.put("Hello", "Helllo workd");
		map.put("Hello2", "Helllo Test");
		System.out.println(map.get("Hello"));
		System.out.println(map.get("Hello2"));
		System.out.println(map.get("Hello1"));
	}
}
