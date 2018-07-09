package com.success.amazon.fb;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class FindFbDistance {
	private static SortedSet<Integer> set = new TreeSet<>();
	private static List<Integer> list = new ArrayList<>();
	/**
	 * 
	 * Step 1: increment the counter by 1
	 * Step 2: for each friends in his friends list check if anyone matches 'to', if macthes then add the path and the count
	 * Step 3: if not matches, set the path (path is important to skip the already visited friend in a hierarchy to avoid the indefinite calls)
	 * Step 4: for each friends in his friends list and if the path doesn't contain his friend do the stpe 2 thru 4 until program exits  
	 * @param from
	 * @param to
	 * @param count
	 * @param path
	 */
	private void find(Friend from, Friend to, int count, String path ){
		boolean exists = false;
		count++;
		for(Friend f : from.getFriends()){
			if(f.getId().equals(to.getId())){
				path = path.concat(from.getName());
				set.add(count);
				list.add(count);
				System.out.println("id ->"+to.getId() +"  Path->"+path);
				exists = true;
			}
		}
		if(set.isEmpty() || count<set.first()){
			if(!exists){
				path = path.concat(from.getName());
				for(Friend f : from.getFriends()){
					if(!path.contains(f.getName())){
						find(f,to,count,path);
					}
				}
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Friend a = new Friend("A", 1);
		Friend b = new Friend("B", 2);
		Friend c = new Friend("C", 3);
		Friend d = new Friend("D", 4);
		Friend e = new Friend("E", 5);
		Friend f = new Friend("F", 6);
		Friend g = new Friend("G", 7);
		Friend h = new Friend("H", 8);
		Friend i = new Friend("I", 9);
		Friend j = new Friend("J", 10);
		
		a.addFriend(b);
		a.addFriend(c);
		a.addFriend(d);
		a.addFriend(j);
		
		b.addFriend(a);
		b.addFriend(f);
		
		c.addFriend(a);
		c.addFriend(e);
		c.addFriend(f);
		
		d.addFriend(a);
		d.addFriend(g);
		
		e.addFriend(c);
		e.addFriend(g);
		e.addFriend(h);
		
		f.addFriend(b);
		f.addFriend(c);
		f.addFriend(h);
		
		g.addFriend(d);
		g.addFriend(e);
		
		h.addFriend(e);
		h.addFriend(f);
		h.addFriend(i);
		h.addFriend(j);
		
		i.addFriend(h);
		i.addFriend(j);
		
		j.addFriend(h);
		j.addFriend(i);
		j.addFriend(a);
	
		new FindFbDistance().find(j, d,0,"");
		System.out.println(set);
		System.out.println(list);
	}

}
