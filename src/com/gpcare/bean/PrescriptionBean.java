package com.gpcare.bean;

public class PrescriptionBean {

	String Item_ordered;
	String Quantity_ordered;
	String Date_ordered;
	String Estimate_delivery_date;
	public PrescriptionBean(String item_ordered, String quantity_ordered,
			String date_ordered, String estimate_delivery_date) {
		super();
		Item_ordered = item_ordered;
		Quantity_ordered = quantity_ordered;
		Date_ordered = date_ordered;
		Estimate_delivery_date = estimate_delivery_date;
	}
	public String getItem_ordered() {
		return Item_ordered;
	}
	public void setItem_ordered(String item_ordered) {
		Item_ordered = item_ordered;
	}
	public String getQuantity_ordered() {
		return Quantity_ordered;
	}
	public void setQuantity_ordered(String quantity_ordered) {
		Quantity_ordered = quantity_ordered;
	}
	public String getDate_ordered() {
		return Date_ordered;
	}
	public void setDate_ordered(String date_ordered) {
		Date_ordered = date_ordered;
	}
	public String getEstimate_delivery_date() {
		return Estimate_delivery_date;
	}
	public void setEstimate_delivery_date(String estimate_delivery_date) {
		Estimate_delivery_date = estimate_delivery_date;
	}
	
	
}
