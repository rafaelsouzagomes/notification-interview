package com.interview.notification.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.notification.dtos.CategoryDTO;
import com.interview.notification.dtos.mappers.CategoryMapper;
import com.interview.notification.model.Category;
import com.interview.notification.repositories.CategoryRepository;

@Service
public class CategoryService {

	private CategoryRepository categoryRepository;
	
	public List<CategoryDTO> findAllCategories() {
		 List<Category> categories = categoryRepository.findAll();
		 return CategoryMapper.INSTANCE.toDTOList(categories);
	}
	
	@Autowired
	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
}
