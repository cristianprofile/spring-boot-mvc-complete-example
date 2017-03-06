package com.mylab.cromero.controller.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

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
        String builder = "PizzaForm [name=" +
                name +
                "]";
        return builder;
	}
 	
	


	 
	 
	
}
