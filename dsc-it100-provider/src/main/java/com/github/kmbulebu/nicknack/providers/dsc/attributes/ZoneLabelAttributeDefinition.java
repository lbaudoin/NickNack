package com.github.kmbulebu.nicknack.providers.dsc.attributes;

import java.util.UUID;

import com.github.kmbulebu.nicknack.core.attributes.BasicAttributeDefinition;
import com.github.kmbulebu.nicknack.core.valuetypes.TextType;

public class ZoneLabelAttributeDefinition extends BasicAttributeDefinition<TextType> {
	
	public static final ZoneLabelAttributeDefinition INSTANCE = new ZoneLabelAttributeDefinition();

	public ZoneLabelAttributeDefinition() {
		super(UUID.fromString("faa115a0-8e57-4fad-9f94-5c5c8f5d860b"), "Zone Label", new TextType(), true);
	}

}
