package com.interview.notification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interview.notification.enums.TypeCategory;
import com.interview.notification.model.Category;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long>  {

	Category findByTypeCategory(TypeCategory typCategory);

}
