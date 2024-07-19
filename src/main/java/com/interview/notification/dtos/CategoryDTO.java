package com.interview.notification.dtos;

public class CategoryDTO {

	private final Long idCategory;
	
	private final String typeCategory;
	
	public CategoryDTO(Long idCategory, String typeCategory) {
		this.idCategory = idCategory;
		this.typeCategory = typeCategory;
	}
	
	public Long getIdCategory() {
		return idCategory;
	}
	
	public String getTypeCategory() {
		return typeCategory;
	}
}
