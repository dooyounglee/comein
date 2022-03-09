package com.come.in.util;

import java.util.Map;

public class Util {

	public static void printMap(Map<String, Object> map) {
		java.util.Set<String> set = map.keySet();
		for(String a : set) {
			System.out.println(a+": "+map.get(a));
		}
	}
}
