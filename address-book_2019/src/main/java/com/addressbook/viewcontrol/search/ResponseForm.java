package com.addressbook.viewcontrol.search;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

import com.addressbook.dto.ContactPerson;

public class ResponseForm extends Form<ContactPerson>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2886852121742631196L;

	public ResponseForm(String id, IModel<ContactPerson> model) {
		super(id, model);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onSubmit() {
		// TODO Auto-generated method stub
		super.onSubmit();
		ContactPerson person = getModelObject();
		System.out.println(person.getNamePerson());
	}

}
