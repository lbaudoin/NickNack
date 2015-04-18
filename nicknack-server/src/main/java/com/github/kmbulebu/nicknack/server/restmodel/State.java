package com.github.kmbulebu.nicknack.server.restmodel;

import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonView;

public class State {

	@JsonView(View.Summary.class)
	private UUID uuid;
	
	@JsonView(View.Summary.class)
	private Map<UUID, String[]> attributes;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Map<UUID, String[]> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<UUID, String[]> attributes) {
		this.attributes = attributes;
	}
	
	
	
}
