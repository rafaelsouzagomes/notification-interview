package com.interview.notification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interview.notification.model.Category;

public interface CategoryRepository  extends JpaRepository<Category, Long>  {

	Category findByTypeCategory(String category);

}