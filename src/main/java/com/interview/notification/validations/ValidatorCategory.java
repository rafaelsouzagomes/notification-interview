package com.interview.notification.validations;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.notification.model.Category;
import com.interview.notification.repositories.CategoryRepository;

@Service
public class ValidatorCategory {
	
	private CategoryRepository categoryRepository;

	public void validateEntity(Long idCategory) {
		Optional<Category> categoryOpt = categoryRepository.findById(idCategory);
		if(categoryOpt.isEmpty())
			throw new ValidationNotificationGlobalException("This category doesn't extist.");
	}
	
	@Autowired
	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
}
