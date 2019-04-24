package com.addressbook.search;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.PageCreator;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.WindowClosedCallback;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import com.addressbook.businesslogic.ContactPersonImpl;
import com.addressbook.dto.ContactPerson;
import com.addressbook.landing.SignUp;

public class ContactPage extends WebPage{
	
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 773373378524218295L;
	private ModalWindow modalWindow;
	private String action;

	@SuppressWarnings({ "unchecked", "serial" })
	public ContactPage(PageParameters params) {
		modalWindow=new ModalWindow("modalWindow");
		
        final String searchString = params.get("searchString").toString();
        
        IModel<ContactPerson> contactsModel = new LoadableDetachableModel<ContactPerson>() {
            /**
			 * 
			 */
			private static final long serialVersionUID = -6645316581893767636L;

			protected ContactPerson load() {
            	ContactPersonImpl cmpl=new ContactPersonImpl();;
                return cmpl.getPerson(searchString);
            }
        };
        
        modalWindow.setPageCreator(new PageCreator() {
			
			@Override
			public Page createPage() {
				// TODO Auto-generated method stub
				PageParameters pageParameters=new PageParameters();
				pageParameters.add("id", searchString);
				return new ViewContact(pageParameters, action);
			}
		});
        
        modalWindow.setTitle("View Contact");
        modalWindow.setWindowClosedCallback(new WindowClosedCallback() {
			
			@Override
			public void onClose(AjaxRequestTarget target) {
				// TODO Auto-generated method stub
				
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
        
        
        add(new Button("back") {
            public void onSubmit() {
                setResponsePage(ResponsePage.class);
            }
        }.setDefaultFormProcessing(false));
        
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
	
	

