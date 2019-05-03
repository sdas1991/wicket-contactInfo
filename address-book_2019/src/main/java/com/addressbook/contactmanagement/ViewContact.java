package com.addressbook.contactmanagement;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.parse.metapattern.parsers.IntegerVariableAssignmentParser;
import org.apache.wicket.validation.IValidationError;
import org.apache.wicket.validation.ValidationError;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.StringValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.addressbook.businesslogic.ContactPersonImpl;
import com.addressbook.dto.ContactPerson;
import com.addressbook.dto.User;
import com.addressbook.errorhandling.ErrorFilter;
import com.addressbook.landing.UserSession;
import com.addressbook.validator.PhoneValidator;
import com.addressbook.validator.UserNameValidator;

public class ViewContact extends WebPage{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2099163414637207501L;


	private String action;
	private static final Logger LOGGER=LoggerFactory.getLogger(ViewContact.class);
	private FeedbackPanel succesFeedBackPanel;
	
	
	
	private ContactPersonImpl cPersonImpl;
	private String search;
	private ContactPerson passObject;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	public ViewContact(PageParameters pageParameters, String action, ContactPerson passObject) {
		// TODO Auto-generated constructor stub
		this.action=action;
		this.search=pageParameters.get("name").toString();
		this.passObject=passObject;
		cPersonImpl=new ContactPersonImpl();
		
		add(new Label("msg", new Model<String>(UserSession.getInstance().getUser().getEmail())));
		
		setDefaultModel(new CompoundPropertyModel(new LoadableDetachableModel() {
            protected Object load() {
              //return the object received
            	ContactPerson cp=cPersonImpl.getEmptyPerson();
            	cp.setEmailAdd(passObject.getEmailAdd());
            	cp.setNamePerson(passObject.getNamePerson());
            	cp.setPhoneNumber(passObject.getPhoneNumber());
            	return cp;
            }
        }));
		
		FeedbackPanel errorFeedBackPanel = new FeedbackPanel("feedback",
				new ErrorFilter(FeedbackMessage.ERROR));
		succesFeedBackPanel = new FeedbackPanel("success",
				new ErrorFilter(FeedbackMessage.SUCCESS));
		
		//add(new FeedbackPanel("feedback"));
		add(errorFeedBackPanel);
		add(succesFeedBackPanel);
		
		
	
		add(new  ContactForm("viewForm", getDefaultModel()));
	
			

	}
	
	private class ContactForm extends Form {

		/**
		 * 
		 */
		private static final long serialVersionUID = -2314699159249561484L;
		
		
		@SuppressWarnings("unchecked")
		public ContactForm(String id, IModel model) {
			super(id, model);
			Fragment fragment=new Fragment("container", "editFragment", this);
			//add form component
			addToFieldEdit(fragment);
			//add delete button
			addDeleteButton(fragment);
			//add save button
			addSaveButton(fragment);
			
			// add the fragment to form
			add(fragment);
			
			

		}
		
		@SuppressWarnings({ "rawtypes", "unchecked"})
		private void addToFieldEdit(Fragment fragment) {
			
			TextField name = new TextField("namePerson");
	         name.setRequired(true);
			 name.add(new UserNameValidator());
			 
	        fragment.add(name);
	        
	        TextField phoneNumber = new TextField("phoneNumber");
	        
	        	phoneNumber.setRequired(true);
	        	phoneNumber.add(new PhoneValidator());
		       
	        fragment.add(phoneNumber);

	        TextField email = new TextField("emailAdd");
	        	email.add(StringValidator.maximumLength(150));
    	        email.add(EmailAddressValidator.getInstance());
    	        
	        fragment.add(email);
	       
            
            

		}
		
		@SuppressWarnings({ "serial" })
	private void addDeleteButton(Fragment fragment) {
			 fragment.add(new Button("delete") {
		        	public void onSubmit() {
		                //ContactPerson c = (ContactPerson) getForm().getModelObject();
		                User user=UserSession.getInstance().getUser();
		               //delete method called in business layer
		                cPersonImpl.deleteContact(user,passObject);
		                
		                succesFeedBackPanel.getFeedbackMessages().success(this, "Deleted Successfully");
		                
		                
		            }
		        			           
		            });
			 
			
		}
		@SuppressWarnings({ "serial" })
		private void addSaveButton(Fragment fragment) {
			 fragment.add(new Button("save") {
		        	public void onSubmit() {
		                ContactPerson c = (ContactPerson) getForm().getModelObject();
		                
		                LOGGER.debug("Model object phone : "+c.getPhoneNumber());
		                LOGGER.debug("passobject phone : "+passObject.getPhoneNumber());
		                
		                User user=UserSession.getInstance().getUser();
		                //update method called in business layer
		                try {
							cPersonImpl.updateEntry(user,passObject,c.getNamePerson(),c.getEmailAdd(),c.getPhoneNumber());
							succesFeedBackPanel.getFeedbackMessages().success(this, "Updated Successfully");
						} catch (Exception e) {
							error("error in updating");
							LOGGER.error("error in updating "+e.getMessage());
						}
		                
		                
		                
		                
		            }

		           
		            });
		}
		
		
	}

}
