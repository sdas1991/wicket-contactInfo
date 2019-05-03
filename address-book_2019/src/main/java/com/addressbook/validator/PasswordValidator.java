package com.addressbook.validator;

import org.apache.wicket.validation.INullAcceptingValidator;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

public class PasswordValidator implements IValidator<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7020418732857916892L;

	@Override
	public void validate(IValidatable<String> validatable) {
		// TODO Auto-generated method stub
		
		String pasword=validatable.getValue();
		
		if (pasword == null || pasword.length() == 0) {
			error(validatable, "invalid");
		}
		if (pasword.length() < 3 || pasword.length() > 10) {
			error(validatable, "invalid");
		}
	}
	
	private void error(IValidatable<String> validatable, String errorKey) {
		ValidationError error = new ValidationError();
		error.addKey(getClass().getSimpleName() + "." + errorKey);
		validatable.error(error);
	}


}
