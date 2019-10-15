package com.osiris.food.event;

public class FragmentChangeEvent {

	private String frgment;


	public FragmentChangeEvent(String frgment) {
		this.frgment = frgment;
	}

	public String getFrgment() {
		return frgment;
	}

	public void setFrgment(String frgment) {
		this.frgment = frgment;
	}
}
