package com.interview.notification.dtos.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.interview.notification.dtos.CategoryDTO;
import com.interview.notification.model.Category;

@Mapper
public interface CategoryMapper {

	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
	
	 @Mapping(source = "idCategory", target = "idCategory")
	 @Mapping(source = "typeCategory", target = "typeCategory")
	 CategoryDTO toDTO(Category category);
	 
	 List<CategoryDTO> toDTOList(List<Category> category);
}
