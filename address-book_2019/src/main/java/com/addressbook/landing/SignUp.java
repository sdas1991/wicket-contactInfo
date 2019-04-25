package com.addressbook.landing;

import java.awt.Label;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import com.addressbook.businesslogic.UserServiceImpl;
import com.addressbook.contactmanagement.ResponsePage;
import com.addressbook.dto.ContactPerson;
import com.addressbook.dto.User;
import com.addressbook.errorhandling.ErrorFilter;
import com.addressbook.validator.PasswordValidator;

public class SignUp extends WebPage{
	/**
	 * 
	 */
	private static final long serialVersionUID = 488568181178862465L;
	private UserServiceImpl uImpl;

	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	public SignUp(final PageParameters parameters) {
		
		uImpl=new UserServiceImpl();
		
		setDefaultModel(new CompoundPropertyModel(new LoadableDetachableModel() {
            protected Object load() {
                return uImpl.getEmptyUser();
            }
        }));
		
		
		FeedbackPanel errorFeedBackPanel = new FeedbackPanel("feedback",
				new ErrorFilter(FeedbackMessage.ERROR));
		FeedbackPanel succesFeedBackPanel = new FeedbackPanel("success",
				new ErrorFilter(FeedbackMessage.SUCCESS));
		
		add(errorFeedBackPanel);
		add(succesFeedBackPanel);
		
		
		add(new SignUpForm("formSignUp", getDefaultModel()));
		
		

		
	}
	
	
	private class SignUpForm extends Form {

		/**
		 * 
		 */
		private static final long serialVersionUID = -867765566713221694L;
		

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public SignUpForm(String id, IModel model) {
			super(id, model);
			
			
			
			TextField email=new TextField("emailId");
			//Validate Email
			email.add(EmailAddressValidator.getInstance());
			add(email);
			
			final PasswordTextField password = new PasswordTextField("password");
			
			password.add(new PasswordValidator());
			
			final PasswordTextField cpassword = new PasswordTextField("cpassword",
					Model.of(""));
			
			
			add(password);
			add(cpassword);
			add(new EqualPasswordInputValidator(password, cpassword));
			addButton();
			
			
		
		}
		
		public void addButton() {
			
			
			add(new Button("signUpSubmit") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1503574524012955920L;

				@Override
				public void onSubmit() {
					// TODO Auto-generated method stub
					super.onSubmit();
					User user=(User) getForm().getModelObject();
								
					UserSession.getInstance().setUser(user);
					
					uImpl.saveUser(user);
					
					PageParameters pageParameters = new PageParameters();
					pageParameters.add("email", user.getEmail());
					
					setResponsePage(ResponsePage.class, pageParameters);
				}
			});
		}

		
		
	}

}
