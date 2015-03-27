package com.github.kmbulebu.nicknack.providers.dsc.attributes;

import java.util.UUID;

import com.github.kmbulebu.nicknack.core.attributes.BasicAttributeDefinition;
import com.github.kmbulebu.nicknack.core.valuetypes.builder.ValueTypeBuilder;
import com.github.kmbulebu.nicknack.core.valuetypes.impl.text.Text;

public class PartitionLabelAttributeDefinition extends BasicAttributeDefinition<Text, String> {
	
	public static final PartitionLabelAttributeDefinition INSTANCE = new PartitionLabelAttributeDefinition();

	public PartitionLabelAttributeDefinition() {
		super(UUID.fromString("639d4cf6-48a3-4979-88dd-94802b19b2b1"), 
				"Partition Label",
				ValueTypeBuilder.text().build(),
				null,
				true,
				false);
	}

}
