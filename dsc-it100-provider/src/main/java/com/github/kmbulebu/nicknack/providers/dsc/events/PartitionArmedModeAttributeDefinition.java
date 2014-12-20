package com.github.kmbulebu.nicknack.providers.dsc.events;

import java.util.UUID;

import com.github.kmbulebu.nicknack.core.events.BasicAttributeDefinition;
import com.github.kmbulebu.nicknack.core.units.StringUnit;

public class PartitionArmedModeAttributeDefinition extends BasicAttributeDefinition {
	
	public static final PartitionArmedModeAttributeDefinition INSTANCE = new PartitionArmedModeAttributeDefinition();

	public PartitionArmedModeAttributeDefinition() {
		super(UUID.fromString("86b7d0e2-8fc1-4963-a54e-be1bd9859c14"), "Partition Armed Mode", StringUnit.INSTANCE, false);
	}

}