package com.github.kmbulebu.nicknack.providers.dsc.settings;

import com.github.kmbulebu.nicknack.core.providers.settings.AbstractProviderSettingDefinition;
import com.github.kmbulebu.nicknack.core.valuetypes.WholeNumberType;

public class ActivePartitionsSettingDefinition extends AbstractProviderSettingDefinition<WholeNumberType, Integer>  {

	public ActivePartitionsSettingDefinition() {
		super("partitions", new WholeNumberType(1, 16, 1), null, "Active Partitions", "Add a partition to NickNack. If none are specified, all partitions are used.", false, true);
	}

}
