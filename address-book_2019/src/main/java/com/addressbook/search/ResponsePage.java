package com.addressbook.search;


import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;


import com.addressbook.dto.ContactPerson;
import com.addressbook.errorhandling.ErrorFilter;
import com.addressbook.landing.LoginPage;
import com.addressbook.landing.UserSession;

public class ResponsePage extends WebPage{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2788311229442360682L;
	private ContactPerson person;
	private  Form<?> form;
	
	
	private String searchString;

	public ResponsePage() {
		
		person=new ContactPerson();
		
		
		add(new Label("msg", new Model<String>(UserSession.getInstance().getUser().getEmail())));
		
		
		addForm(person);	
		
		
	}
	 private void addForm(ContactPerson person) {
	        form = createForm(person);
	        
	        
	        addSubmitButton(form);
	        add(form);
	    }
	 
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	private Form<?> createForm(ContactPerson person) {
	       // add(new FeedbackPanel("feedback"));
		 
		 
		 Form<?> form = new Form<>("search-form");
		 FeedbackPanel errorFeedBackPanel = new FeedbackPanel("feedback",
					new ErrorFilter(FeedbackMessage.ERROR));
			FeedbackPanel succesFeedBackPanel = new FeedbackPanel("succes",
					new ErrorFilter(FeedbackMessage.SUCCESS));
			
			add(errorFeedBackPanel);
			add(succesFeedBackPanel);
	        
	        
	        add(new TextField("searchString",
	                  new PropertyModel(this, "searchString")));
	        add(new BookmarkablePageLink("addContact",
	                  AddContact.class));
			
	        form.add(new Link<String>("signOut") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 5953529955254239528L;

				@Override
				public void onClick() {
					UserSession.getInstance().invalidate();
					
					setResponsePage(LoginPage.class);
					
				}
				
			});
	        
	        return form;
	    }
	 
	 
	 
	 public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	
	
	private void addSubmitButton(Form<?> form) {
        form.add(new Button("submit") {

            @Override
            public void onSubmit() {
            	PageParameters params = new PageParameters();
                params.add("searchString", getSearchString());
                setResponsePage(ContactPage.class, params);
            }

        });
    }
	
	

}
