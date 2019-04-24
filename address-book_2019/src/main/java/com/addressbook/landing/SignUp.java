package com.addressbook.landing;

import java.awt.Label;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.addressbook.dto.User;
import com.addressbook.search.ResponsePage;

public class SignUp extends WebPage{
	/**
	 * 
	 */
	private static final long serialVersionUID = 488568181178862465L;
	private final User user;

	public SignUp(final PageParameters parameters) {
		user=new User();
		Form<Object> form=new Form<Object>("formSignUp");
		form.add(new TextField<String>("emailId",new PropertyModel<String>(user,"email")));
		final PasswordTextField password = new PasswordTextField("password",
				new PropertyModel<String>(user,"password"));
		
		
		final PasswordTextField cpassword = new PasswordTextField("cpassword",
				Model.of(""));
		
		
		form.add(new Button("signUpSubmit") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1503574524012955920L;

			@Override
			public void onSubmit() {
				// TODO Auto-generated method stub
				super.onSubmit();
				UserSession.getInstance().setUser(user);
				PageParameters pageParameters = new PageParameters();
				pageParameters.add("email", user.getEmail());
				System.out.println("emailid is "+user.getEmail());
				System.out.println("pass is "+user.getPassword());
				setResponsePage(ResponsePage.class, pageParameters);
			}
		});
		add(form);
		form.add(password);
		form.add(cpassword);
		form.add(new EqualPasswordInputValidator(password, cpassword));
		
		
	}

}
