package com.interview.notification.validations;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.notification.model.UserCustomer;
import com.interview.notification.repositories.UserRepository;

@Service
public class ValidatorUserCostumer {

	private UserRepository userRepository;
	
	public void validateEntity(Long idUser) {
		Optional<UserCustomer> userCustomerOpt = userRepository.findById(idUser);
		
		if(userCustomerOpt.isEmpty())
			throw new ValidationNotificationGlobalException("This User doesn't extist.");
	}
	
	public void validateEmail(UserCustomer user) {
		String email = user.getEmail();
		if(Objects.isNull(email))
			throw new ValidationNotificationGlobalException("We Cannot send a email to the user (ID: " + user.getIdUser()+" ) because we don't have his email.");
	}
	
	public void validateEmailAppActive(UserCustomer user) {
		String email = user.getEmail();
		if(Objects.isNull(email))
			throw new ValidationNotificationGlobalException("We Cannot send a email to the user (ID: " + user.getIdUser()+" ) because we don't have his email.");
	}
	
	public void validatePhoneNumber(UserCustomer user) {
		String phoneNumber = user.getPhoneNumber();
		if(phoneNumber.isBlank())
			throw new ValidationNotificationGlobalException("The user (ID: " + user.getIdUser() + ") doesn't have an phone number stored.");
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}
