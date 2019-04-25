package com.addressbook.validator;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

public class UserNameValidator implements IValidator<String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2681343405902434349L;
	private final static String userPatter="/^[A-Z]+$/i";
	@Override
	public void validate(IValidatable<String> validatable) {
		// TODO Auto-generated method stub
		String _name=validatable.getValue();
		if (_name == null || _name.length() == 0) {
			error(validatable, "Name is Empty");
		}
		if (!_name.matches(userPatter)) {
			error(validatable, "Name is invalid");
		}
	}
	
	private void error(IValidatable<String> validatable, String errorKey) {
		ValidationError error = new ValidationError();
		error.addKey(getClass().getSimpleName() + "." + errorKey);
		validatable.error(error);
	}

}
