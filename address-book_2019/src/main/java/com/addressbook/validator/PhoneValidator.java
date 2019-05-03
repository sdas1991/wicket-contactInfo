package com.addressbook.validator;

import java.util.regex.Pattern;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

public class PhoneValidator implements IValidator<String> {
	
	private static final String phonePattern="^[0-9]+$";
	

	@Override
	public void validate(IValidatable<String> validatable) {
		// TODO Auto-generated method stub
		
		String phoneNumber=validatable.getValue();
		if (phoneNumber == null || phoneNumber.length() == 0) {
			error(validatable, "invalid");
		}
		if (phoneNumber.length() != 10) {
			error(validatable, "invalid");
		}
		if(!phoneNumber.matches( phonePattern)) {
			System.out.println("error in phone");
			error(validatable, "invalid");
		}
	}
	
	private void error(IValidatable<String> validatable, String errorKey) {
		ValidationError error = new ValidationError();
		error.addKey(getClass().getSimpleName() + "." + errorKey);
		validatable.error(error);
	}

}
