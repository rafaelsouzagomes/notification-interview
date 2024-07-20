package com.interview.notification.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.interview.notification.enums.TypeCategory;

@Entity
public class Category {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCategory;
	
    @Enumerated(EnumType.STRING)
	private TypeCategory typeCategory;
 
	private String description;
	
	@ManyToMany(mappedBy = "subscribedCategories")
    private List<UserCustomer> users;
	
	public void setUsers(List<UserCustomer> users) {
		this.users = users;
	}
	
	public List<UserCustomer> getUsers() {
		return users;
	}

	public Long getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Long idCategory) {
		this.idCategory = idCategory;
	}

	public TypeCategory getTypeCategory() {
		return typeCategory;
	}

	public void setTypeCategory(TypeCategory typeCategory) {
		this.typeCategory = typeCategory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
