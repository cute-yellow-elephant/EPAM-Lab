package test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {
	
	public class InnerMain{
		
	}
	
	public static void main(String[] args) {
//		Tree tree = new Tree();
//		System.out.println(tree);
//		B.growTree(tree);
//		System.out.println(tree);
		
		Class<?> tree;
		try {
			tree = Main.class.getClassLoader().loadClass("Tree");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List list = new ArrayList(Collections.nCopies(10, null));
		System.out.println(list);
		list.add(null);
		System.out.println(list);
	}

}
