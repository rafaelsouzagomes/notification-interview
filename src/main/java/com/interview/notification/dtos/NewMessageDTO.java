package com.interview.notification.dtos;

import javax.validation.constraints.NotNull;

public class NewMessageDTO {

	@NotNull(message = "Category cannot be null")
	private final Long idCategory;
	
	@NotNull(message = "User Origin cannot be null")
	private final Long idUser_origin;
	
	@NotNull(message = "Message cannot be null")
	private final String message;
	
	public NewMessageDTO(Long idCategory, String message , Long idUser) {
		this.idCategory = idCategory;
		this.message = message;
		this.idUser_origin = idUser;
	}
	
	public Long getIdCategory() {
		return idCategory;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Long getIdUser_origin() {
		return idUser_origin;
	}
}
