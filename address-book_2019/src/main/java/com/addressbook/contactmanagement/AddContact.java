package com.addressbook.contactmanagement;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.IValidationError;
import org.apache.wicket.validation.ValidationError;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.addressbook.businesslogic.ContactPersonImpl;
import com.addressbook.dto.ContactPerson;
import com.addressbook.dto.User;
import com.addressbook.errorhandling.ErrorFilter;
import com.addressbook.landing.LoginPage;
import com.addressbook.landing.UserSession;
import com.addressbook.validator.PhoneValidator;
import com.addressbook.validator.UserNameValidator;



public class AddContact extends WebPage{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1861107066037065657L;
	private static final Logger LOGGER=LoggerFactory.getLogger(AddContact.class);
	private FeedbackPanel succesFeedBackPanel;



	
	private ContactPersonImpl cPersonImpl;
	private User user;
	
	@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
	
	public AddContact() {
		
		
		cPersonImpl=new ContactPersonImpl();
		FeedbackPanel errorFeedBackPanel = new FeedbackPanel("feedback",
				new ErrorFilter(FeedbackMessage.ERROR));
		succesFeedBackPanel = new FeedbackPanel("success",
				new ErrorFilter(FeedbackMessage.SUCCESS));
		
		add(errorFeedBackPanel);
		add(succesFeedBackPanel);
		add(new Label("msg", new Model<String>(UserSession.getInstance().getUser().getEmail())));

		setDefaultModel(new CompoundPropertyModel(new LoadableDetachableModel() {
            protected Object load() {
            	
                return cPersonImpl.getEmptyPerson();
            }
        }));
		
		user=UserSession.getInstance().getUser();
		
		add(new AddContactForm("addForm", getDefaultModel()));
		
		
	}
	
	


	
	private class AddContactForm extends Form {

		/**
		 * 
		 */
		private static final long serialVersionUID = 4806188515141329802L;

		@SuppressWarnings("unchecked")
		public AddContactForm(String id, IModel model) {
			super(id, model);
			
			
			TextField< String> name=new RequiredTextField<>("namePerson");
			name.add(new UserNameValidator());
			
			add(name);
			
			
			TextField< String> phoneNumber=new RequiredTextField<>("phoneNumber");
			phoneNumber.add(new PhoneValidator());
			add(phoneNumber);
			
			TextField< String> emailAdd=new RequiredTextField<>("emailAdd");
			emailAdd.add(StringValidator.maximumLength(150));
			emailAdd.add(EmailAddressValidator.getInstance());
			add(emailAdd);
			
			
			addSaveButton();
			backToSearch();
			
		
		}
		
		@SuppressWarnings({ "serial" })
		private void addSaveButton() {
			
			 add(new Button("add") {
		        	public void onSubmit() {
		        		
		                ContactPerson c = (ContactPerson) getForm().getModelObject();
		                boolean temp=cPersonImpl.saveContact(user,c);
		              
		                if (temp) {
		                	info("Saved Successfully");
		                	succesFeedBackPanel.getFeedbackMessages().success(this, "Saved Successfully");
		                	info("saved");
						} else {
							
							error("User cannot be saved");
							LOGGER.info("User cannot be saved");

						}
		                
		                
		       
		                
		                
		                
		            }

		           
		            });
		
	}
		
		private void backToSearch() {
			
			add(new AjaxLink<String>("back") {

				/**
				 * 
				 */
				private static final long serialVersionUID = 6573750371569433358L;

				@Override
				public void onClick(AjaxRequestTarget target) {
					// TODO Auto-generated method stub
					setResponsePage(ResponsePage.class);
				}
			});
		}


			
			
	}
	
}