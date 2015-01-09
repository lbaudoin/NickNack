package com.github.kmbulebu.nicknack.core.providers.settings;


public abstract class AbstractProviderIntegerSettingDefinition extends AbstractProviderSettingDefinition<Integer> {

	public AbstractProviderIntegerSettingDefinition(String key, String name, String description, boolean isRequired) {
		super(key, name, description, isRequired);
	}
	
	@Override
	public String save(Integer settingValue) {
		return Integer.toString(settingValue);
	}
	
	@Override
	public Integer load(String savedData) {
		return Integer.valueOf(savedData);
	}

}
