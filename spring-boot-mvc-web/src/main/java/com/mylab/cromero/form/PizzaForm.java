package com.mylab.cromero.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class PizzaForm {
     

	
	@NotNull
    @NotEmpty
 	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PizzaForm [name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
 	
	


	 
	 
	
}
