package com.interview.notification.validations;

import java.util.Optional;


import org.springframework.stereotype.Service;

import com.interview.notification.model.UserCustomer;
import com.interview.notification.repositories.UserRepository;

@Service
public class ValidatorUserCostumer {

	private UserRepository userRepository;
	
	public void validateEntity(Long idUser) {
		Optional<UserCustomer> userCustomerOpt = userRepository.findById(idUser);
		
		if(userCustomerOpt.isEmpty())
			throw new ValidationException("This User doesn't extist.");
	}
	
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}
