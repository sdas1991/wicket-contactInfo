package com.addressbook.search;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;

import com.addressbook.businesslogic.ContactPersonImpl;
import com.addressbook.dto.ContactPerson;



public class AddContact extends WebPage{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1861107066037065657L;


	private ContactPersonImpl cPersonImpl;
	
	
	private ContactPerson cperson;
	@SuppressWarnings({ "rawtypes", "serial" })
	public AddContact() {
		
		cPersonImpl=new ContactPersonImpl();
		
		IModel iPersonImpl = new LoadableDetachableModel() {
		    protected Object load() {
		    	ContactPerson cperson = cPersonImpl.getEmptyPerson();;
		        return cperson;
		    }
		};
		
		
		Form<Object> form=new Form<Object>("addForm");
		
		form.add(new TextField<String>("namePerson",new PropertyModel<String>(iPersonImpl.getObject(),"namePerson")));
		form.add(new TextField<String>("phoneNumber",new PropertyModel<String>(iPersonImpl.getObject(),"phoneNumber")));
		form.add(new TextField<String>("emailAdd",new PropertyModel<String>(iPersonImpl.getObject(),"emailAdd")));
		
		addSaveButton(form);
		
		add(form);
	}
	
	@SuppressWarnings({ "serial" })
	private void addSaveButton(Form<?> form) {
		 form.add(new Button("add") {
	        	public void onSubmit() {
	                ContactPerson c = (ContactPerson) getForm().getModelObject();
	                cPersonImpl.saveContact(c);
	                info("Saved Successfully");
	                
	                
	            }

	           
	            });

}
	
}