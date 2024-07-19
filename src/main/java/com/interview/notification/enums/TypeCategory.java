package com.interview.notification.enums;

import java.util.HashMap;
import java.util.Map;

public enum TypeCategory {

	SPORTS, FINANCE, MOVIES;
	
	private static Map<String, TypeCategory> mapCategories;
	
	static {
		mapCategories = new HashMap<>();
		mapCategories.put(SPORTS.name(), SPORTS);
		mapCategories.put(FINANCE.name(), FINANCE);
		mapCategories.put(MOVIES.name(), MOVIES);
	}

	public static TypeCategory get(String category) {
		return mapCategories.get(category);
	}
}

