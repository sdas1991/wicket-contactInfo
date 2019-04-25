package com.addressbook.validator;

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
			error(validatable, "phoneNumber is Empty");
		}
		if (phoneNumber.length() != 10) {
			error(validatable, "phoneNumber invalid");
		}
		/*if(!phonePattern.matches(phonePattern)) {
			System.out.println("eror phone");
			error(validatable, "phoneNumber invalid");
		}*/
	}
	
	private void error(IValidatable<String> validatable, String errorKey) {
		ValidationError error = new ValidationError();
		error.addKey(getClass().getSimpleName() + "." + errorKey);
		validatable.error(error);
	}

}
