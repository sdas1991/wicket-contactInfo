package com.addressbook.landing;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.IValidationError;
import org.apache.wicket.validation.ValidationError;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.addressbook.businesslogic.UserServiceImpl;
import com.addressbook.contactmanagement.ResponsePage;
import com.addressbook.dto.User;
import com.addressbook.errorhandling.ErrorFilter;
import com.addressbook.validator.PasswordValidator;


public class LoginPage extends WebPage{
	/**
	 * 
	 */
	private static final Logger LOGGER=LoggerFactory.getLogger(LoginPage.class);
	private static final long serialVersionUID = -459295927972351123L;
	private UserServiceImpl uImpl;
	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	public LoginPage() {
		LOGGER.info("inside login page");
		FeedbackPanel errorFeedBackPanel = new FeedbackPanel("feedback",
				new ErrorFilter(FeedbackMessage.ERROR));
		FeedbackPanel succesFeedBackPanel = new FeedbackPanel("success",
				new ErrorFilter(FeedbackMessage.SUCCESS));
		
		add(errorFeedBackPanel);
		add(succesFeedBackPanel);
		
		uImpl=new UserServiceImpl();
		
		setDefaultModel(new CompoundPropertyModel(new LoadableDetachableModel() {
            protected Object load() {
                return uImpl.getEmptyUser();
            }
        }));
		
		
		
		add(new LoginForm("form", getDefaultModel()));
	
		
	
	}
	
	@SuppressWarnings({ "rawtypes", "serial" })
	private class LoginForm extends Form{
		

		@SuppressWarnings("unchecked")
		public LoginForm(String id, IModel model) {
			super(id, model);
			
			addFormComponent();
			addButton();
			addLink();
		}
		
		public void addFormComponent() {
			TextField<String> userName=new TextField<String>("emailId");
			userName.add(EmailAddressValidator.getInstance());
			add(userName);
			
			PasswordTextField password=new PasswordTextField("password");
			password.add(new PasswordValidator());
			add(password);
		}
		
		public void addButton() {
			add(new Button("submitLog") {
				
				/**
				 * 
				 */
				private static final long serialVersionUID = -6525001367274151206L;

				@Override
				public void onSubmit() {
					// TODO Auto-generated method stub
					super.onSubmit();
					User user=(User)getForm().getModelObject();
					if (uImpl.validateUser(user)) {
						
						UserSession.getInstance().setUser(user);
						
						
						setResponsePage(ResponsePage.class);
					} else {
						error((IValidationError) new ValidationError().addKey("userNotFound"));
						LOGGER.info("userNotFound");

					}
				}
			});
			
		}
		
		public void addLink() {
			add(new AjaxLink<String>("backSignUp") {
				


				/**
				 * 
				 */
				private static final long serialVersionUID = -7650762579919167112L;

				@Override
				public void onClick(AjaxRequestTarget target) {
					// TODO Auto-generated method stub
					PageParameters pageparam=new PageParameters();
					pageparam.add("id", "fromlogin");
					setResponsePage(SignUp.class, pageparam);
					
				}
			});
		}
		
	}
	
	
	

}
