package com.techmahindra.cadastrocliente.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.http.ResponseEntity;

import lombok.Getter;

public class RetornoResponseEntityEvent extends ApplicationEvent {
	
	private static final long serialVersionUID = -8166624242732195530L;
	
	@Getter
	private ResponseEntity<?> responseEntity;
	
	public RetornoResponseEntityEvent(Object source, ResponseEntity<?> responseEntity) {
		super(source);
		this.responseEntity = responseEntity;
	}
}
