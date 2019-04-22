package com.addressbook.viewcontrol.search;


import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.util.ListModel;


import com.addressbook.dto.Address;
import com.addressbook.dto.ContactPerson;
import com.addressbook.errorhandling.ErrorFilter;
import com.addressbook.viewcontrol.LoginPage;
import com.addressbook.viewcontrol.UserSession;

public class ResponsePage extends WebPage{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2788311229442360682L;
	private ContactPerson person;
	private ListModel<Address> listAddress;

	public ResponsePage() {
		
		person=new ContactPerson();
		
		add(new Label("msg", new Model<String>(UserSession.getInstance().getUser().getEmail())));
		loadAddress();
		addForm(person);	
		
		
	}
	 private void addForm(ContactPerson person) {
	        Form<?> form = createForm(person);
	        addAddressElements(form );
	        addSubmitButton(form);
	    }
	 
	 private Form<?> createForm(ContactPerson person) {
	       // add(new FeedbackPanel("feedback"));
		 
		 FeedbackPanel errorFeedBackPanel = new FeedbackPanel("feedback",
					new ErrorFilter(FeedbackMessage.ERROR));
			FeedbackPanel succesFeedBackPanel = new FeedbackPanel("succes",
					new ErrorFilter(FeedbackMessage.SUCCESS));
			
			add(errorFeedBackPanel);
			add(succesFeedBackPanel);
	        Form<?> form = new Form<>("contactForm");
	        form.add(new TextField<String>("namePlace",new PropertyModel<String>(person,"namePerson")));
			form.add(new TextField<String>("emailPlace",new PropertyModel<String>(person,"emailAdd")));
			form.add(new TextField<String>("phonePlace",new PropertyModel<String>(person,"phoneNumber")));
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
	        add(form);
	        return form;
	    }
	 
	 private void loadAddress() {
		 if(listAddress==null) {
			 listAddress = new ListModel<>();
			//listAddress.getObject().set(0, new Address("hello","567489"));
		 }
		 
	    }
	
	private void addAddressElements(Form<?> form) {
        form.add(new ListView<Address>("addPlace", listAddress) {


			@Override
			protected void populateItem(ListItem<Address> item) {
				// TODO Auto-generated method stub
				addAddress(item);
				
			}

        });
    }
	
	private void addAddress(ListItem<Address> address) {
        IModel<Address> addressModel = address.getModel();
        addressModel.setObject(new Address("Texas", "3456"));
        //address.add(new TextField<String>("place", new PropertyModel<>(addressModel, "addressLine")));
        //address.add(new TextField<String>("pincode", new PropertyModel<>(addressModel, "pincode")));
        
        
    }
	
	private void addSubmitButton(Form<?> form) {
        form.add(new Button("submit") {

            @Override
            public void onSubmit() {
                System.out.println(person.getEmailAdd());
            }

        });
    }
	
	

}
