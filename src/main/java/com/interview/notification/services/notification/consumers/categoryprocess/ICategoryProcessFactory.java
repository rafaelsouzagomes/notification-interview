package com.interview.notification.services.notification.consumers.categoryprocess;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ICategoryProcessFactory {

	private final Map<String, ICategoryProcess> categories;
	
	@Autowired
	public ICategoryProcessFactory(@Qualifier("FINANCE")ICategoryProcess finance,
								   @Qualifier("MOVIES")ICategoryProcess movie,
								   @Qualifier("SPORTS")ICategoryProcess sport) {
		
		categories = new HashMap<>();
		categories.put("FINANCE", finance);
		categories.put("MOVIES", movie);
		categories.put("SPORTS", sport);
	}
	
    public ICategoryProcess getCategoryBy(String key) {
        return categories.get(key);
    }
}
