package com.github.kmbulebu.nicknack.server.services.exceptions;

import java.util.UUID;

import com.github.kmbulebu.nicknack.core.actions.ActionDefinition;

public class EventFilterNotFoundException extends ResourceNotFoundException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5377153584663402285L;

	public EventFilterNotFoundException(String uuid) {
		super(ActionDefinition.class.getSimpleName(), uuid);
	}

	public EventFilterNotFoundException(UUID uuid) {
		this(uuid.toString());
	}
}
