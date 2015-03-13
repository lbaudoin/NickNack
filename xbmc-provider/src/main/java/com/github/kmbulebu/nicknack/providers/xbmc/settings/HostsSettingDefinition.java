package com.github.kmbulebu.nicknack.providers.xbmc.settings;

import com.github.kmbulebu.nicknack.core.providers.settings.AbstractProviderSettingDefinition;
import com.github.kmbulebu.nicknack.core.valuetypes.HostnameType;

public class HostsSettingDefinition extends AbstractProviderSettingDefinition<HostnameType> {
	
	public HostsSettingDefinition() {
		super("host",
			new HostnameType(), null, "Kodi Hostname", 
			"Hostname or IP address of Kodi.",
			true,
			true);
	}

}