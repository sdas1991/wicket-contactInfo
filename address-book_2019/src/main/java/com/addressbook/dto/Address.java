package com.addressbook.dto;

public class Address extends ModelBase{
	
	private String addressLine;
	private String pincode;
	
	
	/*private String appartment;
	private String zipCode;*/
	
	public Address() {
		// TODO Auto-generated constructor stub
	}
	
	public Address(String addressLine,String pincode) {
		this.addressLine=addressLine;
		this.pincode=pincode;
		

	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}
	

	

}
