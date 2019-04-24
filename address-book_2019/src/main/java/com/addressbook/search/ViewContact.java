package com.addressbook.search;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.addressbook.businesslogic.ContactPersonImpl;
import com.addressbook.dto.ContactPerson;
import com.addressbook.errorhandling.ErrorFilter;

public class ViewContact extends WebPage{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2099163414637207501L;


	private String action;
	
	
	private Fragment fragment;
	private ContactPersonImpl cPersonImpl;
	private Form<?> form;
	
	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	public ViewContact(PageParameters pageParameters, String action) {
		// TODO Auto-generated constructor stub
		this.action=action;
		cPersonImpl=new ContactPersonImpl();
		
		setDefaultModel(new CompoundPropertyModel(new LoadableDetachableModel() {
            protected Object load() {
                return cPersonImpl.getEmptyPerson();
            }
        }));
		form=new Form("viewForm", getDefaultModel());
		
		
		addComponents(pageParameters, form);
		
	
       
	}

	private void addComponents(PageParameters pageParameters, Form<?> form) {
		
		
		FeedbackPanel errorFeedBackPanel = new FeedbackPanel("feedback",
				new ErrorFilter(FeedbackMessage.ERROR));
		FeedbackPanel succesFeedBackPanel = new FeedbackPanel("succes",
				new ErrorFilter(FeedbackMessage.SUCCESS));
		
		add(errorFeedBackPanel);
		add(succesFeedBackPanel);
		
	if (action.equalsIgnoreCase("view")) {
		fragment=new Fragment("container", "editFragment", form);
	}
			
			
			addFragmentEdit(fragment, form);
			addDeleteButton(fragment);
			addSaveButton(fragment);
			
		form.add(fragment);
		add(form);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	public void addFragmentEdit(Fragment fragment, Form<?> form) {
		setDefaultModel(new CompoundPropertyModel(new LoadableDetachableModel() {
            protected Object load() {
                return cPersonImpl.getEmptyPerson();
            }
        }));
		addToFieldEdit(fragment);
	}
	
	@SuppressWarnings({ "rawtypes"})
	private void addToFieldEdit(Fragment fragment) {
		
		TextField name = new TextField("namePerson");
        name.setRequired(true);
        //firstName.add(StringValidator.maximumLength(15));
        fragment.add(name);
        
        TextField phoneNumber = new TextField("phoneNumber");
        phoneNumber.setRequired(true);
        //lastName.add(StringValidator.maximumLength(20));
        fragment.add(phoneNumber);

        TextField email = new TextField("emailAdd");
        //email.add(StringValidator.maximumLength(150));
        //email.add(EmailAddressValidator.getInstance());
        fragment.add(email);
       

	}
	
	
	
	@SuppressWarnings({ "serial" })
	private void addDeleteButton(Fragment fragment) {
		 fragment.add(new Button("delete") {
	        	public void onSubmit() {
	                ContactPerson c = (ContactPerson) getForm().getModelObject();
	                cPersonImpl.deleteContact(c);
	                info("Deleted Successfully");
	                
	                
	            }

	           
	            });
	}
	@SuppressWarnings({ "serial" })
	private void addSaveButton(Fragment fragment) {
		 fragment.add(new Button("save") {
	        	public void onSubmit() {
	                ContactPerson c = (ContactPerson) getForm().getModelObject();
	                cPersonImpl.saveContact(c);
	                info("Saved Successfully");
	                
	                
	            }

	           
	            });
	}

}
