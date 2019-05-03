package com.addressbook.contactmanagement;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.PageCreator;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.WindowClosedCallback;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import com.addressbook.businesslogic.ContactPersonImpl;
import com.addressbook.dto.ContactPerson;
import com.addressbook.dto.User;
import com.addressbook.errorhandling.ErrorFilter;
import com.addressbook.landing.SignUp;
import com.addressbook.landing.UserSession;

public class ContactPage extends WebPage{
	
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 773373378524218295L;
	private ModalWindow modalWindow;
	private String action;
	private ContactPerson passObject;

	@SuppressWarnings({ "unchecked", "serial" })
	public ContactPage(PageParameters params) {
		FeedbackPanel errorFeedBackPanel = new FeedbackPanel("feedback",
				new ErrorFilter(FeedbackMessage.ERROR));
		FeedbackPanel succesFeedBackPanel = new FeedbackPanel("success",
				new ErrorFilter(FeedbackMessage.SUCCESS));
		
		add(errorFeedBackPanel);
		add(succesFeedBackPanel);
		
		//show modal window
		modalWindow=new ModalWindow("modalWindow");
		
        final String searchString = params.get("searchString").toString();
        
        IModel contactsModel = new LoadableDetachableModel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = -6645316581893767636L;

			protected Object load() {
				
            	ContactPersonImpl cmpl=new ContactPersonImpl();
            	User user=UserSession.getInstance().getUser();
            	
                return cmpl.getPerson(user,searchString);
            }
        };
        
        
        
        modalWindow.setPageCreator(new PageCreator() {
			
			@Override
			public Page createPage() {
				// TODO Auto-generated method stub
				PageParameters pageParameters=new PageParameters();
				pageParameters.add("name", searchString);
				
				return new ViewContact(pageParameters, action, passObject);
			}
		});
        
        modalWindow.setTitle("View Contact");
        modalWindow.setWindowClosedCallback(new WindowClosedCallback() {
			
			@Override
			public void onClose(AjaxRequestTarget target) {
				// TODO Auto-generated method stub
				setResponsePage(ResponsePage.class);
				
			}
		});
        
        ListView contacts = new ListView("contacts", contactsModel) {

			@Override
			protected void populateItem(ListItem item) {
				// TODO Auto-generated method stub
				addList(item);
			}
           

			
        };
        add(modalWindow);
        
        add(contacts);
        
        
        
        
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
        
        add(new AjaxLink<String>("addContact") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 6573750371569433358L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				// TODO Auto-generated method stub
				setResponsePage(AddContact.class);
			}
		});
        
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addList(ListItem item) {
		final ContactPerson person=(ContactPerson)item.getModelObject();
		passObject=person;
       // addressModel.setObject(new Address("Texas", "3456"));
		
		AjaxLink view = new AjaxLink("view", item.getModel()) {
            
			@Override
			public void onClick(AjaxRequestTarget target) {
				// TODO Auto-generated method stub
				action="edit";
				modalWindow.show(target);
				
			}

			@Override
			public MarkupContainer setDefaultModel(IModel model) {
				// TODO Auto-generated method stub
				return this;
			}
        };
       
        view.add(new Label("name", new PropertyModel<>(person, "namePerson")));
        view.add(new Label("phone", new PropertyModel<>(person, "phoneNumber")));
        view.add(new Label("email", new PropertyModel<>(person, "emailAdd")));
        item.add(view);
        
        
           
        
        
    
        
        
    }
	

}
	
	

